package com.kaspersky.kaspresso.testcase

import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.device.screenshots.Screenshots
import com.kaspersky.kaspresso.logger.UiTestLogger

/**
 *  An abstract class for all scenarios.
 */
abstract class Scenario {

    protected val logger: UiTestLogger = Configurator.logger
    protected val screenshots: Screenshots = Configurator.screenshots

    private var stepCounter = 0

    /**
     * A step of scenario. Makes screenshot after the action successfully invoked, or if an exception is caught.
     *
     * @param description the description of a step.
     * @param actions an action to invoke.
     */
    protected fun sceneStep(description: String, actions: () -> Unit) {
        logger.i("___________________________________________________________________________")
        logger.i("TEST SCENE_STEP: $description")

        val screenshotTag = "${javaClass.simpleName}_scene_step_${++stepCounter}"

        try {
            actions.invoke()
            screenshots.makeIfPossible(screenshotTag)
        } catch (e: Throwable) {
            screenshots.makeIfPossible("${screenshotTag}_failure_${e.javaClass.simpleName}")
            throw e
        }
    }
}
