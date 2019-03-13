package com.kaspersky.uitest_framework.testcase

import com.kaspersky.uitest_framework.device.ScreenshotManager
import com.kaspersky.uitest_framework.configurator.Configurator
import com.kaspersky.uitest_framework.logger.UiTestLogger

abstract class TestCase(
        configBuilder: Configurator.Builder = Configurator.Builder().default()
) {
    protected val logger: UiTestLogger = Configurator.logger

    private var stepCounter = 0

    init {
        configBuilder.commit()
    }

    protected fun precondition(description: String, actions: () -> Unit) {
        logger.i(description)
        actions.invoke()
    }

    protected fun step(description: String, actions: () -> Unit) {
        logger.i("___________________________________________________________________________")
        logger.i("TEST STEP: $description")

        actions.invoke()

        val screenshotTag = "${this::class.simpleName}_step_${++stepCounter}"
        ScreenshotManager.makeScreenshotIfPossible(screenshotTag)
    }

}