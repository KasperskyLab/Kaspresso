package com.kaspersky.kaspresso.scenarios.stepsrunners

import com.kaspersky.kaspresso.device.Device
import com.kaspersky.kaspresso.device.screenshots.Screenshots
import com.kaspersky.kaspresso.logger.KLogger
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso.scenarios.stepsrunners.exceptions.TestCaseStepsRunnerException

/**
 * An implementation of [TestCase]'s steps runner.
 */
class TestCaseStepsRunner(testCaseName: String) : StepsRunner(testCaseName) {

    private val logger: UiTestLogger = KLogger
    private val screenshots: Screenshots = Device.screenshots
    private var stepsRunnerStage = StepsRunnerStage.START

    /**
     * A representation of a [TestCase]'s step.
     * Method must be called after [precondition] and before [postcondition]
     *
     * @param description a description of a step.
     * @param actions a set of actions of a step.
     * @throws TestCaseStepsRunnerException if method was called in the wrong place
     */
    override fun step(description: String, actions: () -> Unit) {
        when (stepsRunnerStage) {
            StepsRunnerStage.START -> throw TestCaseStepsRunnerException("precondition method must be called in the begin of TestCase")
            StepsRunnerStage.POSTCONDITION_DONE -> throw TestCaseStepsRunnerException(
                "step method should not be after postcondition method"
            )
            else -> {}
        }
        stepsRunnerStage = StepsRunnerStage.STEPS_IN_PROGRESS

        logger.i("___________________________________________________________________________")
        logger.i("TEST STEP: $description")

        val screenshotTag = "${testCaseName}_step_${++stepsCounter}"

        try {
            actions.invoke()
            screenshots.makeIfPossible(screenshotTag)
        } catch (e: Throwable) {
            screenshots.makeIfPossible("${screenshotTag}_failure_${e.javaClass.simpleName}")
            throw e
        }
    }

    /**
     * A representation of a step preparing [TestCase]'s initial app state.
     * Method must be called in the begin of TestCase
     *
     * @param description a description of a precondition.
     * @param actions a set of actions of a precondition.
     * @throws TestCaseStepsRunnerException if method was called in the wrong place
     */
    fun precondition(description: String, actions: () -> Unit) {
        if (stepsRunnerStage != StepsRunnerStage.START) {
            throw TestCaseStepsRunnerException("precondition method must be called in the begin of TestCase")
        }
        stepsRunnerStage = StepsRunnerStage.PRECONDITION_DONE

        logger.i("PRECONDITION: $description")

        try {
            actions.invoke()
        } catch (e: Throwable) {
            screenshots.makeIfPossible("${testCaseName}_precondition_failure_${e.javaClass.simpleName}")
            throw e
        }
    }

    /**
     * A representation of a finishing step returning [TestCase]'s app state to initial.
     * Method must be called in the end of TestCase
     *
     * @param description a description of a postcondition.
     * @param actions a set of actions of a postcondition.
     * @throws TestCaseStepsRunnerException if method was called in the wrong place
     */
    fun postcondition(description: String, actions: () -> Unit) {
        if (stepsRunnerStage != StepsRunnerStage.STEPS_IN_PROGRESS) {
            throw TestCaseStepsRunnerException("postcondition method must be called in the end of TestCase")
        }
        stepsRunnerStage = StepsRunnerStage.POSTCONDITION_DONE

        logger.i("POSTCONDITION: $description")

        try {
            actions.invoke()
        } catch (e: Throwable) {
            screenshots.makeIfPossible("${testCaseName}_postcondition_failure_${e.javaClass.simpleName}")
            throw e
        }
    }

    override fun checkAfter() {
        if (stepsRunnerStage != StepsRunnerStage.POSTCONDITION_DONE) {
            throw TestCaseStepsRunnerException("postcondition method must be called in the end of TestCase")
        }
    }

    private enum class StepsRunnerStage {
        START,
        PRECONDITION_DONE,
        STEPS_IN_PROGRESS,
        POSTCONDITION_DONE
    }

}
