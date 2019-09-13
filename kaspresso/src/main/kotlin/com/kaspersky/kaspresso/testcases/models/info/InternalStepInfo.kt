package com.kaspersky.kaspresso.testcases.models.info

import com.kaspersky.kaspresso.testcases.models.StepStatus

internal class InternalStepInfo(
    override val description: String,
    override val testClassName: String,
    override val level: Int,
    override val number: String,
    override val ordinal: Int,
    override val start: Long,
    // position on each level of step hierarchy
    val stepNumber: MutableList<Int>,
    // internal mutable properties to hide mutability from users
    val parentStepInfo: InternalStepInfo? = null,
    val internalSubStepInfos: MutableList<InternalStepInfo> = mutableListOf(),
    var internalStatus: StepStatus = StepStatus.STARTED,
    var internalThrowable: Throwable? = null,
    var internalStop: Long = -1L
) : StepInfo {

    override val subSteps: List<StepInfo>
        get() = internalSubStepInfos

    override val status: StepStatus
        get() = internalStatus

    override val throwable: Throwable?
        get() = internalThrowable

    override val stop: Long
        get() = internalStop

    override fun toString(): String {
        return "StepInfo(" +
                "description=$description, " +
                "testClassName=$testClassName, " +
                "level=$level, number=$number, " +
                "ordinal=$ordinal, " +
                "stepNumber=$stepNumber, " +
                "subSteps=$internalSubStepInfos" +
                "start=$start" +
                "stop=$internalStop" +
                ")"
    }
}