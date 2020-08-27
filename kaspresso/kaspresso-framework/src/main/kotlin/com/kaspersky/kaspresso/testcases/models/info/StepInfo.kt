package com.kaspersky.kaspresso.testcases.models.info

import com.kaspersky.kaspresso.testcases.models.StepStatus
import com.kaspersky.kaspresso.testcases.models.TestIdentifier

interface StepInfo {
    val description: String
    val testIdentifier: TestIdentifier
    val number: String?
    val ordinal: Int
    val subSteps: List<StepInfo>
    val status: StepStatus
    val throwable: Throwable?
    val startTime: Long
    val stopTime: Long
}
