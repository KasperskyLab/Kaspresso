package com.kaspersky.kaspresso.testcases.models

import com.kaspersky.kaspresso.testcases.models.info.TestInfo

internal data class MainTestSectionResult(
    val testInfo: TestInfo,
    val throwable: Throwable? = null
)
