package com.kaspersky.kaspresso.testcases.core.step

import com.kaspersky.kaspresso.testcases.models.info.StepInfo

/**
 * The interface to provide step info producing functionality.
 */
internal interface StepInfoProducer {

    /**
     * Produces correct step info. Only this function may produce a StepInfo!
     *
     * @param description what happens on this step.
     * @return [StepInfo] of this step. Info may be changed internally but framework users can not mutate it.
     */
    fun produceStepInfo(description: String): StepInfo

    /**
     * A callback function. It should be called after every step finished.
     *
     * @param stepInfo that this [StepInfoProducer] produces early.
     * @param error throwable witch happens on step or null.
     */
    fun onStepFinished(stepInfo: StepInfo, error: Throwable? = null)
}
