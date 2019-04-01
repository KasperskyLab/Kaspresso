package com.kaspersky.kaspresso.testcases

import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.device.screenshots.Screenshots
import com.kaspersky.kaspresso.logger.UiTestLogger

/**
 * A representation of a sequence of test's actions.
 */
class Scenario(
    private val title: String,
    private val log: UiTestLogger.(String, String) -> Unit
) {
    private val logger: UiTestLogger = Configurator.logger
    private val screenshots: Screenshots = Configurator.screenshots

    /**
     * A step counter to evaluate current step's tag.
     */
    private var stepsCounter: Int = 0

    /**
     * A representation of a [Scenario]'s step.
     *
     * @param description a description of a step.
     * @param actions a set of actions of a step.
     */
    fun step(description: String, actions: () -> Unit) {
        log.invoke(logger, title, description)

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