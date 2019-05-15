package com.kaspersky.kaspresso.testcases

import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.device.screenshots.Screenshots
import com.kaspersky.kaspresso.logger.UiTestLogger

/**
 * A representation of a set of actions to be invoked before the test.
 */
class BeforeTestSection(private val builder: TestBody.Builder) {

    /**
     * Wraps [actions] in a lambda, that will invoke these [actions] and make screenshot if [actions] will fail when it
     * will be invoked itself, and sets this lambda as the [MainTestSection.beforeTestActions] to [runner].
     *
     * @param actions actions to be wrapped and invoked before the test.
     */
    fun beforeTest(actions: () -> Unit): AfterTestSection {

        builder.beforeSection {
            val logger: UiTestLogger = Configurator.logger
            val screenshots: Screenshots = Configurator.screenshots

            try {
                logger.section("BEFORE TEST SECTION")
                actions.invoke()
                logger.section("TEST SECTION")
            } catch (e: Throwable) {
                logger.section("BEFORE TEST SECTION FAILED")
                screenshots.makeIfPossible("BeforeTestSection_failure_${e.javaClass.simpleName}")
                throw e
            }
        }

        return AfterTestSection(builder)
    }
}