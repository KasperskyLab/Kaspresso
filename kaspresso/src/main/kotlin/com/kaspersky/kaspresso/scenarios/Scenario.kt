package com.kaspersky.kaspresso.scenarios

import com.kaspersky.kaspresso.scenarios.stepsrunners.StepsRunner

/**
 *  An abstract class for all scenarios.
 */
abstract class Scenario<T : StepsRunner>(
    private val stepsRunnerFactory: (testCaseName: String) -> T
) {
    /**
     * Resets the steps counter and runs steps on the [stepsRunner].
     *
     * @param steps steps to run.
     */
    protected fun runSteps(steps: T.() -> Unit) {
        val stepsRunner = stepsRunnerFactory(javaClass.simpleName)
        stepsRunner.stepsCounter = 0
        steps.invoke(stepsRunner)
        stepsRunner.checkAfter()
    }

}
