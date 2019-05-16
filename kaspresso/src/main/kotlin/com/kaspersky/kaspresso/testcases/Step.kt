package com.kaspersky.kaspresso.testcases

import com.kaspersky.kaspresso.extensions.other.forEachSafely
import com.kaspersky.kaspresso.extensions.other.invokeSafely
import com.kaspersky.kaspresso.extensions.other.throwAll
import com.kaspersky.kaspresso.interceptors.StepInterceptor

class Step(
    val action: () -> Unit,
    val stepInfo: StepInfo,
    private val interceptors: List<StepInterceptor>
) {
    fun proceed() {
        val exceptions: MutableList<Throwable> = mutableListOf()

        interceptors.forEachSafely(exceptions) { it.interceptBefore(stepInfo) }

        try {
            action.invoke()
            interceptors.forEach {
                invokeSafely(exceptions) { it.interceptAfterWithSuccess(stepInfo) }
                invokeSafely(exceptions) { it.interceptAfterFinally(stepInfo) }
            }
        } catch (throwable: Throwable) {
            interceptors.forEach {
                invokeSafely(exceptions) { it.interceptAfterWithError(stepInfo, throwable) }
                invokeSafely(exceptions) { it.interceptAfterFinally(stepInfo) }
            }
            exceptions.add(throwable)
        }

        exceptions.throwAll()
    }
}