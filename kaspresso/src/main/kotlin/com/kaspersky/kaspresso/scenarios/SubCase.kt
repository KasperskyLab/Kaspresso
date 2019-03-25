package com.kaspersky.kaspresso.scenarios

import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.device.screenshots.Screenshots
import com.kaspersky.kaspresso.logger.UiTestLogger

/**
 * A base class for all subcases.
 */
abstract class SubCase : Scenario<SubCase.StepsRunner>(SubCase.StepsRunner()) {

    /**
     * An implementation of [SubCase]'s steps runner.
     */
    class StepsRunner : Scenario.StepsRunner() {

        private val logger: UiTestLogger = Configurator.logger
        private val screenshots: Screenshots = Configurator.screenshots
        private val label = javaClass.declaringClass!!.simpleName.camelCaseToSnakeCase()

        /**
         * A representation of a [SubCase]'s step.
         *
         * @param description a description of a step.
         * @param actions a set of actions of a step.
         */
        override fun step(description: String, actions: () -> Unit) {
            logger.i("${label.toUpperCase()} STEP: ")

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
}