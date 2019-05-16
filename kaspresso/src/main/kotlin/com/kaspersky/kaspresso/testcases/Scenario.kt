package com.kaspersky.kaspresso.testcases

import com.kaspersky.kaspresso.configurator.Configurator

/**
 * A representation of a sequence of test's actions.
 */
class Scenario(
    private val testInfo: TestInfo
) {
    /**
     * A step counter to evaluate current step's tag.
     */
    private var stepsCounter: Int = 0

    /**
     * A representation of a [Scenario]'s step.
     *
     * @param description a description of a step.
     * @param actions a set of actions of a step.
     */

    fun step(description: String, action: () -> Unit) {
        Step(
            action = action,
            stepInfo = StepInfo(
                description = description,
                testClassName = testInfo.testName,
                level = 0, //TODO calculate
                orderOnLevel = 0, //TODO calculate
                ordinal = ++stepsCounter
            ),
            interceptors = Configurator.stepInterceptors
        ).proceed()
    }
}