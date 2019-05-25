package com.kaspersky.kaspresso.testcases.models

data class TestInfo(
    val testName: String,
    val steps: List<StepInfo> = listOf(),
    val throwable: Throwable? = null
)

fun TestInfo.deepCopy(): TestInfo =
    TestInfo(
        testName = testName,
        steps = copySteps(steps),
        throwable = throwable
    )

private fun copySteps(steps: List<StepInfo>): List<StepInfo> {
    val copySteps = ArrayList<StepInfo>(steps.size)
    for (stepInfo in steps) {
        val copyStepInfo = object : StepInfo {
            override val description = stepInfo.description
            override val testClassName = stepInfo.testClassName
            override val level = stepInfo.level
            override val number = stepInfo.number
            override val ordinal = stepInfo.ordinal
            override val subSteps = copySteps(stepInfo.subSteps)
            override val status = stepInfo.status
            override val throwable = stepInfo.throwable
        }
        copySteps.add(copyStepInfo)
    }
    return copySteps
}

