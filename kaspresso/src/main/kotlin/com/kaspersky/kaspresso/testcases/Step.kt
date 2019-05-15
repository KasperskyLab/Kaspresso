package com.kaspersky.kaspresso.testcases

import com.kaspersky.kaspresso.extensions.other.invokeSafely
import com.kaspersky.kaspresso.extensions.other.throwAll
import com.kaspersky.kaspresso.interceptors.StepInterceptor
import io.reactivex.exceptions.CompositeException

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
        val exceptions: MutableList<Throwable> = mutableListOf()

        interceptors.forEach {
            invokeSafely(exceptions) { it.interceptBefore(this) }
        }

        try {
            action.invoke()
            interceptors.forEach {
                invokeSafely(exceptions) { it.interceptAfterWithSuccess(this) }
                invokeSafely(exceptions) { it.interceptAfterFinally(this) }
            }
        } catch (throwable: Throwable) {
            interceptors.forEach {
                invokeSafely(exceptions) { it.interceptAfterWithError(this, throwable) }
                invokeSafely(exceptions) { it.interceptAfterFinally(this) }
            }
            exceptions.add(throwable)
        }

        exceptions.throwAll()
    }
}