package com.kaspersky.uitest_framework.testcase

import com.kaspersky.uitest_framework.configurator.Configurator
import com.kaspersky.uitest_framework.device.ScreenshotManager
import com.kaspersky.uitest_framework.logger.UiTestLogger

/**
 *  Abstract class for all scenarios.
 */
abstract class Scenario {

    protected val logger: UiTestLogger = Configurator.logger

    private var stepCounter = 0

    protected fun precondition(description: String, actions: () -> Unit) {
        logger.i(description)
        actions.invoke()
    }

    protected fun step(description: String, actions: () -> Unit) {
        logger.i("___________________________________________________________________________")
        logger.i("TEST STEP: $description")

        val screenshotTag = "${this::class.simpleName}_step_${++stepCounter}"

        try {
            actions.invoke()
            ScreenshotManager.makeScreenshotIfPossible(screenshotTag)
        } catch (e: Throwable) {
            ScreenshotManager.makeScreenshotIfPossible(
                "${screenshotTag}_failure_${e::class.simpleName}"
            )
            throw e
        }
    }
}
