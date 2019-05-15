package com.kaspersky.kaspresso.testcases

/**
 * A base class for all subcases. A representation of some repeating scenario inside the [TestCase].
 */
abstract class SubCase {

    /**
     * An instance of [SubCaseSection] to run [steps] on.
     */
    private val runner = SubCaseSection(javaClass.simpleName)

    /**
     * Steps to run. Need to be implemented in derived [SubCase].
     */
    protected abstract val steps: Scenario.() -> Unit

    /**
     * Runs [steps] on [SubCaseSection]. Called from outside to execute prepared derived [SubCase].
     */
    fun run() = runner.runSteps(steps)
}