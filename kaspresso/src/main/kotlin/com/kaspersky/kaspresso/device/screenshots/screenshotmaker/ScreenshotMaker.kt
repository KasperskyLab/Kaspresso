package com.kaspersky.kaspresso.device.screenshots.screenshotmaker

import java.io.File

/**
 * Creates and saves a screenshot
 */
interface ScreenshotMaker {

    fun takeScreenshot(file: File)

    fun takeFullWindowScreenshot(file: File)
}
