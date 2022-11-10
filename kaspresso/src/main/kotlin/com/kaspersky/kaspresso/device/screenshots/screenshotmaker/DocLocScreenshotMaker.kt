package com.kaspersky.kaspresso.device.screenshots.screenshotmaker

import java.io.File

class DocLocScreenshotMaker(
    private val screenshotMaker: ScreenshotMaker,
    private val fullWindowScreenshotMaker: ScreenshotMaker
) : ScreenshotMaker {
    override fun takeScreenshot(file: File) {
        screenshotMaker.takeScreenshot(file)
    }

    override fun takeFullWindowScreenshot(file: File) {
        fullWindowScreenshotMaker.takeFullWindowScreenshot(file)
    }
}
