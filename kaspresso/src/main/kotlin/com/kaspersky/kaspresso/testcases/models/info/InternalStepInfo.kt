package com.kaspersky.kaspresso.testcases.models.info

import com.kaspersky.kaspresso.testcases.models.StepStatus

internal class InternalStepInfo(
    override val description: String,
    override val testClassName: String,
    override val number: String?,
    override val ordinal: Int,
    override val startTime: Long,
    // position on each level of step hierarchy
    val stepNumber: MutableList<Int>?,
    // internal mutable properties to hide mutability from users
    val parentStepInfo: InternalStepInfo? = null,
    val internalSubStepInfos: MutableList<InternalStepInfo> = mutableListOf(),
    var internalStatus: StepStatus = StepStatus.STARTED,
    var internalThrowable: Throwable? = null,
    var internalStopTime: Long = -1L
) : StepInfo {

    override val subSteps: List<StepInfo>
        get() = internalSubStepInfos

    override val status: StepStatus
        get() = internalStatus

    override val throwable: Throwable?
        get() = internalThrowable

    override val stopTime: Long
        get() = internalStopTime

    override fun toString(): String {
        return "StepInfo(" +
                "description=$description, " +
                "testClassName=$testClassName, " +
                "number=$number, " +
                "ordinal=$ordinal, " +
                "stepNumber=$stepNumber, " +
                "subSteps=$internalSubStepInfos" +
                "startTime=$startTime" +
                "stopTime=$internalStopTime" +
                ")"
    }
}
