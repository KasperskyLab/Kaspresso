package com.kaspersky.kaspresso.interceptors

import com.kaspersky.kaspresso.testcases.Step

interface StepInterceptor {

    fun interceptBefore(step: Step) = Unit

    fun interceptAfterWithSuccess(step: Step) = Unit

    fun interceptAfterWithError(step: Step, error: Throwable) = Unit

    fun interceptAfter(step: Step) = Unit
}