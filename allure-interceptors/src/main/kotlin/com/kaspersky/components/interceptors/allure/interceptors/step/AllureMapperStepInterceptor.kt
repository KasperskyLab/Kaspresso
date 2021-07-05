package com.kaspersky.components.interceptors.allure.interceptors.step

import com.kaspersky.kaspresso.interceptors.watcher.testcase.StepWatcherInterceptor
import com.kaspersky.kaspresso.testcases.models.info.StepInfo
import io.qameta.allure.android.AllureAndroidLifecycle
import io.qameta.allure.kotlin.model.Status
import io.qameta.allure.kotlin.model.StepResult
import java.util.UUID

class AllureMapperStepInterceptor : StepWatcherInterceptor {

    private val lifecycle = AllureAndroidLifecycle

    private var _uuid: String? = null
    private val uuid: String
        get() = requireNotNull(_uuid)

    override fun interceptBefore(stepInfo: StepInfo) {
        _uuid = UUID.randomUUID().toString()
        lifecycle.startStep(uuid, StepResult().apply { this.name = stepInfo.description })
    }

    override fun interceptAfterWithSuccess(stepInfo: StepInfo) {
        lifecycle.updateStep(uuid) {
            it.status = Status.PASSED
        }
    }

    override fun interceptAfterWithError(stepInfo: StepInfo, error: Throwable) {
        lifecycle.updateStep(uuid) {
            it.status = Status.FAILED
        }
    }

    override fun interceptAfterFinally(stepInfo: StepInfo) {
        lifecycle.stopStep(uuid)
        _uuid = null
    }
}
