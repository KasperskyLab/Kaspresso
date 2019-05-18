package com.kaspersky.kaspresso.testcases.core

import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.extensions.other.getException
import com.kaspersky.kaspresso.interceptors.TestRunInterceptor
import com.kaspersky.kaspresso.interceptors.impl.composite.TestRunCompositeInterceptor
import com.kaspersky.kaspresso.testcases.models.InternalTestInfo

class TestBody(
    private val testInfo: InternalTestInfo,
    private val beforeTestActions: () -> Unit,
    private val afterTestActions: () -> Unit,
    private val mainSection: TestContext.() -> Unit
) {
    private val exceptions: MutableList<Throwable> = mutableListOf()
    private val testRunInterceptor: TestRunInterceptor =
        TestRunCompositeInterceptor(Configurator.testRunInterceptors, exceptions)

    private val stepManager = StepManager(testInfo)

    private fun runAllSections() {
        var testPassed = true

        var resultException: Throwable? = null
        try {
            testRunInterceptor.onTestStarted(testInfo)

            runBeforeTestSection()
            runMainTestSection()
        } catch (e: Throwable) {
            testPassed = false
            exceptions.add(e)
        } finally {
            try {
                runAfterTestSection()
            } catch (e: Throwable) {
                testPassed = false
                exceptions.add(e)
            } finally {
                resultException = exceptions.getException()
                stepManager.onTestFinished(resultException)
                testRunInterceptor.onTestFinished(testInfo, testPassed)
            }
        }

        resultException?.let { throw it }
    }

    private fun runBeforeTestSection() {
        try {
            testRunInterceptor.onBeforeSectionStarted(testInfo)
            beforeTestActions.invoke()
            testRunInterceptor.onBeforeSectionFinishedSuccess(testInfo)
        } catch (e: Throwable) {
            testRunInterceptor.onBeforeSectionFinishedFailed(testInfo, e)
            throw e
        }
    }

    private fun runMainTestSection() {
        try {
            testRunInterceptor.onMainSectionStarted(testInfo)
            mainSection.invoke(TestContext(stepManager))
            stepManager.onStepsFinished()
            testRunInterceptor.onMainSectionFinishedSuccess(testInfo)
        } catch (e: Throwable) {
            stepManager.onStepsFinished()
            testRunInterceptor.onMainSectionFinishedFailed(testInfo, e)
            throw e
        }
    }

    private fun runAfterTestSection() {
        try {
            testRunInterceptor.onAfterSectionStarted(testInfo)
            afterTestActions.invoke()
            testRunInterceptor.onAfterSectionFinishedSuccess(testInfo)
        } catch (e: Throwable) {
            testRunInterceptor.onAfterSectionFinishedFailed(testInfo, e)
            throw e
        }
    }

    class Builder {
        var testResult: InternalTestInfo? = null
        var beforeTestSection: (() -> Unit)? = null
        var afterTestSection: (() -> Unit)? = null
        var mainTestSection: (TestContext.() -> Unit)? = null

        fun build(): TestBody {

            return TestBody(
                requireNotNull(testResult),
                requireNotNull(beforeTestSection),
                requireNotNull(afterTestSection),
                requireNotNull(mainTestSection)
            )
        }
    }

    interface Runner {
        fun runTest(testBody: TestBody) = testBody.runAllSections()
    }
}