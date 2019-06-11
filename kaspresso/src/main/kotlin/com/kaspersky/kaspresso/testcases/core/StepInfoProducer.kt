package com.kaspersky.kaspresso.testcases.core

import com.kaspersky.kaspresso.testcases.models.StepInfo

interface StepInfoProducer {

    /**
     * Produce correct step info. Only this function may produce a StepInfo!
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