package com.kaspersky.components.alluresupport.interceptors.testrun

class ScreenshotTestInterceptor(
    private val screenshots: Screenshots
) : TestRunWatcherInterceptor {

    override fun onBeforeSectionFinishedFailed(testInfo: TestInfo, throwable: Throwable) {
        onSectionFailed(makeTag("BeforeTestSection", throwable))
    }

    override fun onAfterSectionFinishedFailed(testInfo: TestInfo, throwable: Throwable) {
        onSectionFailed(makeTag("AfterTestSection", throwable))
    }

    private fun onSectionFailed(tag: String) {
        screenshots.takeAndApply(tag) { attachScreenshotToAllureReport() }
    }

    private fun makeTag(section: String, throwable: Throwable): String =
        "Screenshot_${section}_failure_${throwable.javaClass.simpleName}"
}
