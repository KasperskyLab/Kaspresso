package com.kaspersky.kaspresso.scenarios

/**
 *  An abstract class for all scenarios.
 */
abstract class Scenario<T : Scenario.StepsRunner>(
    private val stepsRunner: T
) {
    /**
     * Resets the steps counter and runs steps on the [stepsRunner].
     *
     * @param steps steps to run.
     */
    protected fun runSteps(steps: T.() -> Unit) {
        stepsRunner.stepsCounter = 0
        steps.invoke(stepsRunner)
    }

    /**
     * An abstract class for all steps runners.
     */
    abstract class StepsRunner {

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
    }
}