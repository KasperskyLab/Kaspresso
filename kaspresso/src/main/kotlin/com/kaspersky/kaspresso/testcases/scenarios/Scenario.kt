package com.kaspersky.kaspresso.testcases.scenarios

/**
 * An abstract class for all scenarios.
 */
abstract class Scenario(protected val title: String) {

    /**
     * A step counter to evaluate current step's tag.
     */
    protected var stepsCounter: Int = 0

    /**
     * A representation of a [Scenario]'s step.
     *
     * @param description a description of a step.
     * @param actions a set of actions of a step.
     */
    abstract fun step(description: String, actions: () -> Unit)
}