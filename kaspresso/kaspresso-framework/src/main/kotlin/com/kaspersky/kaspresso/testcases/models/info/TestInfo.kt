package com.kaspersky.kaspresso.testcases.models.info

data class TestInfo internal constructor(
    val testName: String,
    val stepInfos: List<StepInfo> = listOf(),
    val throwable: Throwable? = null
)
