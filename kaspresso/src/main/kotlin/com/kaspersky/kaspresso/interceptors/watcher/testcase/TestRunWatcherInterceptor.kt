package com.kaspersky.kaspresso.interceptors.watcher.testcase

import com.kaspersky.kaspresso.testcases.models.info.TestInfo

/**
 * The interface for all interceptors intercepting test run events.
 */
interface TestRunWatcherInterceptor : TestContextHolder {

    fun onTestStarted(testInfo: TestInfo) = Unit

    fun onBeforeSectionStarted(testInfo: TestInfo) = Unit
    fun onBeforeSectionFinishedSuccess(testInfo: TestInfo) = Unit
    fun onBeforeSectionFinishedFailed(testInfo: TestInfo, throwable: Throwable) = Unit

    fun onMainSectionStarted(testInfo: TestInfo) = Unit
    fun onMainSectionFinishedSuccess(testInfo: TestInfo) = Unit
    fun onMainSectionFinishedFailed(testInfo: TestInfo, throwable: Throwable) = Unit

    fun onAfterSectionStarted(testInfo: TestInfo) = Unit
    fun onAfterSectionFinishedSuccess(testInfo: TestInfo) = Unit
    fun onAfterSectionFinishedFailed(testInfo: TestInfo, throwable: Throwable) = Unit

    fun onTestFinished(testInfo: TestInfo, success: Boolean) = Unit
}
