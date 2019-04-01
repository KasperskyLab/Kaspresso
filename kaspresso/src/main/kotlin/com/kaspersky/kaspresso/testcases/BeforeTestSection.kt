package com.kaspersky.kaspresso.testcases

import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.device.screenshots.Screenshots
import com.kaspersky.kaspresso.testcases.runners.TestCaseRunner

/**
 * A representation of a set of actions to be invoked before the test.
 */
class BeforeTestSection(private val runner: TestCaseRunner) {

    /**
     * Wraps [actions] in a lambda, that will invoke these [actions] and make screenshot if [actions] will fail when it
     * will be invoked itself, and sets this lambda as the [TestCaseRunner.beforeTestActions] to [runner].
     *
     * @param actions actions to be wrapped and invoked before the test.
     */
    fun beforeTest(actions: () -> Unit) {
        runner.apply {
            beforeTestActions = {
                val screenshots: Screenshots = Configurator.screenshots

                try {
                    actions.invoke()
                } catch (e: Throwable) {
                    screenshots.makeIfPossible("BeforeTestSection_failure_${e.javaClass.simpleName}")
                    throw e
                }
            }
        }
    }
}

