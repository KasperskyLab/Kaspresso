package com.kaspersky.kaspresso.testcases.core

import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.extensions.other.forEachSafely
import com.kaspersky.kaspresso.extensions.other.invokeSafely
import com.kaspersky.kaspresso.extensions.other.throwAll
import com.kaspersky.kaspresso.interceptors.StepInterceptor
import com.kaspersky.kaspresso.testcases.BaseScenario

class TestContext<MainSectionData> constructor(
    private val stepProducer: StepProducer,
    private val mainSectionData: MainSectionData?
) {

    val data: MainSectionData
        get() = mainSectionData ?: throw IllegalStateException(
            "Unable access data. Please check you " +
                    "have overridden provideMainDataProducer method at testcase or base test rule"
        )


    private val interceptors: List<StepInterceptor> = Configurator.stepInterceptors

    /**
     * A representation of a [TestContext]'s step.
     *
     * @param description a description of a step.
     * @param actions a set of actions of a step.
     */
    fun step(description: String, actions: () -> Unit) {

        val exceptions: MutableList<Throwable> = mutableListOf()

        val stepInfo = stepProducer.produceStep(description)

        interceptors.forEachSafely(exceptions) { it.interceptBefore(stepInfo) }

        try {
            actions.invoke()
            stepProducer.onStepFinished(stepInfo)

            interceptors.forEach {
                invokeSafely(exceptions) { it.interceptAfterWithSuccess(stepInfo) }
                invokeSafely(exceptions) { it.interceptAfterFinally(stepInfo) }
            }
        } catch (throwable: Throwable) {
            stepProducer.onStepFinished(stepInfo, throwable)
            interceptors.forEach {
                invokeSafely(exceptions) { it.interceptAfterWithError(stepInfo, throwable) }
                invokeSafely(exceptions) { it.interceptAfterFinally(stepInfo) }
            }
            exceptions.add(throwable)
        }

        exceptions.throwAll()
    }

    fun scenario(scenario: BaseScenario<MainSectionData>) = scenario.invoke(this)
}