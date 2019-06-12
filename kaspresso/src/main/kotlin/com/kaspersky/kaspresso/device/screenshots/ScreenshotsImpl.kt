package com.kaspersky.kaspresso.device.screenshots

import com.kaspersky.kaspresso.device.activities.Activities
import com.kaspersky.kaspresso.device.screenshots.screenshoter.ScreenshotFiles
import com.kaspersky.kaspresso.device.screenshots.screenshoter.external.ExternalScreenshotMaker
import com.kaspersky.kaspresso.device.screenshots.screenshoter.internal.InternalScreenshotMaker
import com.kaspersky.kaspresso.extensions.other.getStackTraceAsString
import com.kaspersky.kaspresso.logger.UiTestLogger
import java.io.File

/**
 * Default implementation of Screenshots interface.
 */
class ScreenshotsImpl(
    private val logger: UiTestLogger,
    private val activities: Activities,
    screenshotDir: File = File("screenshots")
) : Screenshots {

    private val screenshotFiles = ScreenshotFiles(screenshotDir)

    private val internalScreenshotMaker = InternalScreenshotMaker(screenshotFiles)
    private val externalScreenshotMaker = ExternalScreenshotMaker(screenshotFiles)

    /**
     * Takes screenshot if it is possible, otherwise logs the error.
     *
     * Required Permissions: WRITE_EXTERNAL_STORAGE
     *
     * @param tag a unique tag to further identify the screenshot. Must match [a-zA-Z0-9_-]+.
     */
    override fun take(tag: String) {
        val resumedActivity = activities.getResumed()

        if (resumedActivity != null) {
            runCatching {
                internalScreenshotMaker.screenshot(resumedActivity, tag)
            }.onSuccess {
                return
            }
        }

        runCatching { externalScreenshotMaker.screenshot(tag) }
            .onFailure { e -> logger.e("An error while making screenshot occurred: ${e.getStackTraceAsString()}") }
    }
}