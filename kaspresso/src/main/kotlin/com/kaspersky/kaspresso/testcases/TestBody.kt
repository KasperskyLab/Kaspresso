package com.kaspersky.kaspresso.testcases

import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.extensions.other.throwAll
import com.kaspersky.kaspresso.interceptors.TestRunInterceptor
import com.kaspersky.kaspresso.interceptors.impl.composite.TestRunCompositeInterceptor

class TestBody(
    private val testInfo: TestInfo,
    private val beforeTestActions: () -> Unit,
    private val afterTestActions: () -> Unit,
    private val mainSection: Scenario.() -> Unit
) {

    private val exceptions: MutableList<Throwable> = mutableListOf()
    private val testRunInterceptor: TestRunInterceptor =
        TestRunCompositeInterceptor(Configurator.testRunInterceptors, exceptions)

    companion object {
        fun builder(): Builder =
            Builder()
    }

    fun run() {
        testRunInterceptor.onTestStarted(testInfo)
        var stepsPassed = true

        try {
            runBeforeTestSection()
            testRunInterceptor.onMainSectionStarted(testInfo)
            mainSection.invoke(Scenario(testInfo))
            testRunInterceptor.onMainSectionFinishedSuccess(testInfo)
        } catch (e: Throwable) {
            testRunInterceptor.onMainSectionFinishedFailed(testInfo, e)
            stepsPassed = false
            exceptions.add(e)
        } finally {
            runAfterTestSection(stepsPassed)
        }
        exceptions.throwAll()
    }

    private fun runAfterTestSection(stepsPassed: Boolean) {
        testRunInterceptor.onAfterSectionStarted(testInfo)
        var testPassed = stepsPassed

        try {
            afterTestActions.invoke()
            testRunInterceptor.onAfterSectionFinishedSuccess(testInfo)
        } catch (e: Throwable) {
            testRunInterceptor.onAfterSectionFinishedFailed(testInfo, e)
            testPassed = false
            throw e
        } finally {
            testRunInterceptor.onTestFinished(testInfo, testPassed)
        }
    }

    private fun runBeforeTestSection() {

        testRunInterceptor.onBeforeSectionStarted(testInfo)

        try {
            beforeTestActions.invoke()
            testRunInterceptor.onBeforeSectionFinishedSuccess(testInfo)
        } catch (e: Throwable) {
            testRunInterceptor.onBeforeSectionFinishedFailed(testInfo, e)
            throw e
        }
    }

    class Builder {
        private var beforeTestSection: (() -> Unit)? = null
        private var afterTestSection: (() -> Unit)? = null
        private var mainSection: (Scenario.() -> Unit)? = null
        private var className: String? = null

        fun beforeSection(action: () -> Unit): Builder {
            beforeTestSection = action
            return this
        }

        fun afterSection(action: () -> Unit): Builder {
            afterTestSection = action
            return this
        }

        fun mainSection(action: Scenario.() -> Unit): Builder {
            mainSection = action
            return this
        }

        fun className(name: String): Builder {
            className = name
            return this
        }


        fun build(): TestBody {
            if (beforeTestSection == null || afterTestSection == null || mainSection == null || className == null)
                throw IllegalArgumentException("Check all section filled")

            return TestBody(
                TestInfo(className!!),
                beforeTestSection!!,
                afterTestSection!!,
                mainSection!!
            )
        }
    }
}