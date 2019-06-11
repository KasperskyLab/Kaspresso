package com.kaspersky.kaspresso.testcases.models

internal data class MainTestSectionResult(
    val testInfo: TestInfo,
    val throwable: Throwable? = null
)