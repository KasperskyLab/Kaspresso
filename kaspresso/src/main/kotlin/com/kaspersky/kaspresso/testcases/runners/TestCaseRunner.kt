package com.kaspersky.kaspresso.testcases.runners

import com.kaspersky.kaspresso.testcases.TestCase
import com.kaspersky.kaspresso.testcases.Scenario

/**
 * An implementation of [ScenarioRunner] for [TestCase]'s usage.
 */
class TestCaseRunner(private val title: String) : ScenarioRunner {

    internal lateinit var actionsBefore: () -> Unit
    internal lateinit var actionsAfter: () -> Unit

    private inline val divider
        get() = "___________________________________________________________________________\n"

    /**
     * Runs [actionsBefore], [TestCase]'s [steps] and then [actionsAfter]. [actionsAfter] are
     * invoked even if [actionsBefore] or [TestCase]'s [steps] fail.
     *
     * @param steps steps to run.
     */
    override fun runSteps(steps: Scenario.() -> Unit) {
        try {
            actionsBefore.invoke()

            val scenario = Scenario(title) { title, description ->
                "${divider}TEST STEP: \"$description\" in $title"
            }

            steps.invoke(scenario)
        } finally {
            actionsAfter.invoke()
        }
    }
}