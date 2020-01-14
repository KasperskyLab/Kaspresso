package com.kaspersky.kaspresso.docloc

import com.kaspersky.kaspresso.device.activities.Activities
import com.kaspersky.kaspresso.device.activities.metadata.ActivityMetadata
import com.kaspersky.kaspresso.device.apps.Apps
import com.kaspersky.kaspresso.device.screenshots.screenshoter.ScreenshotFiles
import com.kaspersky.kaspresso.device.screenshots.screenshoter.external.ExternalScreenshotMaker
import com.kaspersky.kaspresso.internal.extensions.other.safeWrite
import com.kaspersky.kaspresso.internal.extensions.other.toXml
import com.kaspersky.kaspresso.internal.wait.wait
import com.kaspersky.kaspresso.logger.UiTestLogger
import java.io.File

/**
 * The class for saving and clearing docloc screenshots with metadata.
 * @param screenshotsDir directory to save screenshots and metadata.
 */
internal class DocLocScreenshotCapturer(
    private val screenshotsDir: File,
    private val logger: UiTestLogger,
    private val activities: Activities,
    private val apps: Apps
) {

    private companion object {
        private const val SCREENSHOT_CAPTURE_DELAY_MS: Long = 500
    }

    private val screenshotDirOnFailed = screenshotsDir.resolve("fails")

    private val screenshoter = ExternalScreenshotMaker(ScreenshotFiles(screenshotsDir))
    private val failScreenshoter = ExternalScreenshotMaker(ScreenshotFiles(screenshotDirOnFailed))
    private val activityMetadata = ActivityMetadata(logger)

    /**
     * Captures a screenshot and save it with metadata to [screenshotsDir].
     * @param screenshotName name of screenshot. Must match [a-zA-Z0-9_-]+
     */
    fun captureScreenshot(screenshotName: String) {
        wait(timeoutMs = SCREENSHOT_CAPTURE_DELAY_MS, logger = logger) {
            val screenshotFile = screenshoter.screenshot(screenshotName)
            saveScreenshotMetadata(screenshotFile.parentFile, screenshotName)
        }
    }

    /**
     * Captures a screenshot and save it to [screenshotsDir]/fails.
     * @param screenshotName name of screenshot. Must match [a-zA-Z0-9_-]+
     */
    fun captureScreenshotOnFail(screenshotName: String) {
        wait(timeoutMs = SCREENSHOT_CAPTURE_DELAY_MS, logger = logger) { failScreenshoter.screenshot(screenshotName) }
    }

    private fun saveScreenshotMetadata(folderPath: File, stepName: String) {
        val activity = activities.getResumed()
        if (activity == null) {
            logger.e("Activity is null on $stepName")
            return
        }
        runCatching {
            val metadata = activityMetadata.getFromActivity(activity)
                .toXml(apps.targetAppPackageName)
            folderPath.resolve("$stepName.xml").safeWrite(logger, metadata)
        }
    }
}