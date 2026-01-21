package com.kaspersky.kaspresso.testcases.models.info

data class TestInfo(
    val testName: String,
    val stepInfos: List<StepInfo> = listOf(),
    val throwable: Throwable? = null
)
