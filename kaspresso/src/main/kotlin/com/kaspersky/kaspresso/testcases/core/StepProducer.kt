package com.kaspersky.kaspresso.testcases.core

import com.kaspersky.kaspresso.testcases.models.StepInfo
import com.kaspersky.kaspresso.testcases.models.TestInfo

class StepProducer(private val testInfo: TestInfo) {
    private val stepResultList: MutableList<Step> = mutableListOf()

    private var currentStepResult: Step? = null

    private var stepsCounter: Int = 0


    fun produceStepInfo(description: String): StepInfo {
        val localCurrentStep = currentStepResult
        val step: Step
        if (localCurrentStep == null) {
            val stepNumber = mutableListOf(stepResultList.size + 1)
            step = produceStep(description, stepNumber)
            currentStepResult = step
            stepResultList.add(step)
        } else {
            val stepNumber: MutableList<Int> = mutableListOf()
            stepNumber.addAll(localCurrentStep.stepNumber)
            stepNumber.add(localCurrentStep.subSteps.size + 1)
            step = produceStep(description, stepNumber, localCurrentStep)
            localCurrentStep.subSteps.add(step)
            currentStepResult = step
        }
        return step.stepInfo
    }

    fun onStepFinished(stepInfo: StepInfo) {
        val localCurrentStepResult = currentStepResult
        if (localCurrentStepResult?.stepInfo != stepInfo)
            throw IllegalStateException(
                "Unable to finish step $stepInfo cause it is not current. " +
                        "Current step is $localCurrentStepResult. All steps: $stepResultList"
            )
        currentStepResult = localCurrentStepResult.parentStep
    }

    private fun produceStep(
        description: String,
        stepNumber: MutableList<Int>,
        parentStep: Step? = null
    ): Step {
        val stepInfo = StepInfo(
            description = description,
            testClassName = testInfo.testName,
            level = stepNumber.size,
            number = stepNumber.joinToString(separator = "."),
            ordinal = ++stepsCounter
        )
        return Step(stepInfo, stepNumber, parentStep)
    }

    private data class Step(
        val stepInfo: StepInfo,
        val stepNumber: MutableList<Int>,
        val parentStep: Step? = null,
        val subSteps: MutableList<Step> = mutableListOf()
    ) {

        override fun toString(): String {
            return "Step(stepInfo=$stepInfo, stepNumber=$stepNumber, subSteps=$subSteps)"
        }
    }
}

