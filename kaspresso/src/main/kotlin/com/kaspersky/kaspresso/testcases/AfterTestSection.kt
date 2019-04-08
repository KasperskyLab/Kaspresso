package com.kaspersky.kaspresso.testcases

import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.device.screenshots.Screenshots
import com.kaspersky.kaspresso.testcases.runners.TestCaseRunner

/**
 * A representation of a set of actions to invoke after the test.
 */
class AfterTestSection(private val runner: TestCaseRunner) {

    /**
     * Wraps [actions] in a lambda, that will invoke these [actions] and make screenshot if [actions] will fail when it
     * will be invoked itself, and sets this lambda as the [TestCaseRunner.afterTestActions] to [runner].
     *
     * @param actions actions to be wrapped and invoked after the test.
     * @return [runner] to continue building a test.
     */
    fun afterTest(actions: () -> Unit): TestCaseRunner {
        return runner.apply {
            afterTestActions = {
                val screenshots: Screenshots = Configurator.screenshots

                try {
                    actions.invoke()
                } catch (e: Throwable) {
                    screenshots.makeIfPossible("AfterTestSection_failure_${e.javaClass.simpleName}")
                    throw e
                }
            }
        }
    }
}
