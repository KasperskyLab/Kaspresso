package com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.screenshot

import com.kaspersky.kaspresso.device.screenshots.Screenshots
import com.kaspersky.kaspresso.interceptors.watcher.testcase.TestRunWatcherInterceptor
import com.kaspersky.kaspresso.testcases.models.info.TestInfo

class TestRunnerScreenshotWatcherInterceptor(
    private val screenshots: Screenshots
) : TestRunWatcherInterceptor {

    override fun onAfterSectionFinishedFailed(testInfo: TestInfo, throwable: Throwable) {
        screenshots.take("AfterTestSection_failure_${throwable.javaClass.simpleName}")
    }

    override fun onBeforeSectionFinishedFailed(testInfo: TestInfo, throwable: Throwable) {
        screenshots.take("BeforeTestSection_failure_${throwable.javaClass.simpleName}")
    }
}