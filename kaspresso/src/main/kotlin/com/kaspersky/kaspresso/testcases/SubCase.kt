package com.kaspersky.kaspresso.testcases

import com.kaspersky.kaspresso.testcases.scenarios.Scenario
import com.kaspersky.kaspresso.testcases.scenarios.SubCaseScenario

/**
 * A base class for all subcases. A representation of some repeating scenario inside the [TestCase].
 */
abstract class SubCase : ScenarioRunner {

    /**
     * Steps to run. Need to be implemented in derived [SubCase].
     */
    protected abstract val steps: Scenario.() -> Unit

    /**
     * Runs [steps]. Called from outside to run prepared derived [SubCase].
     */
    fun run() = runSteps(steps)

    /**
     * An implementation of [ScenarioRunner]'s [runSteps] method. Called by [run] with [steps] as
     * an argument.
     */
    final override fun runSteps(steps: Scenario.() -> Unit) {
        steps.invoke(SubCaseScenario(javaClass.simpleName))
    }
}