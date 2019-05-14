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

        var error: Throwable? = null

        try {
            action.invoke()
        } catch (e: Throwable) {
            error = e
        }

        if (error == null) {
            interceptors.forEach { it.interceptAfterWithSuccess(this) }
        } else {
            interceptors.forEach { it.interceptAfterWithError(this, error) }
            throw error
        }
    }
}