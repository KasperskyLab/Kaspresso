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

    /**
     * A representation of a [SubCaseScenario]'s step.
     *
     * @param description a description of a step.
     * @param actions a set of actions of a step.
     */
    override fun step(description: String, actions: () -> Unit) {
        logger.i("$title STEP: \"$description\"")

        val screenshotTag = "${title}_step_${++stepsCounter}"

        try {
            actions.invoke()
            screenshots.makeIfPossible(screenshotTag)
        } catch (e: Throwable) {
            screenshots.makeIfPossible("${screenshotTag}_failure_${e.javaClass.simpleName}")
            throw e
        }
    }
}