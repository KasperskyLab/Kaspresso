package com.kaspersky.kaspresso.testcase

import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.device.screenshots.ScreenshotsManager
import com.kaspersky.kaspresso.logger.UiTestLogger

/**
 *  Abstract class for all scenarios.
 */
abstract class Scenario {

    protected val logger: UiTestLogger = Configurator.logger
    protected val screenshotsManager: ScreenshotsManager = Configurator.screenshotsManager

    private var stepCounter = 0

    protected fun sceneStep(description: String, actions: () -> Unit) {
        logger.i("___________________________________________________________________________")
        logger.i("TEST SCENE_STEP: $description")

        val screenshotTag = "${javaClass.simpleName}_scene_step_${++stepCounter}"

        try {
            actions.invoke()
            screenshotsManager.makeScreenshotIfPossible(screenshotTag)
        } catch (e: Throwable) {
            screenshotsManager.makeScreenshotIfPossible("${screenshotTag}_failure_${e.javaClass.simpleName}")
            throw e
        }
    }
}
