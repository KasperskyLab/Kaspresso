package com.kaspersky.kaspressonitrogen.testcases.core

import com.kaspersky.kaspressonitrogen.enricher.NitrogenMainSectionEnricher
import com.kaspersky.kaspressonitrogen.enricher.impl.composite.NitrogenCompositeMainSectionEnricher
import com.kaspersky.kaspresso.interceptors.watcher.testcase.NitrogenTestRunWatcherInterceptor
import com.kaspersky.kaspressonitrogen.interceptors.watcher.testcase.impl.composite.NitrogenTestRunCompositeWatcherInterceptor
import com.kaspersky.kaspresso.internal.extensions.other.getException
import com.kaspersky.kaspressonitrogen.kaspresso.KaspressoNitrogen
import com.kaspersky.kaspresso.testcases.core.step.StepsManager
import com.kaspersky.kaspressonitrogen.testcases.core.testcontext.NitrogenBaseTestContext
import com.kaspersky.kaspressonitrogen.testcases.core.testcontext.NitrogenTestContext
import com.kaspersky.kaspresso.testcases.models.MainTestSectionResult
import com.kaspersky.kaspressonitrogen.testcases.models.NitrogenTestBody
import com.kaspersky.kaspresso.testcases.models.info.StepInfo
import com.kaspersky.kaspresso.testcases.models.info.TestInfo

internal class NitrogenTestRunner<InitData, Data>(
        private val kaspresso: KaspressoNitrogen
) {
    fun run(testBody: NitrogenTestBody<InitData, Data>) {
        val exceptions: MutableList<Throwable> = mutableListOf()
        val resultException: Throwable?
        val baseTestContext =
            NitrogenBaseTestContext(
                kaspresso
            )

        val testRunWatcherInterceptor: NitrogenTestRunWatcherInterceptor =
            NitrogenTestRunCompositeWatcherInterceptor(
                kaspresso.testRunWatcherInterceptors,
                kaspresso.libLogger,
                exceptions
            )
        testRunWatcherInterceptor.setBaseTestContext(baseTestContext)
        val compositeMainSectionEnricher: NitrogenCompositeMainSectionEnricher<Data> =
            NitrogenCompositeMainSectionEnricher(
                testBody.mainSectionEnrichers,
                exceptions
            )

        val stepsManager = StepsManager(testBody.testName, kaspresso.params.stepParams)
        var testInfo = TestInfo(testBody.testName)
        var testPassed = true

        try {
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
            }
        }

        resultException?.let { throw it }
    }

    @Suppress("LongParameterList")
    private fun runBeforeTestSection(
        testInfo: TestInfo,
        baseTestContext: NitrogenBaseTestContext,
        beforeTestActions: (NitrogenBaseTestContext.() -> Unit)?,
        initDataActions: (InitData.() -> Unit)?,
        transformDataActionsList: List<Data.() -> Unit>,
        testRunWatcherInterceptor: NitrogenTestRunWatcherInterceptor,
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
        steps: NitrogenTestContext<Data>.() -> Unit,
        testRunWatcherInterceptor: NitrogenTestRunWatcherInterceptor,
        mainSectionEnricher: NitrogenMainSectionEnricher<Data>,
        stepsManager: StepsManager,
        data: Data
    ): MainTestSectionResult {

        var mainTestSectionResult: MainTestSectionResult

        checkTestInfo(testInfo)

        try {
            val testContext = NitrogenTestContext(kaspresso, stepsManager, data)
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
        baseTestContext: NitrogenBaseTestContext,
        afterTestActions: (NitrogenBaseTestContext.() -> Unit)?,
        testRunWatcherInterceptor: NitrogenTestRunWatcherInterceptor
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
