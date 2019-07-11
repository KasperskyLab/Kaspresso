package com.kaspersky.kaspresso.testcases.core

import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.extensions.other.getException
import com.kaspersky.kaspresso.interceptors.TestRunInterceptor
import com.kaspersky.kaspresso.interceptors.impl.composite.TestRunCompositeInterceptor
import com.kaspersky.kaspresso.testcases.core.step.StepsManager
import com.kaspersky.kaspresso.testcases.core.testcontext.BaseTestContext
import com.kaspersky.kaspresso.testcases.core.testcontext.TestContext
import com.kaspersky.kaspresso.testcases.models.MainTestSectionResult
import com.kaspersky.kaspresso.testcases.models.TestBody
import com.kaspersky.kaspresso.testcases.models.info.StepInfo
import com.kaspersky.kaspresso.testcases.models.info.TestInfo

internal class TestRunner<InitData, Data>(
    private val configurator: Configurator
) {
    fun run(testBody: TestBody<InitData, Data>) {

        val exceptions: MutableList<Throwable> = mutableListOf()
        val resultException: Throwable?

        val testRunInterceptor: TestRunInterceptor = TestRunCompositeInterceptor(
            configurator.testRunInterceptors,
            exceptions
        )

        val stepsManager = StepsManager(testBody.testName)
        var testInfo = TestInfo(testBody.testName)
        var testPassed = true

        try {
            testRunInterceptor.onTestStarted(testInfo)

            val data: Data = runBeforeTestSection(
                testInfo,
                testBody.beforeTestActions,
                testBody.initActions,
                testBody.transformActionsList,
                testRunInterceptor,
                testBody.dataProducer
            )

            val mainTestSectionResult: MainTestSectionResult = runMainTestSection(
                testInfo,
                testBody.steps,
                testRunInterceptor,
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
                runAfterTestSection(testInfo, testBody.afterTestActions, testRunInterceptor)
            } catch (e: Throwable) {
                testPassed = false
                exceptions.add(e)
            } finally {
                resultException = exceptions.getException()
                testInfo = testInfo.copy(throwable = resultException)
                testRunInterceptor.onTestFinished(testInfo, testPassed)
                configurator.reset()
            }
        }

        resultException?.let { throw it }
    }

    @Suppress("LongParameterList")
    private fun runBeforeTestSection(
        testInfo: TestInfo,
        beforeTestActions: (BaseTestContext.() -> Unit)?,
        initDataActions: (InitData.() -> Unit)?,
        transformDataActionsList: List<Data.() -> Unit>,
        testRunInterceptor: TestRunInterceptor,
        dataProducer: ((InitData.() -> Unit)?) -> Data
    ): Data {

        try {
            testRunInterceptor.onBeforeSectionStarted(testInfo)
            beforeTestActions?.invoke(BaseTestContext(configurator))

            val data: Data = dataProducer.invoke(initDataActions)

            for (transformation in transformDataActionsList) {
                transformation.invoke(data)
            }

            testRunInterceptor.onBeforeSectionFinishedSuccess(testInfo)

            return data
        } catch (e: Throwable) {
            testRunInterceptor.onBeforeSectionFinishedFailed(testInfo, e)
            throw e
        }
    }

    private fun runMainTestSection(
        testInfo: TestInfo,
        steps: TestContext<Data>.() -> Unit,
        testRunInterceptor: TestRunInterceptor,
        stepsManager: StepsManager,
        data: Data
    ): MainTestSectionResult {

        var mainTestSectionResult: MainTestSectionResult

        checkTestInfo(testInfo)

        try {
            testRunInterceptor.onMainSectionStarted(testInfo)

            steps.invoke(
                TestContext(configurator, stepsManager, data)
            )

            val allStepsResult: List<StepInfo> = stepsManager.getAllStepsResult()
            val updatedTestInfo = testInfo.copy(stepInfos = allStepsResult)
            mainTestSectionResult = MainTestSectionResult(updatedTestInfo)

            testRunInterceptor.onMainSectionFinishedSuccess(updatedTestInfo)
        } catch (e: Throwable) {
            val allStepsResult: List<StepInfo> = stepsManager.getAllStepsResult()
            val updatedTestInfo = testInfo.copy(stepInfos = allStepsResult)
            mainTestSectionResult = MainTestSectionResult(updatedTestInfo, e)

            testRunInterceptor.onMainSectionFinishedFailed(updatedTestInfo, e)
        }

        return mainTestSectionResult
    }

    private fun runAfterTestSection(
        testInfo: TestInfo,
        afterTestActions: (BaseTestContext.() -> Unit)?,
        testRunInterceptor: TestRunInterceptor
    ) {
        try {
            testRunInterceptor.onAfterSectionStarted(testInfo)
            afterTestActions?.invoke(BaseTestContext(configurator))
            testRunInterceptor.onAfterSectionFinishedSuccess(testInfo)
        } catch (e: Throwable) {
            testRunInterceptor.onAfterSectionFinishedFailed(testInfo, e)
            throw e
        }
    }

    private fun checkTestInfo(testInfo: TestInfo) {
        if (testInfo.stepInfos.isNotEmpty()) {
            throw AssertionError("getAllStepsResult called on already finished test.")
        }
    }
}