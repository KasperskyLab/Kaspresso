package com.kaspersky.kaspresso.testcases

import com.kaspersky.kaspresso.testcases.runners.SubCaseRunner

/**
 * A base class for all subcases. A representation of some repeating scenario inside the [TestCase].
 */
abstract class SubCase {

    /**
     * An instance of [SubCaseRunner] to run [steps] on.
     */
    private val runner = SubCaseRunner(javaClass.simpleName)

    /**
     * Steps to run. Need to be implemented in derived [SubCase].
     */
    protected abstract val steps: Scenario.() -> Unit

    /**
     * Runs [steps] on [SubCaseRunner]. Called from outside to execute prepared derived [SubCase].
     */
    fun run() = runner.runSteps(steps)
}