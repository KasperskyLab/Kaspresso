package com.kaspersky.uitest_framework.testcase

import com.kaspersky.uitest_framework.Configuration
import com.kaspersky.uitest_framework.device.Device
import com.kaspersky.uitest_framework.logger.UiTestLogger
import com.squareup.spoon.Spoon

abstract class TestCase{

    val logger: UiTestLogger = Configuration.logger

    protected fun precondition(description: String, actions: () -> Unit) {
        logger.i(description)
        actions.invoke()
    }

    protected fun step(description: String, actions: () -> Unit) {
        logger.i("___________________________________________________________________________")
        logger.i("TEST STEP: $description")

        actions.invoke()

        makeScreenshotIfCan()
    }

    private fun makeScreenshotIfCan() {
        val resumedActivity = Device.activitiesManager.getResumedActivity() ?: return

        try {
            Spoon.screenshot(resumedActivity, "screen")
        } catch (e: Throwable) {
            logger.e("An error while making screenshot occured: $e")
        }
    }

    interface ScreensStorage
}