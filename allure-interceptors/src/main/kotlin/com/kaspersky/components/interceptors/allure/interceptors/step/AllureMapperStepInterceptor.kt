package com.kaspersky.components.interceptors.allure.interceptors.step

import com.kaspersky.kaspresso.interceptors.watcher.testcase.StepWatcherInterceptor
import com.kaspersky.kaspresso.testcases.models.info.StepInfo
import io.qameta.allure.android.AllureAndroidLifecycle
import io.qameta.allure.kotlin.model.Status
import io.qameta.allure.kotlin.model.StepResult
import java.util.Stack
import java.util.UUID

class AllureMapperStepInterceptor : StepWatcherInterceptor {

    private val lifecycle = AllureAndroidLifecycle

    private val uuids: Stack<String> = Stack()

    override fun interceptBefore(stepInfo: StepInfo) {
        val uuid = UUID.randomUUID().toString()
        uuids.push(uuid)
        lifecycle.startStep(uuid, StepResult().apply { this.name = stepInfo.description })
    }

    override fun interceptAfterWithSuccess(stepInfo: StepInfo) {
        val current = uuids.peek()
        lifecycle.updateStep(current) {
            it.status = Status.PASSED
        }
    }

    override fun interceptAfterWithError(stepInfo: StepInfo, error: Throwable) {
        val current = uuids.peek()
        lifecycle.updateStep(current) {
            it.status = Status.FAILED
        }
    }

    override fun interceptAfterFinally(stepInfo: StepInfo) {
        val uuid = uuids.pop()
        lifecycle.stopStep(uuid)
    }
}
