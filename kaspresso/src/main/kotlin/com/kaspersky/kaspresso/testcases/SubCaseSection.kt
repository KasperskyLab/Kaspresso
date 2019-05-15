package com.kaspersky.kaspresso.testcases

/**
 * An implementation of [ScenarioRunner] for [SubCase]'s usage.
 */
class SubCaseSection(private val title: String) : ScenarioRunner {

    /**
     * Runs [SubCase]'s [steps]. Called by [SubCase.run] with [SubCase.steps] as an argument.
     *
     * @param steps steps to run.
     */
    override fun runSteps(steps: Scenario.() -> Unit) {
        steps.invoke(Scenario(title))
    }
}