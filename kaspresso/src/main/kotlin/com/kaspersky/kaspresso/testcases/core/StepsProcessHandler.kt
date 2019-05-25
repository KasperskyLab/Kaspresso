package com.kaspersky.kaspresso.testcases.core

import com.kaspersky.kaspresso.testcases.models.InternalStepInfo
import com.kaspersky.kaspresso.testcases.models.StepInfo
import com.kaspersky.kaspresso.testcases.models.StepStatus
import java.lang.RuntimeException


/**
 * [StepsProcessHandler] produces step and manages lifecycle of step.
 * To make correct numeration for sub steps(see example below) it builds step hierarchy.
 *
 *
 * step("A"){
 *   step("B")
 *   step("C"){
 *     step("D")
 *   }
 * }
 *
 * Steps will have numbers:
 *
 * A : 1
 * B : 1.1
 * C : 1.2
 * D : 1.2.1
 *
 * Step numbers calculation algorithm:
 *
 * 0) Preconditions:
 *  - [currentStepResult] is null,
 *  - [stepsCounter] is 0. Number of steps(including sub steps)
 *  - [stepResultList] is empty. This is container to first level steps (step without parent step)
 *
 * 1) While calling [produceStep]:
 *
 * 1.1) If [currentStepResult] is null:
 *  - [stepsCounter] increasing by 1
 *  - Create new step.
 *  - Put it on the [stepResultList]
 *  - Step number is position on [stepResultList] + 1
 *  - Now it is a [currentStepResult]
 *
 * 1.2) If we already has [currentStepResult]:
 * - [stepsCounter] increasing by 1
 *  - Create new step
 *  - Put it on the sub steps of [currentStepResult]
 *  - Step number is: "${number of its parent}.${position at its parent sub steps + 1}"
 *  - Now it is a [currentStepResult]
 *
 * 2) While calling [onStepFinished]
 *  - If we trying to finish step that not a [currentStepResult] method throws [IllegalStateException] cause it is not a valid situation
 *  - mark step as [StepStatus.SUCCESS] or [StepStatus.FAILED]. It depends on 'error' arguments value
 *
 * 2.1) If step has parent
 *  - Now its parent is a [currentStepResult]
 * 2.2 If step has no parent
 *  - [currentStepResult] is null
 *
 *
 *
 */

internal class StepsProcessHandler(private val testName: String) : StepProducer {

    private val stepResultList: MutableList<InternalStepInfo> = mutableListOf()
    private var currentStepResult: InternalStepInfo? = null
    private var stepsCounter: Int = 0

    private var testPassed: Boolean = false

    override fun produceStep(description: String): StepInfo {
        checkHandlerState()
        val localCurrentStep = currentStepResult
        val step: InternalStepInfo
        if (localCurrentStep == null) {
            val stepNumber = mutableListOf(stepResultList.size + 1)
            step = produceStepInternal(description, stepNumber)
            currentStepResult = step
            stepResultList.add(step)
        } else {
            val stepNumber: MutableList<Int> = mutableListOf()
            stepNumber.addAll(localCurrentStep.stepNumber)
            stepNumber.add(localCurrentStep.internalSubSteps.size + 1)
            step = produceStepInternal(description, stepNumber, localCurrentStep)
            localCurrentStep.internalSubSteps.add(step)
            currentStepResult = step
        }
        return step
    }

    private fun checkHandlerState() {
        if (testPassed) {
            throw RuntimeException(
                "Please create new StepsProcessHandler object because this object consists old state"
            )
        }
    }

    private fun produceStepInternal(
        description: String,
        stepNumber: MutableList<Int>,
        parentStep: InternalStepInfo? = null
    ): InternalStepInfo {
        return InternalStepInfo(
            description = description,
            testClassName = testName,
            level = stepNumber.size,
            number = stepNumber.joinToString(separator = "."),
            ordinal = ++stepsCounter,
            stepNumber = stepNumber,
            parentStep = parentStep
        )
    }

    override fun onStepFinished(stepInfo: StepInfo, error: Throwable?) {
        checkHandlerState()
        val localCurrentStepResult = currentStepResult
        if (localCurrentStepResult != stepInfo)
            throw IllegalStateException(
                "Unable to finish step $stepInfo cause it is not current. " +
                        "Current step is $localCurrentStepResult. All steps: $stepResultList"
            )

        localCurrentStepResult.internalStatus = if (error == null) StepStatus.SUCCESS else StepStatus.FAILED
        localCurrentStepResult.internalThrowable = error
        currentStepResult = localCurrentStepResult.parentStep
    }

    /**
     * Calling after all test's steps finished.
     * It helps correctly finish all steps to return lately an actual steps hierarchy
     * @return result expressed in List of StepInfo (supports hierarchy) gotten after Test completed
     */
    fun onAllStepsFinishedAndGetResultInSteps(): List<StepInfo> {
        checkHandlerState()

        var localCurrentStep = currentStepResult
        var error: Throwable? = null

        while (localCurrentStep != null) {
            localCurrentStep.internalStatus = StepStatus.FAILED

            if (error == null) {
                val childFailedStepResult =
                    localCurrentStep.internalSubSteps.reversed().firstOrNull { it.internalStatus == StepStatus.FAILED }

                error = when (childFailedStepResult) {
                    null -> IllegalStateException("Unable to find error to finish failed step $localCurrentStep. Check all steps $stepResultList")
                    else -> childFailedStepResult.throwable
                }
            }

            localCurrentStep.internalThrowable = error
            localCurrentStep = localCurrentStep.parentStep
        }

        testPassed = true
        // swallow copy
        return stepResultList.map { it }
    }

}

