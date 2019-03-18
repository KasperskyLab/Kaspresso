package com.kaspersky.uitest_framework.device

import com.kaspersky.uitest_framework.configurator.Configurator
import com.kaspersky.uitest_framework.logger.UiTestLogger
import com.squareup.spoon.Spoon

/**
 * Encapsulates all work with screenshots.
 */
object ScreenshotManager {

    private val logger: UiTestLogger = Configurator.logger

    /**
     * Makes screenshot if it is possible, otherwise logs an error
     */
    fun makeScreenshotIfPossible(tag: String) {
        val resumedActivity = Device.activitiesManager.getResumedActivity() ?: return

        try {
            Spoon.screenshot(resumedActivity, tag)
        } catch (e: Throwable) {
            logger.e("An error while making screenshot occured: $e")
        }
    }
}