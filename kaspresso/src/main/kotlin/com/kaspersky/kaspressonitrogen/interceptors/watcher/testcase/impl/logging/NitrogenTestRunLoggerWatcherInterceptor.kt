package com.kaspersky.kaspressonitrogen.interceptors.watcher.testcase.impl.logging

import com.kaspersky.kaspresso.interceptors.watcher.testcase.NitrogenTestRunWatcherInterceptor
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso.testcases.models.info.TestInfo

class NitrogenTestRunLoggerWatcherInterceptor(
        private val logger: UiTestLogger
) : NitrogenTestRunWatcherInterceptor {

    /**
     * Logs the given [testInfo] on "before" section starts.
     *
     * @param testInfo the test info to log.
     */
    override fun onBeforeSectionStarted(testInfo: TestInfo) {
        logger.section("BEFORE TEST SECTION")
    }

    /**
     * Logs the given [testInfo] on "before" section finishes with failure.
     *
     * @param testInfo the test info to log.
     * @param throwable the error occurred to log.
     */
    override fun onBeforeSectionFinishedFailed(testInfo: TestInfo, throwable: Throwable) {
        logger.section("BEFORE TEST SECTION FAILED")
    }

    /**
     * Logs the given [testInfo] on "main" section starts.
     *
     * @param testInfo the test info to log.
     */
    override fun onMainSectionStarted(testInfo: TestInfo) {
        logger.section("TEST SECTION")
    }

    /**
     * Logs the given [testInfo] on "after" section starts.
     *
     * @param testInfo the test info to log.
     */
    override fun onAfterSectionStarted(testInfo: TestInfo) {
        logger.section("AFTER TEST SECTION")
    }

    /**
     * Logs the given [testInfo] on "after" section finishes with failure.
     *
     * @param testInfo the test info to log.
     * @param throwable the error occurred to log.
     */
    override fun onAfterSectionFinishedFailed(testInfo: TestInfo, throwable: Throwable) {
        logger.section("AFTER TEST SECTION FAILED")
    }

    /**
     * Logs the given [testInfo] on whole test finishes.
     *
     * @param testInfo the test info to log.
     * @param success the while test was finished successfully or not.
     */
    override fun onTestFinished(testInfo: TestInfo, success: Boolean) {
        logger.section(if (success) "TEST PASSED" else "TEST FAILED")
    }
}
