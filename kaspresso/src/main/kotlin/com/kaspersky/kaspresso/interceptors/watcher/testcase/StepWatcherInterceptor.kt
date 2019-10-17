package com.kaspersky.kaspresso.interceptors.watcher.testcase

import com.kaspersky.kaspresso.testcases.core.testcontext.BaseTestContext
import com.kaspersky.kaspresso.testcases.models.info.StepInfo

/**
 * The interface for all interceptors intercepting step events.
 */
interface StepWatcherInterceptor {

    fun BaseTestContext.interceptBefore(stepInfo: StepInfo) = Unit

    fun BaseTestContext.interceptAfterWithSuccess(stepInfo: StepInfo) = Unit

    fun BaseTestContext.interceptAfterWithError(stepInfo: StepInfo, error: Throwable) = Unit

    fun BaseTestContext.interceptAfterFinally(stepInfo: StepInfo) = Unit
}