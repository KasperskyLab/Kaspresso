package com.kaspersky.kaspresso.testcases.models

internal data class RunMainTestSectionResult(
    val testInfo: TestInfo,
    val throwable: Throwable? = null
)