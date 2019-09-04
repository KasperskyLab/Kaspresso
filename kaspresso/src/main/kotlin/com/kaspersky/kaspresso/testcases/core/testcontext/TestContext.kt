package com.kaspersky.kaspresso.testcases.core.testcontext

import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.internal.extensions.other.forEachSafely
import com.kaspersky.kaspresso.internal.extensions.other.invokeSafely
import com.kaspersky.kaspresso.internal.extensions.other.throwAll
import com.kaspersky.kaspresso.interceptors.testcase.StepInterceptor
import com.kaspersky.kaspresso.testcases.api.scenario.BaseScenario
import com.kaspersky.kaspresso.testcases.core.step.StepInfoProducer

/**
 * Special class to operate with in user scenario.
 *
 * @param Data data created in before section.
 */
class TestContext<Data> internal constructor(
    configurator: Configurator,
    private val stepInfoProducer: StepInfoProducer,
    val data: Data
) : BaseTestContext(configurator) {

    private val interceptors: List<StepInterceptor> = configurator.stepInterceptors

    /**
     * A representation of a [TestContext]'s step.
     *
     * @param description a description of a step.
     * @param actions a set of actions of a step.
     */
    fun step(description: String, actions: () -> Unit) {

        val exceptions: MutableList<Throwable> = mutableListOf()

        val stepInfo = stepInfoProducer.produceStepInfo(description)

        interceptors.forEachSafely(exceptions) { it.interceptBefore(stepInfo) }

        try {
            actions.invoke()
            stepInfoProducer.onStepFinished(stepInfo)

            interceptors.forEach {
                invokeSafely(exceptions) { it.interceptAfterWithSuccess(stepInfo) }
                invokeSafely(exceptions) { it.interceptAfterFinally(stepInfo) }
            }
        } catch (throwable: Throwable) {
            stepInfoProducer.onStepFinished(stepInfo, throwable)
            interceptors.forEach {
                invokeSafely(exceptions) { it.interceptAfterWithError(stepInfo, throwable) }
                invokeSafely(exceptions) { it.interceptAfterFinally(stepInfo) }
            }

            exceptions.add(throwable)
        }

        exceptions.throwAll()
    }

    fun scenario(scenario: BaseScenario<Data>) = scenario.invoke(this)
}