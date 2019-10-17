package com.kaspersky.kaspresso.interceptors.watcher.testcase

import com.kaspersky.kaspresso.testcases.core.testcontext.BaseTestContext
import com.kaspersky.kaspresso.testcases.models.info.TestInfo

/**
 * The interface for all interceptors intercepting test run events.
 */
interface TestRunWatcherInterceptor {

    fun BaseTestContext.onTestStarted(testInfo: TestInfo) = Unit

    fun BaseTestContext.onBeforeSectionStarted(testInfo: TestInfo) = Unit
    fun BaseTestContext.onBeforeSectionFinishedSuccess(testInfo: TestInfo) = Unit
    fun BaseTestContext.onBeforeSectionFinishedFailed(testInfo: TestInfo, throwable: Throwable) = Unit

    fun BaseTestContext.onMainSectionStarted(testInfo: TestInfo) = Unit
    fun BaseTestContext.onMainSectionFinishedSuccess(testInfo: TestInfo) = Unit
    fun BaseTestContext.onMainSectionFinishedFailed(testInfo: TestInfo, throwable: Throwable) = Unit

    fun BaseTestContext.onAfterSectionStarted(testInfo: TestInfo) = Unit
    fun BaseTestContext.onAfterSectionFinishedSuccess(testInfo: TestInfo) = Unit
    fun BaseTestContext.onAfterSectionFinishedFailed(testInfo: TestInfo, throwable: Throwable) = Unit

    fun BaseTestContext.onTestFinished(testInfo: TestInfo, success: Boolean) = Unit
}