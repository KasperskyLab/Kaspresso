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
        return step
    }

    fun onStepFinished(stepInfo: StepInfo) {
        val localCurrentStepResult = currentStepResult
        if (localCurrentStepResult != stepInfo)
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
        return Step(
            description = description,
            testClassName = testInfo.testName,
            level = stepNumber.size,
            number = stepNumber.joinToString(separator = "."),
            ordinal = ++stepsCounter,
            stepNumber = stepNumber,
            parentStep = parentStep
        )
    }


    private class Step(
        override val description: String,
        override val testClassName: String,
        override val level: Int,
        override val number: String,
        override val ordinal: Int,
        val stepNumber: MutableList<Int>,
        val parentStep: Step? = null,
        val subSteps: MutableList<Step> = mutableListOf()
    ) : StepInfo {

        override fun toString(): String {
            return "Step(" +
                    "description=$description, " +
                    "testClassName=$testClassName, " +
                    "level=$level, number=$number, " +
                    "ordinal=$ordinal, " +
                    "stepNumber=$stepNumber, " +
                    "subSteps=$subSteps" +
                    ")"
        }
    }
}

