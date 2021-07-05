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

    companion object {
        private const val ORIGINAL_SCALE = 1f
    }

    override fun takeScreenshot(file: File) {
        UiDevice.getInstance(
            InstrumentationRegistry.getInstrumentation()
        ).takeScreenshot(file, ORIGINAL_SCALE, params.quality)
    }
}
