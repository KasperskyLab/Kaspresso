package com.kaspersky.kaspresso.report.impl

import com.kaspersky.kaspresso.testcases.models.StepStatus.FAILED
import com.kaspersky.kaspresso.testcases.models.StepStatus.STARTED
import com.kaspersky.kaspresso.testcases.models.StepStatus.SUCCESS
import com.kaspersky.kaspresso.testcases.models.info.StepInfo
import io.qameta.allure.model.Stage
import io.qameta.allure.model.Status
import io.qameta.allure.model.StatusDetails
import io.qameta.allure.model.StepResult

/**
 * Converts [com.kaspersky.kaspresso.testcases.models.info.StepInfo] into
 * [io.qameta.allure.model.StepResult] JSON model.
 */
class StepInfoConverter {

    fun convert(stepInfo: StepInfo): StepResult {
        return StepResult().apply {
            name = stepInfo.description
            status = when (stepInfo.status) {
                STARTED -> Status.BROKEN
                SUCCESS -> Status.PASSED
                FAILED -> Status.FAILED
            }
            if (status == Status.FAILED) {
                statusDetails = StatusDetails(
                    message = stepInfo.throwable?.message,
                    trace = stepInfo.throwable?.stackTrace?.toString()
                )
            }
            start = stepInfo.startTime
            stop = stepInfo.stopTime
            stage = Stage.FINISHED
            steps = stepInfo.subSteps.mapTo(mutableListOf()) { convert(it) }
        }
    }
}