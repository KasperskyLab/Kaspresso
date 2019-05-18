package com.kaspersky.kaspresso.testcases.core

import com.kaspersky.kaspresso.testcases.models.StepInfo

interface StepProducer {
    fun produceStepInfo(description: String): StepInfo
    fun onStepFinished(stepInfo: StepInfo, error: Throwable? = null)
}