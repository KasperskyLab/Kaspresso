package com.kaspersky.kaspresso.testcases.scenarios

import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.device.screenshots.Screenshots
import com.kaspersky.kaspresso.logger.UiTestLogger

/**
 * An implementation of [Scenario] for [com.kaspersky.kaspresso.testcases.SubCase]'s usage.
 */
class SubCaseScenario(title: String) : Scenario(title) {

    private val logger: UiTestLogger = Configurator.logger
    private val screenshots: Screenshots = Configurator.screenshots
    private val label = title.camelCaseToSnakeCase()

    /**
     * A representation of a [SubCaseScenario]'s step.
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