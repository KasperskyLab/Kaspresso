package com.kaspersky.kaspresso.testcases.core.step

import com.kaspersky.kaspresso.params.StepParams
import com.kaspersky.kaspresso.testcases.models.StepStatus
import com.kaspersky.kaspresso.testcases.models.info.InternalStepInfo
import com.kaspersky.kaspresso.testcases.models.info.StepInfo

/**
 * [StepsManager] produces step and manages lifecycle of step.
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
 * 1) While calling [produceStepInfo]:
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
 */

internal class StepsManager(
    private val testName: String,
    private val stepParams: StepParams
) : StepInfoProducer {

    private val stepResultList: MutableList<InternalStepInfo> = mutableListOf()
    private var currentStepResult: InternalStepInfo? = null
    private var stepsCounter: Int = 0

    private var testPassed: Boolean = false

    override fun produceStepInfo(description: String): StepInfo {
        checkState()

        val localCurrentStep = currentStepResult
        var stepNumber: MutableList<Int>? = null
        val stepInfo: InternalStepInfo

        if (localCurrentStep == null) {
            if (stepParams.autonumber) {
                stepNumber = mutableListOf(stepResultList.size + 1)
            }

            stepInfo = produceInternalStepInfo(description, stepNumber)
            currentStepResult = stepInfo
            stepResultList.add(stepInfo)
        } else {
            if (stepParams.autonumber) {
                stepNumber = mutableListOf()
                stepNumber.addAll(localCurrentStep.stepNumber!!)
                stepNumber.add(localCurrentStep.internalSubStepInfos.size + 1)
            }

            stepInfo = produceInternalStepInfo(description, stepNumber, localCurrentStep)
            localCurrentStep.internalSubStepInfos.add(stepInfo)
            currentStepResult = stepInfo
        }

        return stepInfo
    }

    override fun onStepFinished(stepInfo: StepInfo, error: Throwable?) {
        checkState()

        val localCurrentStepResult = currentStepResult

        if (localCurrentStepResult != stepInfo)
            throw IllegalStateException(
                "Unable to finish step $stepInfo cause it is not current. " +
                        "Current step is $localCurrentStepResult. All stepInfos: $stepResultList"
            )

        localCurrentStepResult.internalStatus = if (error == null) StepStatus.SUCCESS else StepStatus.FAILED
        localCurrentStepResult.internalThrowable = error
        localCurrentStepResult.internalStopTime = System.currentTimeMillis()
        currentStepResult = localCurrentStepResult.parentStepInfo
    }

    /**
     * Calling after all test's steps finished.
     * It helps correctly finish all steps to return lately an actual steps hierarchy.
     * @return result expressed in List of StepInfo (supports hierarchy) gotten after Test completed.
     */
    fun getAllStepsResult(): List<StepInfo> {
        checkState()

        var localCurrentStep = currentStepResult
        var error: Throwable? = null

        while (localCurrentStep != null) {
            localCurrentStep.internalStatus = StepStatus.FAILED

            if (error == null) {
                val childFailedStepResult =
                    localCurrentStep.internalSubStepInfos
                        .reversed()
                        .firstOrNull { it.internalStatus == StepStatus.FAILED }

                error = when (childFailedStepResult) {
                    null -> IllegalStateException("Unable to find error to finish failed step $localCurrentStep. Check all stepInfos $stepResultList")
                    else -> childFailedStepResult.throwable
                }
            }

            localCurrentStep.internalThrowable = error
            localCurrentStep = localCurrentStep.parentStepInfo
        }

        testPassed = true

        // swallow copy
        return stepResultList.map { it }
    }

    private fun checkState() {
        if (testPassed) {
            throw IllegalStateException(
                "Please create new StepsProcessHandler object because this object consists old state." +
                        "Possible reason is getAllStepsResult() calling " +
                        "was not after all test operations."
            )
        }
    }

    private fun produceInternalStepInfo(
        description: String,
        stepNumber: MutableList<Int>?,
        parentStepInfo: InternalStepInfo? = null
    ): InternalStepInfo {

        return InternalStepInfo(
            description = description,
            testClassName = testName,
            number = stepNumber?.joinToString(separator = "."),
            ordinal = ++stepsCounter,
            stepNumber = stepNumber,
            parentStepInfo = parentStepInfo,
            startTime = System.currentTimeMillis()
        )
    }
}
