package com.kaspersky.kaspresso.testcases

import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.device.screenshots.Screenshots
import com.kaspersky.kaspresso.logger.UiTestLogger

class TestBody(
    private val title: String,
    private val beforeTestActions: () -> Unit,
    private val afterTestActions: () -> Unit,
    private val mainSection: Scenario.() -> Unit
) {

    companion object {
        fun builder(): Builder =
            Builder()
    }

    fun run() {
        var stepsPassed = true

        try {
            runBeforeTestSection()
            mainSection.invoke(Scenario(title))
        } catch (e: Throwable) {
            stepsPassed = false
            throw e
        } finally {
            runAfterTestSection(stepsPassed)
        }
    }

    private fun runAfterTestSection(stepsPassed: Boolean) {

        var testPassed = stepsPassed
        val logger: UiTestLogger = Configurator.logger
        val screenshots: Screenshots = Configurator.screenshots

        try {
            logger.section("AFTER TEST SECTION")
            afterTestActions.invoke()
        } catch (e: Throwable) {
            testPassed = false
            logger.section("AFTER TEST SECTION FAILED")
            screenshots.makeIfPossible("AfterTestSection_failure_${e.javaClass.simpleName}")
            throw e
        } finally {
            logger.section(if (testPassed) "TEST PASSED" else "TEST FAILED")
        }
    }

    private fun runBeforeTestSection() {

        val logger: UiTestLogger = Configurator.logger
        val screenshots: Screenshots = Configurator.screenshots

        try {
            logger.section("BEFORE TEST SECTION")
            beforeTestActions.invoke()
            logger.section("TEST SECTION")
        } catch (e: Throwable) {
            logger.section("BEFORE TEST SECTION FAILED")
            screenshots.makeIfPossible("BeforeTestSection_failure_${e.javaClass.simpleName}")
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