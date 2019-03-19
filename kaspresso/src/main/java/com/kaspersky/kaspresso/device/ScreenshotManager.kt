package com.kaspersky.kaspresso.device

import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.squareup.spoon.Spoon

object ScreenshotManager {

    private val logger: UiTestLogger = Configurator.logger

    fun makeScreenshotIfPossible(tag: String) {
        val resumedActivity = Device.activitiesManager.getResumedActivity() ?: return

        try {
            Spoon.screenshot(resumedActivity, tag)
        } catch (e: Throwable) {
            logger.e("An error while making screenshot occured: $e")
        }
    }
}