package com.kaspersky.kaspresso.device.screenshots.docloc

import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.device.Device
import com.kaspersky.kaspresso.device.activities.metadata.ActivityMetadata
import com.kaspersky.kaspresso.extensions.other.safeWrite
import com.kaspersky.kaspresso.extensions.other.toXml
import com.kaspersky.kaspresso.extensions.spoonext.UiAutomatorSpoon
import com.kaspersky.kaspresso.flakysafety.wait
import java.io.File

/**
 *  Class for saving and clearing docloc screenshots with metadata.
 *  @param screenshotsDir directory to save screenshots and metadata
 */
internal class DocLocScreenshotCapturer(private val screenshotsDir: File) {

    companion object {

        private const val SCREENSHOT_CAPTURE_DELAY_MS: Long = 500
    }

    private val logger = Configurator.logger

    private val screenshotDirOnFailed = screenshotsDir.resolve("fails")

    private val screenshoter = UiAutomatorSpoon(screenshotsDir)
    private val failScreenshoter = UiAutomatorSpoon(screenshotDirOnFailed)

    /**
     *  Capture screenshot and save it with metadata to [screenshotsDir]
     *  @param screenshotName name of screenshot. Must match [a-zA-Z0-9_-]+
     */
    fun captureScreenshot(screenshotName: String) {
        wait(SCREENSHOT_CAPTURE_DELAY_MS) {
            screenshoter.screenshot(screenshotName, screenshotsDir)
            saveScreenshotMetadata(screenshotsDir, screenshotName)
        }
    }

    /**
     *  Capture screenshot and save it to [screenshotsDir]/fails
     *  @param screenshotName name of screenshot. Must match [a-zA-Z0-9_-]+
     */
    fun captureScreenshotOnFail(screenshotName: String) {
        wait(SCREENSHOT_CAPTURE_DELAY_MS) {
            failScreenshoter.screenshot(screenshotName, screenshotDirOnFailed)
        }
    }

    private fun saveScreenshotMetadata(folderPath: File, stepName: String) {
        val activity = Device.activities.getResumed()
        if (activity == null) {
            logger.e("Activity is null on $stepName")
            return
        }
        runCatching {
            val metadata = ActivityMetadata.getFromActivity(activity)
                .toXml(Device.apps.targetAppPackageName)
            folderPath.resolve("$stepName.xml").safeWrite(metadata)
        }
    }
}