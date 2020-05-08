package com.kaspersky.kaspresso.interceptors.watcher.testcase

import com.kaspersky.kaspresso.testcases.models.info.StepInfo

/**
 * The interface for all interceptors intercepting step events.
 */
interface StepWatcherInterceptor {

    fun interceptBefore(stepInfo: StepInfo) = Unit

    fun interceptAfterWithSuccess(stepInfo: StepInfo) = Unit

    fun interceptAfterWithError(stepInfo: StepInfo, error: Throwable) = Unit

    fun interceptAfterFinally(stepInfo: StepInfo) = Unit
}
