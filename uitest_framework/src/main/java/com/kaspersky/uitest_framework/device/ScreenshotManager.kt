package com.kaspersky.uitest_framework.device

import com.kaspersky.uitest_framework.configuration.InterceptorConfigurator
import com.kaspersky.uitest_framework.logger.UiTestLogger
import com.squareup.spoon.Spoon

object ScreenshotManager {

    private val logger: UiTestLogger = InterceptorConfigurator.logger

    fun makeScreenshotIfPossible(tag: String) {
        val resumedActivity = Device.activitiesManager.getResumedActivity() ?: return

        try {
            Spoon.screenshot(resumedActivity, tag)
        } catch (e: Throwable) {
            logger.e("An error while making screenshot occured: $e")
        }
    }
}