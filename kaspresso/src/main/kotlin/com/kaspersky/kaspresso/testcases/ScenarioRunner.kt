package com.kaspersky.kaspresso.testcases

import com.kaspersky.kaspresso.testcases.scenarios.Scenario

/**
 * An interface to run steps of a [Scenario].
 */
interface ScenarioRunner {

    /**
     * Runs the [Scenario]'s steps.
     *
     * @param steps the steps to run.
     */
    fun runSteps(steps: Scenario.() -> Unit)
}