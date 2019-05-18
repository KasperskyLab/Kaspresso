package com.kaspersky.kaspresso.testcases.models

class InternalTestInfo(
    override val testName: String,
    internal val internalSteps: MutableList<StepInfo> = mutableListOf(),
    internal var internalThrowable: Throwable? = null
) : TestInfo {
    override val steps: List<StepInfo>
        get() = internalSteps

    override val throwable: Throwable?
        get() = internalThrowable

    override fun toString(): String {
        return "TestResult(" +
                "testName=$testName, " +
                "internalSteps=$internalSteps, " +
                "internalThrowable=$internalThrowable" +
                ")"
    }
}