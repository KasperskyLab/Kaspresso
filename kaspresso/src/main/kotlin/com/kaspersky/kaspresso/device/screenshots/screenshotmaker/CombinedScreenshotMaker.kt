package com.kaspersky.kaspresso.device.screenshots.screenshotmaker

import java.io.File

class CombinedScreenshotMaker(
    private val preferredScreenshotMaker: ScreenshotMaker,
    private val fallbackScreenshotMaker: ScreenshotMaker
) : ScreenshotMaker {

    override fun takeScreenshot(file: File) {
        runCatching {
            preferredScreenshotMaker.takeScreenshot(file)
        }.onFailure {
            fallbackScreenshotMaker.takeScreenshot(file)
        }
    }
}