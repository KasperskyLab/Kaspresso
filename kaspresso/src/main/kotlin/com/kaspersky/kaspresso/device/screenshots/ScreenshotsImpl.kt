package com.kaspersky.kaspresso.device.screenshots

import com.kaspersky.kaspresso.device.activities.Activities
import com.kaspersky.kaspresso.extensions.other.getStackTraceAsString
import com.kaspersky.kaspresso.extensions.spoonext.UiAutomatorSpoon
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.squareup.spoon.Spoon
import java.io.File

/**
 * Default implementation of Screenshots interface.
 */
class ScreenshotsImpl(
    private val logger: UiTestLogger,
    private val activities: Activities
) : Screenshots {

    private val uiAutomatorSpoon = UiAutomatorSpoon(File("app_spoon-screenshots"), logger)

    /**
     * Makes screenshot if it is possible, otherwise logs the error.
     *
     * Required Permissions: WRITE_EXTERNAL_STORAGE
     *
     * @param tag a unique tag to further identify the screenshot. Must match [a-zA-Z0-9_-]+.
     */
    override fun makeIfPossible(tag: String) {
        val resumedActivity = activities.getResumed()

        try {
            resumedActivity?.let { Spoon.screenshot(it, tag) } ?: uiAutomatorSpoon.screenshot(tag)
        } catch (e: Throwable) {
            logger.e("An error while making screenshot occurred: ${e.getStackTraceAsString()}")
        }
    }
}