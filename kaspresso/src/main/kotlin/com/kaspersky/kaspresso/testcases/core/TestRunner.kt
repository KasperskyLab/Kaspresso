package com.kaspersky.kaspresso.testcases.core

import com.kaspersky.kaspresso.enricher.MainSectionEnricher
import com.kaspersky.kaspresso.enricher.impl.composite.CompositeMainSectionEnricher
import com.kaspersky.kaspresso.interceptors.watcher.testcase.TestRunWatcherInterceptor
import com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.composite.TestRunCompositeWatcherInterceptor
import com.kaspersky.kaspresso.internal.extensions.other.getException
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.testcases.core.step.StepsManager
import com.kaspersky.kaspresso.testcases.core.testcontext.BaseTestContext
import com.kaspersky.kaspresso.testcases.core.testcontext.TestContext
import com.kaspersky.kaspresso.testcases.models.MainTestSectionResult
import com.kaspersky.kaspresso.testcases.models.TestBody
import com.kaspersky.kaspresso.testcases.models.info.StepInfo
import com.kaspersky.kaspresso.testcases.models.info.TestInfo

/**
 * Kaspresso test runner. Runs all test sections and calls test run watcher interceptors.
 */
internal class TestRunner<InitData, Data>(
    private val kaspresso: Kaspresso
) {
    fun run(testBody: TestBody<InitData, Data>) {
        val exceptions: MutableList<Throwable> = mutableListOf()
        val resultException: Throwable?
        val baseTestContext = BaseTestContext(kaspresso)

        val testRunWatcherInterceptor: TestRunWatcherInterceptor =
            TestRunCompositeWatcherInterceptor(
                kaspresso.testRunWatcherInterceptors,
                kaspresso.libLogger,
                exceptions
            )
        testRunWatcherInterceptor.setBaseTestContext(baseTestContext)
        val compositeMainSectionEnricher: CompositeMainSectionEnricher<Data> =
            CompositeMainSectionEnricher(
                testBody.mainSectionEnrichers,
                exceptions
            )

        val stepsManager = StepsManager(testBody.testName, kaspresso.params.stepParams)
        var testInfo = TestInfo(testBody.testName)
        var testPassed = true

        try {
            kaspresso.libLogger.line()
            testRunWatcherInterceptor.onTestStarted(testInfo)

            val data: Data = runBeforeTestSection(
                testInfo,
                baseTestContext,
                testBody.beforeTestActions,
                testBody.initActions,
                testBody.transformActionsList,
                testRunWatcherInterceptor,
                testBody.dataProducer
            )

            val mainTestSectionResult: MainTestSectionResult = runMainTestSection(
                testInfo,
                testBody.steps,
                testRunWatcherInterceptor,
                compositeMainSectionEnricher,
                stepsManager,
                data
            )

            testInfo = mainTestSectionResult.testInfo
            mainTestSectionResult.throwable?.let { throw it }
        } catch (e: Throwable) {
            testPassed = false
            exceptions.add(e)
        } finally {
            try {
                runAfterTestSection(
                    testInfo,
                    baseTestContext,
                    testBody.afterTestActions,
                    testRunWatcherInterceptor
                )
            } catch (e: Throwable) {
                testPassed = false
                exceptions.add(e)
            } finally {
                resultException = exceptions.getException()
                testInfo = testInfo.copy(throwable = resultException)
                testRunWatcherInterceptor.onTestFinished(testInfo, testPassed)
                kaspresso.libLogger.line()
                kaspresso.adbServer.disconnectServer()
                kaspresso.reset()
            }
        }

        resultException?.let { throw it }
    }

    @Suppress("LongParameterList")
    private fun runBeforeTestSection(
        testInfo: TestInfo,
        baseTestContext: BaseTestContext,
        beforeTestActions: (BaseTestContext.() -> Unit)?,
        initDataActions: (InitData.() -> Unit)?,
        transformDataActionsList: List<Data.() -> Unit>,
        testRunWatcherInterceptor: TestRunWatcherInterceptor,
        dataProducer: ((InitData.() -> Unit)?) -> Data
    ): Data {

        try {
            testRunWatcherInterceptor.onBeforeSectionStarted(testInfo)
            beforeTestActions?.invoke(baseTestContext)

            val data: Data = dataProducer.invoke(initDataActions)

            for (transformation in transformDataActionsList) {
                transformation.invoke(data)
            }

            testRunWatcherInterceptor.onBeforeSectionFinishedSuccess(testInfo)

            return data
        } catch (e: Throwable) {
            testRunWatcherInterceptor.onBeforeSectionFinishedFailed(testInfo, e)
            throw e
        }
    }

    @Suppress("LongParameterList")
    private fun runMainTestSection(
        testInfo: TestInfo,
        steps: TestContext<Data>.() -> Unit,
        testRunWatcherInterceptor: TestRunWatcherInterceptor,
        mainSectionEnricher: MainSectionEnricher<Data>,
        stepsManager: StepsManager,
        data: Data
    ): MainTestSectionResult {

        var mainTestSectionResult: MainTestSectionResult

        checkTestInfo(testInfo)

        try {
            val testContext = TestContext(kaspresso, stepsManager, data)
            testRunWatcherInterceptor.onMainSectionStarted(testInfo)

            testContext.run { mainSectionEnricher.run { beforeMainSectionRun(testInfo) } }
            steps.invoke(testContext)
            testContext.run { mainSectionEnricher.run { afterMainSectionRun(testInfo) } }

            val allStepsResult: List<StepInfo> = stepsManager.getAllStepsResult()
            val updatedTestInfo = testInfo.copy(stepInfos = allStepsResult)
            mainTestSectionResult = MainTestSectionResult(updatedTestInfo)

            testRunWatcherInterceptor.onMainSectionFinishedSuccess(updatedTestInfo)
        } catch (e: Throwable) {
            val allStepsResult: List<StepInfo> = stepsManager.getAllStepsResult()
            val updatedTestInfo = testInfo.copy(stepInfos = allStepsResult)
            mainTestSectionResult = MainTestSectionResult(updatedTestInfo, e)

            testRunWatcherInterceptor.onMainSectionFinishedFailed(updatedTestInfo, e)
        }

        return mainTestSectionResult
    }

    private fun runAfterTestSection(
        testInfo: TestInfo,
        baseTestContext: BaseTestContext,
        afterTestActions: (BaseTestContext.() -> Unit)?,
        testRunWatcherInterceptor: TestRunWatcherInterceptor
    ) {
        try {
            testRunWatcherInterceptor.onAfterSectionStarted(testInfo)
            afterTestActions?.invoke(baseTestContext)
            testRunWatcherInterceptor.onAfterSectionFinishedSuccess(testInfo)
        } catch (e: Throwable) {
            testRunWatcherInterceptor.onAfterSectionFinishedFailed(testInfo, e)
            throw e
        }
    }

    private fun checkTestInfo(testInfo: TestInfo) {
        if (testInfo.stepInfos.isNotEmpty()) {
            throw AssertionError("getAllStepsResult called on already finished test.")
        }
    }
}
