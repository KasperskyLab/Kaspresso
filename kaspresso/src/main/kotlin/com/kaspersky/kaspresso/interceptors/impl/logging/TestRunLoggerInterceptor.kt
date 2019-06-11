package com.kaspersky.kaspresso.interceptors.impl.logging

import com.kaspersky.kaspresso.interceptors.TestRunInterceptor
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso.testcases.models.TestInfo

class TestRunLoggerInterceptor(
    private val logger: UiTestLogger
) : TestRunInterceptor {

    override fun onBeforeSectionStarted(testInfo: TestInfo) {
        logger.section("BEFORE TEST SECTION")
    }

    override fun onBeforeSectionFinishedFailed(testInfo: TestInfo, throwable: Throwable) {
        logger.section("BEFORE TEST SECTION FAILED")
    }

    override fun onMainSectionStarted(testInfo: TestInfo) {
        logger.section("TEST SECTION")
    }

    override fun onAfterSectionStarted(testInfo: TestInfo) {
        logger.section("AFTER TEST SECTION")
    }

    override fun onAfterSectionFinishedFailed(testInfo: TestInfo, throwable: Throwable) {
        logger.section("AFTER TEST SECTION FAILED")
    }

    override fun onTestFinished(testInfo: TestInfo, success: Boolean) {
        logger.section(if (success) "TEST PASSED" else "TEST FAILED")
    }
}