package com.kaspersky.kaspresso.scenarios

import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.device.screenshots.Screenshots
import com.kaspersky.kaspresso.logger.UiTestLogger

/**
 *  A base class for all test cases. Extend this class with a single base project-wide inheritor of [TestCase] as a
 *  parent for all actual project-wide test cases. Nesting test cases are not permitted because they may produce an
 *  exception caused by re-initialization of the [Configurator], use [Scenario] instead.
 */
abstract class TestCase(
    configBuilder: Configurator.Builder = Configurator.Builder.default()
) : Scenario<TestCase.StepsRunner>(TestCase.StepsRunner()) {

    /**
     * Finishes building of [Configurator]. Passing [Configurator.Builder] to base [TestCase]'s constructor is the only
     * way for project-wide inheritor of [TestCase] to tune [Configurator].
     */
    init {
        configBuilder.commit()
    }

    /**
     * An implementation of [TestCase]'s steps runner.
     */
    class StepsRunner : Scenario.StepsRunner() {

        private val logger: UiTestLogger = Configurator.logger
        private val screenshots: Screenshots = Configurator.screenshots
        private val label = javaClass.declaringClass!!.simpleName

        /**
         * A representation of a [TestCase]'s step.
         *
         * @param description a description of a step.
         * @param actions a set of actions of a step.
         */
        override fun step(description: String, actions: () -> Unit) {
            logger.i("___________________________________________________________________________")
            logger.i("TEST STEP: $description")

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
         * A repesentation of a step preparing [TestCase]'s initial app state.
         *
         * @param description a description of a precondition.
         * @param actions a set of actions of a precondition.
         */
        fun precondition(description: String, actions: () -> Unit) {
            logger.i("PRECONDITION: $description")

            try {
                actions.invoke()
            } catch (e: Throwable) {
                screenshots.makeIfPossible("${label}_precondition_failure_${e.javaClass.simpleName}")
                throw e
            }
        }

        /**
         * A repesentation of a finishing step returning [TestCase]'s app state to initial.
         *
         * @param description a description of a postcondition.
         * @param actions a set of actions of a postcondition.
         */
        fun postcondition(description: String, actions: () -> Unit) {
            logger.i("POSTCONDITION: $description")

            try {
                actions.invoke()
            } catch (e: Throwable) {
                screenshots.makeIfPossible("${label}_postcondition_failure_${e.javaClass.simpleName}")
                throw e
            }
        }
    }
}