package com.kaspersky.kaspresso.docloc.rule

import com.kaspersky.kaspresso.docloc.DocLocScreenshotCapturer
import org.junit.rules.TestWatcher
import org.junit.runner.Description

/**
 * The test rule to capture a screenshot in case of unexpected docloc screenshot test failure.
 */
class TestFailRule internal constructor() : TestWatcher() {

    internal lateinit var screenshotCapturer: DocLocScreenshotCapturer

    override fun failed(throwable: Throwable?, description: Description?) {
        val screenshotName = "${description!!.className}_${description.methodName}".replace(".", "_")
        screenshotCapturer.captureScreenshotOnFail(screenshotName)
    }
}
