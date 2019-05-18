package com.kaspersky.kaspresso.testcases.core

import com.kaspersky.kaspresso.testcases.models.InternalStepInfo
import com.kaspersky.kaspresso.testcases.models.InternalTestInfo
import com.kaspersky.kaspresso.testcases.models.StepInfo
import com.kaspersky.kaspresso.testcases.models.StepStatus


class StepManager(private val testResult: InternalTestInfo) : StepProducer {
    private val stepResultList: MutableList<InternalStepInfo> = mutableListOf()

    private var currentStepResult: InternalStepInfo? = null

    private var stepsCounter: Int = 0

    override fun produceStepInfo(description: String): StepInfo {
        val localCurrentStep = currentStepResult
        val step: InternalStepInfo
        if (localCurrentStep == null) {
            val stepNumber = mutableListOf(stepResultList.size + 1)
            step = produceStep(description, stepNumber)
            currentStepResult = step
            stepResultList.add(step)
        } else {
            val stepNumber: MutableList<Int> = mutableListOf()
            stepNumber.addAll(localCurrentStep.stepNumber)
            stepNumber.add(localCurrentStep.internalSubSteps.size + 1)
            step = produceStep(description, stepNumber, localCurrentStep)
            localCurrentStep.internalSubSteps.add(step)
            currentStepResult = step
        }
        return step
    }

    override fun onStepFinished(stepInfo: StepInfo, error: Throwable?) {
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

    private fun produceStep(
        description: String,
        stepNumber: MutableList<Int>,
        parentStep: InternalStepInfo? = null
    ): InternalStepInfo {
        return InternalStepInfo(
            description = description,
            testClassName = testResult.testName,
            level = stepNumber.size,
            number = stepNumber.joinToString(separator = "."),
            ordinal = ++stepsCounter,
            stepNumber = stepNumber,
            parentStep = parentStep
        )
    }

    fun onStepsFinished() {

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
        currentStepResult = null

        if (testResult.internalSteps.isNotEmpty()) {
            throw AssertionError("onStepsFinished called on already finished test")
        }
        testResult.internalSteps.addAll(stepResultList)

    }

    fun onTestFinished(throwable: Throwable? = null) {
        testResult.internalThrowable = throwable
    }
}

