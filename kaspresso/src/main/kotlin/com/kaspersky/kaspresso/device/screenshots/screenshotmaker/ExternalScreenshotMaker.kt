package com.kaspersky.kaspresso.device.screenshots.screenshotmaker

import androidx.test.uiautomator.UiDevice
import com.kaspersky.kaspresso.instrumental.InstrumentalDepsAssistant
import com.kaspersky.kaspresso.params.ScreenshotParams
import java.io.File

/**
 * Captures spoon-compatible screenshots by uiautomator.
 */
class ExternalScreenshotMaker(
    private val instrumentalDepsAssistant: InstrumentalDepsAssistant,
    private val params: ScreenshotParams = ScreenshotParams()
) : ScreenshotMaker {

    private val device: UiDevice
        get() = instrumentalDepsAssistant.uiDevice

    // Somehow scale param is not used in UiDevice#takeScreenshot method,
    // so just using default here
    private val scale: Float = 1.0f

    override fun takeScreenshot(file: File) {
        device.takeScreenshot(file, scale, params.quality)
    }
}
