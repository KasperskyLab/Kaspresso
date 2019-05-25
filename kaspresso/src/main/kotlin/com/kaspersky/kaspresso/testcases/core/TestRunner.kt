package com.kaspersky.kaspresso.testcases.core

import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.extensions.other.getException
import com.kaspersky.kaspresso.interceptors.TestRunInterceptor
import com.kaspersky.kaspresso.interceptors.impl.composite.TestRunCompositeInterceptor
import com.kaspersky.kaspresso.testcases.models.TestBody

class TestRunner {
    fun run(testBody: TestBody) {

        val exceptions: MutableList<Throwable> = mutableListOf()
        val testRunInterceptor: TestRunInterceptor =
            TestRunCompositeInterceptor(
                Configurator.testRunInterceptors,
                exceptions
            )

        val stepManager = StepManager(testBody.testInfo)


        var testPassed = true

        var resultException: Throwable? = null
        try {
            testRunInterceptor.onTestStarted(testBody.testInfo)

            runBeforeTestSection(testBody, testRunInterceptor)
            runMainTestSection(testBody, testRunInterceptor, stepManager)
        } catch (e: Throwable) {
            testPassed = false
            exceptions.add(e)
        } finally {
            try {
                runAfterTestSection(testBody, testRunInterceptor)
            } catch (e: Throwable) {
                testPassed = false
                exceptions.add(e)
            } finally {
                resultException = exceptions.getException()
                stepManager.onTestFinished(resultException)
                testRunInterceptor.onTestFinished(testBody.testInfo, testPassed)
            }
        }

        resultException?.let { throw it }
    }

    private fun runBeforeTestSection(
        testBody: TestBody,
        testRunInterceptor: TestRunInterceptor
    ) {
        try {
            testRunInterceptor.onBeforeSectionStarted(testBody.testInfo)
            testBody.beforeTestActions.invoke()
            testRunInterceptor.onBeforeSectionFinishedSuccess(testBody.testInfo)
        } catch (e: Throwable) {
            testRunInterceptor.onBeforeSectionFinishedFailed(testBody.testInfo, e)
            throw e
        }
    }

    private fun runMainTestSection(
        testBody: TestBody,
        testRunInterceptor: TestRunInterceptor,
        stepManager: StepManager
    ) {
        try {
            testRunInterceptor.onMainSectionStarted(testBody.testInfo)
            testBody.mainSection.invoke(TestContext(stepManager))
            stepManager.onAllStepsFinished()
            testRunInterceptor.onMainSectionFinishedSuccess(testBody.testInfo)
        } catch (e: Throwable) {
            stepManager.onAllStepsFinished()
            testRunInterceptor.onMainSectionFinishedFailed(testBody.testInfo, e)
            throw e
        }
    }

    private fun runAfterTestSection(
        testBody: TestBody,
        testRunInterceptor: TestRunInterceptor
    ) {
        try {
            testRunInterceptor.onAfterSectionStarted(testBody.testInfo)
            testBody.afterTestActions.invoke()
            testRunInterceptor.onAfterSectionFinishedSuccess(testBody.testInfo)
        } catch (e: Throwable) {
            testRunInterceptor.onAfterSectionFinishedFailed(testBody.testInfo, e)
            throw e
        }
    }
}