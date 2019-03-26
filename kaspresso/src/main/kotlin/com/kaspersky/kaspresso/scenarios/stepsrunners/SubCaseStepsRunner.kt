package com.kaspersky.kaspresso.scenarios.stepsrunners

import com.kaspersky.kaspresso.device.Device
import com.kaspersky.kaspresso.device.screenshots.Screenshots
import com.kaspersky.kaspresso.logger.KLogger
import com.kaspersky.kaspresso.logger.UiTestLogger

/**
 * An implementation of [SubCase]'s steps runner.
 */
class SubCaseStepsRunner(testCaseName: String) : StepsRunner(testCaseName) {

    private val logger: UiTestLogger = KLogger
    private val screenshots: Screenshots = Device.screenshots
    private val label = testCaseName.camelCaseToSnakeCase()

    /**
     * A representation of a [SubCase]'s step.
     *
     * @param description a description of a step.
     * @param actions a set of actions of a step.
     */
    override fun step(description: String, actions: () -> Unit) {
        logger.i("${label.toUpperCase()} STEP: $description")

        val screenshotTag = "${label}_step_${++stepsCounter}"

        try {
            actions.invoke()
            screenshots.makeIfPossible(screenshotTag)
        } catch (e: Throwable) {
            screenshots.makeIfPossible("${screenshotTag}_failure_${e.javaClass.simpleName}")
            throw e
        }
    }

    /**
     * Converts a [String] from camel case to snake case.
     *
     * @return a [String] in snake case.
     */
    private fun String.camelCaseToSnakeCase() = this.replace("([a-z])([A-Z]+)", "$1_$2")
}
