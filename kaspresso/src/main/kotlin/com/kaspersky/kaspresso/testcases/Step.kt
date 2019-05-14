package com.kaspersky.kaspresso.testcases

import com.kaspersky.kaspresso.interceptors.StepInterceptor

class Step(
    val description: String,
    val action: () -> Unit,
    val testClassName: String,
    val level: Int,
    val orderOnLevel: Int,
    val ordinal: Int,
    private val interceptors: List<StepInterceptor>
) {
    fun proceed() {
        interceptors.forEach { it.interceptBefore(this) }

        try {
            action.invoke()
            interceptors.forEach { it.interceptAfterWithSuccess(this) }
        } catch (e: Throwable) {
            interceptors.forEach { it.interceptAfterWithError(this, e) }
            throw e
        }
    }
}