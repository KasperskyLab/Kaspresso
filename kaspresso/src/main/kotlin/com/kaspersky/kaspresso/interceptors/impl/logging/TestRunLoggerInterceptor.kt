package com.kaspersky.kaspresso.interceptors.impl.logging

import com.kaspersky.kaspresso.interceptors.TestRunInterceptor
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso.testcases.TestBody

class TestRunLoggerInterceptor(private val logger: UiTestLogger) : TestRunInterceptor {

    override fun onBeforeSectionStarted(testBody: TestBody) {
        logger.section("BEFORE TEST SECTION")
    }

    override fun onBeforeSectionFinishedFailed(testBody: TestBody, throwable: Throwable) {
        logger.section("BEFORE TEST SECTION FAILED")
    }

    override fun onMainSectionStarted(testBody: TestBody) {
        logger.section("TEST SECTION")
    }

    override fun onAfterSectionStarted(testBody: TestBody) {
        logger.section("AFTER TEST SECTION")
    }

    override fun onAfterSectionFinishedFailed(testBody: TestBody, throwable: Throwable) {
        logger.section("AFTER TEST SECTION FAILED")
    }

    override fun onTestFinished(testBody: TestBody, success: Boolean) {
        logger.section(if (success) "TEST PASSED" else "TEST FAILED")
    }
}