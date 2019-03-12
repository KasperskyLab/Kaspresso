package com.kaspersky.uitest_framework.testcase

import com.kaspersky.uitest_framework.device.ScreenshotManager
import com.kaspersky.uitest_framework.Configuration
import com.kaspersky.uitest_framework.logger.UiTestLogger

abstract class TestCase<out T: TestCase.ScreensStorage>(
        protected val screens: T,
        configBuilder: Configuration.Builder = Configuration.Builder().default()
) {
    protected val logger: UiTestLogger = Configuration.logger

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

    interface ScreensStorage
}