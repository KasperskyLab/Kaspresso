package com.kaspersky.kaspresso.testcases

import com.kaspersky.kaspresso.testcases.Scenario

/**
 * An interface to run [Scenario]'s steps.
 */
interface ScenarioRunner {

    /**
     * Runs the [Scenario]'s [steps].
     *
     * @param steps steps to run.
     */
    fun runSteps(steps: Scenario.() -> Unit)
}