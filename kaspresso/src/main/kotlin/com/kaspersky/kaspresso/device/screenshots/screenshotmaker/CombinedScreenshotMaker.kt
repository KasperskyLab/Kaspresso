package com.kaspersky.kaspresso.device.screenshots.screenshotmaker

import java.io.File

/**
 * Calls [preferredScreenshotMaker] and fallbacks to [fallbackScreenshotMaker] on fail
 */
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

    override fun takeFullScreenshot(file: File) {
        runCatching {
            preferredScreenshotMaker.takeFullScreenshot(file)
        }.onFailure {
            fallbackScreenshotMaker.takeFullScreenshot(file)
        }
    }
}
