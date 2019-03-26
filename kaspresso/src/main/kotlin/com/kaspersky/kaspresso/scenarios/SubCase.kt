package com.kaspersky.kaspresso.scenarios

import com.kaspersky.kaspresso.scenarios.stepsrunners.SubCaseStepsRunner

/**
 * A base class for all subcases.
 */
abstract class SubCase {

    /**
     * Resets the steps counter and runs steps on the [stepsRunner].
     *
     * @param steps steps to run.
     */
    protected open fun runSteps(steps: SubCaseStepsRunner.() -> Unit) {
        val stepsRunner = SubCaseStepsRunner(javaClass.simpleName)
        stepsRunner.stepsCounter = 0
        steps.invoke(stepsRunner)
    }

}
