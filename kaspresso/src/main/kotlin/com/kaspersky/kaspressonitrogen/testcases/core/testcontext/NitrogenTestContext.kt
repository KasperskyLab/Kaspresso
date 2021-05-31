package com.kaspersky.kaspressonitrogen.testcases.core.testcontext

import com.kaspersky.kaspresso.interceptors.watcher.testcase.StepWatcherInterceptor
import com.kaspersky.kaspresso.internal.extensions.other.forEachSafely
import com.kaspersky.kaspresso.internal.extensions.other.invokeSafely
import com.kaspersky.kaspresso.internal.extensions.other.throwAll
import com.kaspersky.kaspressonitrogen.kaspresso.KaspressoNitrogen
import com.kaspersky.kaspresso.testcases.api.scenario.BaseScenario
import com.kaspersky.kaspressonitrogen.testcases.api.scenario.NitrogenBaseScenario
import com.kaspersky.kaspresso.testcases.core.step.StepInfoProducer
import com.kaspersky.kaspresso.testcases.models.info.StepInfo

class NitrogenTestContext<Data> internal constructor(
    kaspresso: KaspressoNitrogen,
    private val stepInfoProducer: StepInfoProducer,
    val data: Data
) : NitrogenBaseTestContext(kaspresso) {

    private val stepInterceptors: List<StepWatcherInterceptor> = kaspresso.stepWatcherInterceptors

    /**
     * The representation of a [NitrogenTestContext]'s step.
     *
     * @param description a description of a step.
     * @param actions a set of actions of a step.
     */
    fun step(description: String, actions: (StepInfo) -> Unit) {
        val exceptions: MutableList<Throwable> = mutableListOf()
        val stepInfo = stepInfoProducer.produceStepInfo(description)

        stepInterceptors.forEachSafely(exceptions) { it.interceptBefore(stepInfo) }

        try {
            actions.invoke(stepInfo)
            stepInfoProducer.onStepFinished(stepInfo)

            stepInterceptors.forEach {
                invokeSafely(exceptions) { it.interceptAfterWithSuccess(stepInfo) }
                invokeSafely(exceptions) { it.interceptAfterFinally(stepInfo) }
            }
        } catch (throwable: Throwable) {
            stepInfoProducer.onStepFinished(stepInfo, throwable)
            stepInterceptors.forEach {
                invokeSafely(exceptions) { it.interceptAfterWithError(stepInfo, throwable) }
                invokeSafely(exceptions) { it.interceptAfterFinally(stepInfo) }
            }

            exceptions.add(throwable)
        }

        exceptions.throwAll()
    }

    /**
     * The representation of a composed [NitrogenTestContext]'s steps.
     *
     * @param scenario the implementation of [BaseScenario].
     */
    fun scenario(scenario: NitrogenBaseScenario<Data>) = scenario.invoke(this)
}
