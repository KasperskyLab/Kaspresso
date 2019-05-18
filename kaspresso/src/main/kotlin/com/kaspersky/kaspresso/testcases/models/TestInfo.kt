package com.kaspersky.kaspresso.testcases.models

interface TestInfo {
    val testName: String
    val steps: List<StepInfo>
    val throwable: Throwable?
}