package com.kaspersky.kaspresso.testcases

import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.device.screenshots.Screenshots
import com.kaspersky.kaspresso.logger.UiTestLogger

/**
 * A representation of a set of actions to invoke after the test.
 */
class AfterTestSection(private val builder: TestBody.Builder) {

    /**
     * Wraps [actions] in a lambda, that will invoke these [actions] and make screenshot if [actions] will fail when it
     * will be invoked itself, and sets this lambda as the [MainTestSection.afterTestActions] to [runner].
     *
     * @param actions actions to be wrapped and invoked after the test.
     * @return [runner] to continue building a test.
     */
    fun afterTest(actions: () -> Unit): MainTestSection {

        builder.afterSection { stepsPassed ->
            var testPassed = stepsPassed
            val logger: UiTestLogger = Configurator.logger
            val screenshots: Screenshots = Configurator.screenshots

            try {
                logger.section("AFTER TEST SECTION")
                actions.invoke()
            } catch (e: Throwable) {
                testPassed = false
                logger.section("AFTER TEST SECTION FAILED")
                screenshots.makeIfPossible("AfterTestSection_failure_${e.javaClass.simpleName}")
                throw e
            } finally {
                logger.section(if (testPassed) "TEST PASSED" else "TEST FAILED")
            }
        }
        return MainTestSection(builder)
    }
}