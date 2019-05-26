package com.kaspersky.kaspresso.testcases.models

data class TestInfo(
    val testName: String,
    val steps: List<StepInfo> = listOf(),
    val throwable: Throwable? = null
)

