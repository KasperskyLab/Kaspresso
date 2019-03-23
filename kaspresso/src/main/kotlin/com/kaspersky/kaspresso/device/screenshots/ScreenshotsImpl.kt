package com.kaspersky.kaspresso.device.screenshots

import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.device.Device
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.squareup.spoon.Spoon

/**
 * Default implementation of Screenshots interface.
 */
object ScreenshotsImpl: Screenshots {

    private val logger: UiTestLogger = Configurator.logger

    /**
     * Makes screenshot if it is possible, otherwise loggs the error.
     *
     * @param tag a unique tag to further identify the screenshot. Must match [a-zA-Z0-9_-]+.
     */
    override fun makeIfPossible(tag: String) {
        val resumedActivity = Device.activities.getResumed() ?: return

        try {
            Spoon.screenshot(resumedActivity, tag)
        } catch (e: Throwable) {
            logger.e("An error while making screenshot occured: $e")
        }
    }
}