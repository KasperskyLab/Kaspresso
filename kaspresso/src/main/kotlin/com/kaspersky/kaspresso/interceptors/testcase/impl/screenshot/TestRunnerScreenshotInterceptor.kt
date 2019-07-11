package com.kaspersky.kaspresso.interceptors.testcase.impl.screenshot

import com.kaspersky.kaspresso.device.screenshots.Screenshots
import com.kaspersky.kaspresso.interceptors.testcase.TestRunInterceptor
import com.kaspersky.kaspresso.testcases.models.info.TestInfo

class TestRunnerScreenshotInterceptor(
    private val screenshots: Screenshots
) : TestRunInterceptor {

    override fun onAfterSectionFinishedFailed(testInfo: TestInfo, throwable: Throwable) {
        screenshots.take("AfterTestSection_failure_${throwable.javaClass.simpleName}")
    }

    override fun onBeforeSectionFinishedFailed(testInfo: TestInfo, throwable: Throwable) {
        screenshots.take("BeforeTestSection_failure_${throwable.javaClass.simpleName}")
    }
}