package com.kaspersky.kaspresso.testcases.runners

import com.kaspersky.kaspresso.testcases.TestCase
import com.kaspersky.kaspresso.testcases.Scenario

/**
 * An implementation of [ScenarioRunner] for [TestCase]'s usage.
 */
class TestCaseRunner(private val title: String) : ScenarioRunner {

    internal lateinit var beforeTestActions: () -> Unit
    internal lateinit var afterTestActions: () -> Unit

    /**
     * Runs [beforeTestActions], [TestCase]'s [steps] and then runs [afterTestActions]. [afterTestActions] are invoked
     * even if [beforeTestActions] or [TestCase]'s [steps] fail.
     *
     * @param steps steps to run.
     */
    override fun runSteps(steps: Scenario.() -> Unit) {
        try {
            beforeTestActions.invoke()

            steps.invoke(
                Scenario(title) { title, description ->
                    i("___________________________________________________________________________")
                    i("TEST STEP: \"$description\" in $title")
                }
            )
        } finally {
            afterTestActions.invoke()
        }
    }
}