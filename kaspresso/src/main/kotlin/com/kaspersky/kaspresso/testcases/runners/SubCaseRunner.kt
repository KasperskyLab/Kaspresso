package com.kaspersky.kaspresso.testcases.runners

import com.kaspersky.kaspresso.testcases.SubCase
import com.kaspersky.kaspresso.testcases.Scenario

/**
 * An implementation of [ScenarioRunner] for [SubCase]'s usage.
 */
class SubCaseRunner(private val title: String) : ScenarioRunner {

    /**
     * Runs [SubCase]'s [steps]. Called by [SubCase.run] with [SubCase.steps] as an argument.
     *
     * @param steps steps to run.
     */
    override fun runSteps(steps: Scenario.() -> Unit) {
        steps.invoke(
            Scenario(title) { title, description -> i("$title STEP: \"$description\"") }
        )
    }
}