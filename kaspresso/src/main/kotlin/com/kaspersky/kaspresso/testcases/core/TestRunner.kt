package com.kaspersky.kaspresso.testcases.core

import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.extensions.other.getException
import com.kaspersky.kaspresso.interceptors.TestRunInterceptor
import com.kaspersky.kaspresso.interceptors.impl.composite.TestRunCompositeInterceptor
import com.kaspersky.kaspresso.testcases.models.RunMainTestSectionResult
import com.kaspersky.kaspresso.testcases.models.TestBody
import com.kaspersky.kaspresso.testcases.models.TestInfo

internal class TestRunner {

    fun run(testBody: TestBody) {
        val exceptions: MutableList<Throwable> = mutableListOf()
        val testRunInterceptor: TestRunInterceptor =
            TestRunCompositeInterceptor(
                Configurator.testRunInterceptors,
                exceptions
            )
        val stepsProcessHandler = StepsManager(testBody.testName)
        var currentTestInfo = TestInfo(testBody.testName)
        var testPassed = true
        var resultException: Throwable? = null

        try {
            testRunInterceptor.onTestStarted(currentTestInfo)

            runBeforeTestSection(currentTestInfo, testBody.beforeTestActions, testRunInterceptor)

            val runMainTestSectionResult = runMainTestSection(
                currentTestInfo,
                testBody.mainSection,
                testRunInterceptor,
                stepsProcessHandler
            )
            currentTestInfo = runMainTestSectionResult.testInfo
            runMainTestSectionResult.throwable?.let { throw it }
        } catch (e: Throwable) {
            testPassed = false
            exceptions.add(e)
        } finally {
            try {
                runAfterTestSection(currentTestInfo, testBody.afterTestActions, testRunInterceptor)
            } catch (e: Throwable) {
                testPassed = false
                exceptions.add(e)
            } finally {
                resultException = exceptions.getException()
                currentTestInfo = currentTestInfo.copy(
                    throwable = resultException
                )
                testRunInterceptor.onTestFinished(currentTestInfo, testPassed)
            }
        }

        resultException?.let { throw it }
    }

    private fun runBeforeTestSection(
        currentTestInfo: TestInfo,
        beforeTestActions: () -> Unit,
        testRunInterceptor: TestRunInterceptor
    ) {
        try {
            testRunInterceptor.onBeforeSectionStarted(currentTestInfo)
            beforeTestActions.invoke()
            testRunInterceptor.onBeforeSectionFinishedSuccess(currentTestInfo)
        } catch (e: Throwable) {
            testRunInterceptor.onBeforeSectionFinishedFailed(currentTestInfo, e)
            throw e
        }
    }

    private fun runMainTestSection(
        currentTestInfo: TestInfo,
        mainSection: TestContext.() -> Unit,
        testRunInterceptor: TestRunInterceptor,
        stepsProcessHandler: StepsManager
    ): RunMainTestSectionResult {
        var runMainTestSectionResult: RunMainTestSectionResult
        checkTestInfoOnFinishAllSteps(currentTestInfo)
        try {
            testRunInterceptor.onMainSectionStarted(currentTestInfo)

            mainSection.invoke(TestContext(stepsProcessHandler))

            val testResultInSteps = stepsProcessHandler.onAllStepsFinishedAndGetResultInSteps()
            val updatedTestInfo = currentTestInfo.copy(steps = testResultInSteps)
            runMainTestSectionResult = RunMainTestSectionResult(updatedTestInfo)

            testRunInterceptor.onMainSectionFinishedSuccess(updatedTestInfo)
        } catch (e: Throwable) {
            val testResultInSteps = stepsProcessHandler.onAllStepsFinishedAndGetResultInSteps()
            val updatedTestInfo = currentTestInfo.copy(steps = testResultInSteps)
            runMainTestSectionResult = RunMainTestSectionResult(updatedTestInfo, e)

            testRunInterceptor.onMainSectionFinishedFailed(updatedTestInfo, e)
        }
        return runMainTestSectionResult
    }

    private fun checkTestInfoOnFinishAllSteps(testInfo: TestInfo) {
        if (testInfo.steps.isNotEmpty()) {
            throw AssertionError("onAllStepsFinishedAndGetResultInSteps called on already finished test")
        }
    }

    private fun runAfterTestSection(
        currentTestInfo: TestInfo,
        afterTestActions: () -> Unit,
        testRunInterceptor: TestRunInterceptor
    ) {
        try {
            testRunInterceptor.onAfterSectionStarted(currentTestInfo)
            afterTestActions.invoke()
            testRunInterceptor.onAfterSectionFinishedSuccess(currentTestInfo)
        } catch (e: Throwable) {
            testRunInterceptor.onAfterSectionFinishedFailed(currentTestInfo, e)
            throw e
        }
    }

}
