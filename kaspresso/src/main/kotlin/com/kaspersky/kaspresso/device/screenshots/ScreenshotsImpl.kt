package com.kaspersky.kaspresso.device.screenshots

import com.kaspersky.kaspresso.device.activities.Activities
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
    private val activities: Activities
) : Screenshots {

    private val internalScreenshotMaker = InternalScreenshotMaker(File("app_spoon-screenshots"))
    private val externalScreenshotMaker = ExternalScreenshotMaker(File("app_spoon-screenshots"))

    /**
     * Makes screenshot if it is possible, otherwise logs the error.
     *
     * @param tag a unique tag to further identify the screenshot. Must match [a-zA-Z0-9_-]+.
     */
    override fun makeIfPossible(tag: String) {
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