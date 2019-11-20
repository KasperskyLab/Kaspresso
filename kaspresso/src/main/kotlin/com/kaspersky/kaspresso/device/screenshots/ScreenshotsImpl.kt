package com.kaspersky.kaspresso.device.screenshots

import com.kaspersky.kaspresso.device.activities.Activities
import com.kaspersky.kaspresso.device.screenshots.screenshoter.ScreenshotFiles
import com.kaspersky.kaspresso.device.screenshots.screenshoter.external.ExternalScreenshotMaker
import com.kaspersky.kaspresso.device.screenshots.screenshoter.internal.InternalScreenshotMaker
import com.kaspersky.kaspresso.internal.extensions.other.getStackTraceAsString
import com.kaspersky.kaspresso.logger.UiTestLogger
import java.io.File

/**
 * The implementation of the [Screenshots] interface.
 */
class ScreenshotsImpl(
    private val logger: UiTestLogger,
    private val activities: Activities,
    screenshotDir: File = File("screenshots"),
    private val addTimestamps: Boolean = true
) : Screenshots {

    companion object {
        private const val NAME_SEPARATOR = "_"
    }

    private val screenshotFiles = ScreenshotFiles(screenshotDir)

    private val internalScreenshotMaker = InternalScreenshotMaker(screenshotFiles)
    private val externalScreenshotMaker = ExternalScreenshotMaker(screenshotFiles)

    /**
     * Takes screenshot if it is possible, otherwise logs the error.
     * If addTimestamps is true, the method adds System.currentTimeMillis() to the tag to save
     * all screenshots of a test running several times per the same suite. In that case a name will look
     * like "1570158949869_ScreenshotSampleTest_step_1".
     *
     * Required Permissions: WRITE_EXTERNAL_STORAGE.
     *
     * @param tag a unique tag to further identify the screenshot. Must match [a-zA-Z0-9_-]+.
     */
    override fun take(tag: String) {
        val resumedActivity = activities.getResumed()
        val namePrefix = if (addTimestamps) System.currentTimeMillis().toString() + NAME_SEPARATOR else ""
        val fullName = namePrefix + tag

        if (resumedActivity != null) {
            runCatching {
                internalScreenshotMaker.screenshot(resumedActivity, fullName)
            }.onSuccess {
                return
            }
        }

        runCatching { externalScreenshotMaker.screenshot(fullName) }
            .onFailure { e -> logger.e("An error while making screenshot occurred: ${e.getStackTraceAsString()}") }
    }
}