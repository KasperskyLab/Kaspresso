package com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.screenshot

import com.kaspersky.kaspresso.device.screenshots.ScreenshotTestStartListener
import com.kaspersky.kaspresso.device.screenshots.Screenshots
import com.kaspersky.kaspresso.interceptors.watcher.testcase.TestRunWatcherInterceptor
import com.kaspersky.kaspresso.testcases.models.info.TestInfo

/**
 * The implementation of the [TestRunWatcherInterceptor] interface.
 * Takes screenshots if "before" or "after" sections failed.
 */
class TestRunnerScreenshotWatcherInterceptor(
    private val screenshots: Screenshots
) : TestRunWatcherInterceptor {

    override fun onTestStarted(testInfo: TestInfo) {
        if (screenshots is ScreenshotTestStartListener) {
            screenshots.onTestStarted()
        }
    }

    /**
     * Takes a screenshot of the screen on which the "after" section failed.
     *
     * @param testInfo the test info to use in screenshots name.
     * @param throwable the error occurred to use in screenshots name.
     */
    override fun onAfterSectionFinishedFailed(testInfo: TestInfo, throwable: Throwable) {
        screenshots.take("AfterTestSection_failure_${throwable.javaClass.simpleName}")
    }

    /**
     * Takes a screenshot of the screen on which the "before" section failed.
     *
     * @param testInfo the test info to use in screenshots name.
     * @param throwable the error occurred to use in screenshots name.
     */
    override fun onBeforeSectionFinishedFailed(testInfo: TestInfo, throwable: Throwable) {
        screenshots.take("BeforeTestSection_failure_${throwable.javaClass.simpleName}")
    }
}