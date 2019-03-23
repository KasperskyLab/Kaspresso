package com.kaspersky.uitest_framework.testcase

import com.kaspersky.uitest_framework.device.ScreenshotManager
import com.kaspersky.uitest_framework.configurator.Configurator
import com.kaspersky.uitest_framework.logger.UiTestLogger

/**
 *  Base class for all test cases.
 *  Extend this class with a project-wide TestCase and avoid direct inheritance from this one.
 *  Nesting TestCases is not permitted and may produce an Exception
 *  caused by re-initialization of the configurator, use [Scenario] instead.
 */
abstract class TestCase(
    configBuilder: Configurator.Builder = Configurator.Builder.default()
) {
    protected val logger: UiTestLogger = Configurator.logger

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
     * @param description the description of the step.
     * @param actions action
     */
    protected fun step(description: String, actions: () -> Unit) {
        logger.i("___________________________________________________________________________")
        logger.i("TEST STEP: $description")

        val screenshotTag = "${javaClass.simpleName}_step_${++stepCounter}"

        try {
            actions.invoke()
            ScreenshotManager.makeScreenshotIfPossible(screenshotTag)
        } catch (e: Throwable) {
            ScreenshotManager.makeScreenshotIfPossible("${screenshotTag}_failure_${e.javaClass.simpleName}")

            throw e
        }
    }
}
