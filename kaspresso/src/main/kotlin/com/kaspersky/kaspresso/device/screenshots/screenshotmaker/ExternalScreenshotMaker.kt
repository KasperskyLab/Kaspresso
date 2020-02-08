package com.kaspersky.kaspresso.device.screenshots.screenshotmaker

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import java.io.File

/**
 * The class for capturing spoon-compatible screenshots by uiautomator.
 */
class ExternalScreenshotMaker: ScreenshotMaker {

    override fun takeScreenshot(file: File) {
        UiDevice.getInstance(InstrumentationRegistry.getInstrumentation()).takeScreenshot(file)
    }
}