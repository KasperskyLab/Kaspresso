package com.kaspersky.kaspresso.testcases.models.info

import com.kaspersky.kaspresso.testcases.models.TestIdentifier

data class TestInfo internal constructor(
    val testIdentifier: TestIdentifier,
    val stepInfos: List<StepInfo> = listOf(),
    val throwable: Throwable? = null
)
