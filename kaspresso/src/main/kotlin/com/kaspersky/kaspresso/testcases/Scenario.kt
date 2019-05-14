package com.kaspersky.kaspresso.testcases

import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.testcases.step.ExecutionInterceptor
import com.kaspersky.kaspresso.testcases.step.RealChainInterceptor
import com.kaspersky.kaspresso.testcases.step.StepInterceptor

/**
 * A representation of a sequence of test's actions.
 */
class Scenario constructor(private val title: String) {

    private val interceptors: List<StepInterceptor> = Configurator.stepInterceptors + ExecutionInterceptor()

    /**
     * A step counter to evaluate current step's tag.
     */
    private var stepsCounter: Int = 0

    /**
     * A representation of a [Scenario]'s step.
     *
     * @param description a description of a step.
     * @param actions a set of actions of a step.
     */

    fun step(description: String, action: () -> Unit) {
        val realChainInterceptor = RealChainInterceptor(
            description = description,
            action = action,
            testClassName = title,
            stepLevel = 0, //TODO calculate
            stepOrderOnLevel = 0, //TODO calculate
            ordinal = ++stepsCounter,
            index = 0,
            interceptors = interceptors
        )
        realChainInterceptor.proceed(action)
    }
}