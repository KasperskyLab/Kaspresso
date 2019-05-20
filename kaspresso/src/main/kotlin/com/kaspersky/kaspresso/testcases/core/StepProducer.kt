package com.kaspersky.kaspresso.testcases.core

import com.kaspersky.kaspresso.testcases.models.StepInfo

interface StepProducer {
    fun produceStep(description: String): StepInfo
    fun onStepFinished(stepInfo: StepInfo, error: Throwable? = null)
}