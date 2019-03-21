package com.kaspersky.kaspresso.device.screenshots

import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.device.Device
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.squareup.spoon.Spoon

object ScreenshotsManagerImpl: ScreenshotsManager {

    private val logger: UiTestLogger = Configurator.logger

    override fun makeScreenshotIfPossible(tag: String) {
        val resumedActivity = Device.activitiesManager.getResumedActivity() ?: return

        try {
            Spoon.screenshot(resumedActivity, tag)
        } catch (e: Throwable) {
            logger.e("An error while making screenshot occured: $e")
        }
    }
}