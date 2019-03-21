package com.kaspersky.kaspresso.testcase

import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.device.screenshots.ScreenshotsManager
import com.kaspersky.kaspresso.logger.UiTestLogger

/**
 *  Base class for all test cases.
 *  Extend the class with a project-wide TestCase and avoid direct inheritance from this one.
 *  Nesting TestCases is not permitted and may produce an Exception
 *  caused by re-initialization of the configurator, use [Scenario] instead
 */
abstract class TestCase(
    configBuilder: Configurator.Builder = Configurator.Builder.default()
) {
    protected val logger: UiTestLogger = Configurator.logger
    protected val screenshotsManager: ScreenshotsManager = Configurator.screenshotsManager

    private var stepCounter = 0

    /**
     * Finishes building of Configurator. Passing configBuilder to base [TestCase]'s constructor is the only way for
     * project-wide TestCase to tune Configurator.
     */
    init {
        configBuilder.commit()
    }

    /**
     * A step preparing test case's initial app state.
     */
    protected fun precondition(description: String, actions: () -> Unit) {
        logger.i(description)
        actions.invoke()
    }

    /**
     * A step of test case, something that changes the app state. Makes screenshot after the action successfully invoked,
     * or if an exception is caught.
     *
     * @param description the description of a step.
     * @param actions an action to invoke.
     */
    protected fun step(description: String, actions: () -> Unit) {
        logger.i("___________________________________________________________________________")
        logger.i("TEST STEP: $description")

        val screenshotTag = "${javaClass.simpleName}_step_${++stepCounter}"

        try {
            actions.invoke()
            screenshotsManager.makeScreenshotIfPossible(screenshotTag)
        } catch (e: Throwable) {
            screenshotsManager.makeScreenshotIfPossible("${screenshotTag}_failure_${e.javaClass.simpleName}")
            throw e
        }
    }
}