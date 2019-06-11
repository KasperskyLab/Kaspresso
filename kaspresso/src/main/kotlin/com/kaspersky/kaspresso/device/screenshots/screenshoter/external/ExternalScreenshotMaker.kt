package com.kaspersky.kaspresso.device.screenshots.screenshoter.external

import android.support.test.InstrumentationRegistry
import android.support.test.uiautomator.UiDevice
import com.kaspersky.kaspresso.device.screenshots.screenshoter.ScreenshotFiles
import java.io.File

/**
 * Class for capturing spoon-compatible screenshots by uiautomator.
 */
internal class ExternalScreenshotMaker(
    screenshotDir: File
) {

    private val screenshotFiles = ScreenshotFiles(screenshotDir)

    /**
     * Takes a screenshot with the specified tag.
     *
     * @param tag Unique tag to further identify the screenshot. Must match [a-zA-Z0-9_-]+.
     * @return the image file that was created
     */
    fun screenshot(tag: String): File {
        val screenshotFile = screenshotFiles.obtainScreenshotFile(
            InstrumentationRegistry.getTargetContext().applicationContext,
            tag
        )
        takeScreenshot(screenshotFile)
        return screenshotFile
    }

    private fun takeScreenshot(file: File) {
        UiDevice.getInstance(InstrumentationRegistry.getInstrumentation()).takeScreenshot(file)
    }
}
