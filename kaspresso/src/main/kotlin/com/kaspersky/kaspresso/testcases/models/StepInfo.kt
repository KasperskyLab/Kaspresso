package com.kaspersky.kaspresso.testcases.models

interface StepInfo {
    val description: String
    val testClassName: String
    val level: Int
    val number: String
    val ordinal: Int
    val subSteps: List<StepInfo>
    val status: StepStatus
    val throwable: Throwable?
}