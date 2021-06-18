package com.kaspersky.kaspresso.device.screenshots.screenshotmaker

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import com.kaspersky.kaspresso.params.ScreenshotParams
import java.io.File

/**
 * Captures spoon-compatible screenshots by uiautomator.
 */
class ExternalScreenshotMaker(
    private val params: ScreenshotParams = ScreenshotParams()
) : ScreenshotMaker {

    override fun takeScreenshot(file: File) {
        UiDevice.getInstance(
            InstrumentationRegistry.getInstrumentation()
        ).takeScreenshot(file, 1.0f, params.quality)
    }
}
