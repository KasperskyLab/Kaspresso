package com.kaspersky.kaspresso.testcases.api.testcase

import leakcanary.DetectLeaksAfterTestSuccess
import org.junit.Rule

abstract class DetectMemoryLeaksTestCase : TestCase() {
    @get:Rule
    val detectMemoryLeaksRule = DetectLeaksAfterTestSuccess()
}
