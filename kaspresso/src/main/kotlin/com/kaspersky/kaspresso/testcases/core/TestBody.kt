package com.kaspersky.kaspresso.testcases.core

import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.extensions.other.throwAll
import com.kaspersky.kaspresso.interceptors.TestRunInterceptor
import com.kaspersky.kaspresso.interceptors.impl.composite.TestRunCompositeInterceptor
import com.kaspersky.kaspresso.testcases.models.TestInfo

class TestBody(
    private val testInfo: TestInfo,
    private val beforeTestActions: () -> Unit,
    private val afterTestActions: () -> Unit,
    private val mainSection: TestContext.() -> Unit
) {
    private val exceptions: MutableList<Throwable> = mutableListOf()
    private val testRunInterceptor: TestRunInterceptor =
        TestRunCompositeInterceptor(Configurator.testRunInterceptors, exceptions)

    private fun runAllSections() {
        var testPassed = true

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
                testRunInterceptor.onTestFinished(testInfo, testPassed)
            }
        }

        exceptions.throwAll()
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
            mainSection.invoke(TestContext(testInfo))
            testRunInterceptor.onMainSectionFinishedSuccess(testInfo)
        } catch (e: Throwable) {
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
        var testInfo: TestInfo? = null
        var beforeTestSection: (() -> Unit)? = null
        var afterTestSection: (() -> Unit)? = null
        var mainTestSection: (TestContext.() -> Unit)? = null

        fun build(): TestBody {
            if (testInfo == null || beforeTestSection == null || afterTestSection == null || mainTestSection == null)
                throw IllegalArgumentException("Check all section filled")

            return TestBody(
                testInfo!!,
                beforeTestSection!!,
                afterTestSection!!,
                mainTestSection!!
            )
        }
    }

    interface Runner {
        fun runTest(testBody: TestBody) = testBody.runAllSections()
    }
}