package com.kaspersky.kaspresso.interceptors.impl.screenshot

import com.kaspersky.kaspresso.device.screenshots.Screenshots
import com.kaspersky.kaspresso.interceptors.TestRunInterceptor
import com.kaspersky.kaspresso.testcases.TestBody

class TestRunnerScreenshotInterceptor(private val screenshots: Screenshots) :
    TestRunInterceptor {
    override fun onAfterSectionFinishedFailed(testBody: TestBody, throwable: Throwable) {
        screenshots.makeIfPossible("AfterTestSection_failure_${throwable.javaClass.simpleName}")
    }

    override fun onBeforeSectionFinishedFailed(testBody: TestBody, throwable: Throwable) {
        screenshots.makeIfPossible("BeforeTestSection_failure_${throwable.javaClass.simpleName}")
    }
}