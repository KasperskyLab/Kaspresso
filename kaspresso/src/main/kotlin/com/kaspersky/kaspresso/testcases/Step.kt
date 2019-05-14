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
            interceptors.forEach { it.interceptAfterWithSuccess(this) }
        } catch (throwable: Throwable) {
            interceptors.forEach { it.interceptAfterWithError(this, throwable) }
            throw  throwable
        } finally {

        }
    }
}