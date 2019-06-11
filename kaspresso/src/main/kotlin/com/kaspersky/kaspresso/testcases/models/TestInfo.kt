package com.kaspersky.kaspresso.testcases.models

internal data class TestInfo(
    val testName: String,
    val stepInfos: List<StepInfo> = listOf(),
    val throwable: Throwable? = null
)