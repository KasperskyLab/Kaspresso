package com.kaspersky.kaspresso.testcases

import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.interceptors.TestRunInterceptor
import com.kaspersky.kaspresso.interceptors.impl.composite.TestRunCompositeInterceptor

class TestBody(
    private val title: String,
    private val beforeTestActions: () -> Unit,
    private val afterTestActions: () -> Unit,
    private val mainSection: Scenario.() -> Unit
) {

    private val exceptions: MutableList<Throwable> = mutableListOf()
    private val testRunInterceptor: TestRunInterceptor = TestRunCompositeInterceptor(Configurator.testRunInterceptors, exceptions)

    companion object {
        fun builder(): Builder =
            Builder()
    }

    fun run() {
        testRunInterceptor.onTestStarted(this)
        var stepsPassed = true

        try {
            runBeforeTestSection()
            testRunInterceptor.onMainSectionStarted(this)
            mainSection.invoke(Scenario(title))
            testRunInterceptor.onMainSectionFinishedSuccess(this)
        } catch (e: Throwable) {
            testRunInterceptor.onMainSectionFinishedFailed(this, e)
            stepsPassed = false
            throw e
        } finally {
            runAfterTestSection(stepsPassed)
        }
    }

    private fun runAfterTestSection(stepsPassed: Boolean) {
        testRunInterceptor.onAfterSectionStarted(this)
        var testPassed = stepsPassed

        try {
            afterTestActions.invoke()
            testRunInterceptor.onAfterSectionFinishedSuccess(this)
        } catch (e: Throwable) {
            testRunInterceptor.onAfterSectionFinishedFailed(this, e)
            testPassed = false
            throw e
        } finally {
            testRunInterceptor.onTestFinished(this, testPassed)
        }
    }

    private fun runBeforeTestSection() {

        testRunInterceptor.onBeforeSectionStarted(this)

        try {
            beforeTestActions.invoke()
            testRunInterceptor.onBeforeSectionFinishedSuccess(this)
        } catch (e: Throwable) {
            testRunInterceptor.onBeforeSectionFinishedFailed(this, e)
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
                className!!,
                beforeTestSection!!,
                afterTestSection!!,
                mainSection!!
            )
        }
    }
}