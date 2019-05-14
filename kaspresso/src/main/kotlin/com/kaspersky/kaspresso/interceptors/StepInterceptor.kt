package com.kaspersky.kaspresso.interceptors

import com.kaspersky.kaspresso.testcases.Step

interface StepInterceptor {

    fun interceptBefore(step: Step)

    fun interceptAfterWithSuccess(step: Step)

    fun interceptAfterWithError(step: Step, error: Throwable)
}