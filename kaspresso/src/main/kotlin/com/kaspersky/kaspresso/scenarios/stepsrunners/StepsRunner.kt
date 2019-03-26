package com.kaspersky.kaspresso.scenarios.stepsrunners

/**
 * An abstract class for all steps runners.
 */
abstract class StepsRunner(protected val testCaseName: String) {

    /**
     * A step counter to evaluate current step's tag.
     */
    internal var stepsCounter: Int = 0

    /**
     * A representation of a [Scenario]'s step.
     *
     * @param description a description of a step.
     * @param actions a set of actions of a step.
     */
    abstract fun step(description: String, actions: () -> Unit)

    /**
     * A special method to check correct finishing of test
     */
    internal open fun checkAfter() {
        // empty by default
    }

}
