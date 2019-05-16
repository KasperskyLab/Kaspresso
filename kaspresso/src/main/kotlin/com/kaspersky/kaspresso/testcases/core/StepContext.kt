package com.kaspersky.kaspresso.testcases.core

import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.extensions.other.forEachSafely
import com.kaspersky.kaspresso.extensions.other.invokeSafely
import com.kaspersky.kaspresso.extensions.other.throwAll
import com.kaspersky.kaspresso.interceptors.StepInterceptor
import com.kaspersky.kaspresso.testcases.models.StepInfo
import com.kaspersky.kaspresso.testcases.models.TestInfo

class StepContext(
    private val testInfo: TestInfo
) {
    /**
     * A step counter to evaluate current step's tag.
     */
    private var stepsCounter: Int = 0

    private val interceptors: List<StepInterceptor> = Configurator.stepInterceptors

    /**
     * A representation of a [StepContext]'s step.
     *
     * @param description a description of a step.
     * @param actions a set of actions of a step.
     */
    fun step(description: String, action: () -> Unit) {

        val exceptions: MutableList<Throwable> = mutableListOf()

        val stepInfo = StepInfo(
            description = description,
            testClassName = testInfo.testName,
            level = 0, //TODO calculate
            orderOnLevel = 0, //TODO calculate
            ordinal = ++stepsCounter
        )

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