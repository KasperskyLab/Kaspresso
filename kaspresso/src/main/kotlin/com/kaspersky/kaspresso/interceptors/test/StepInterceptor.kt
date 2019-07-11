package com.kaspersky.kaspresso.interceptors.test

import com.kaspersky.kaspresso.testcases.models.info.StepInfo

interface StepInterceptor {

    fun interceptBefore(stepInfo: StepInfo) = Unit

    fun interceptAfterWithSuccess(stepInfo: StepInfo) = Unit

    fun interceptAfterWithError(stepInfo: StepInfo, error: Throwable) = Unit

    fun interceptAfterFinally(stepInfo: StepInfo) = Unit
}