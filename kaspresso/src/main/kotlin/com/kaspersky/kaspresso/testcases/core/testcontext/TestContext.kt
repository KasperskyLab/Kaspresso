package com.kaspersky.kaspresso.testcases.core.testcontext

import com.kaspersky.kaspresso.interceptors.watcher.testcase.StepWatcherInterceptor
import com.kaspersky.kaspresso.internal.extensions.other.forEachSafely
import com.kaspersky.kaspresso.internal.extensions.other.invokeSafely
import com.kaspersky.kaspresso.internal.extensions.other.throwAll
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.testcases.api.scenario.BaseScenario
import com.kaspersky.kaspresso.testcases.core.step.StepInfoProducer

/**
 * The special class to operate with in user scenario.
 * Provides [step] and [scenario] methods in "run" section to build a test.
 *
 * @param Data data created in before section.
 */
class TestContext<Data> internal constructor(
    kaspresso: Kaspresso,
    private val stepInfoProducer: StepInfoProducer,
    val data: Data
) : BaseTestContext(kaspresso) {

    private val stepInterceptors: List<StepWatcherInterceptor> = kaspresso.stepWatcherInterceptors

    /**
     * The representation of a [TestContext]'s step.
     *
     * @param description a description of a step.
     * @param actions a set of actions of a step.
     */
    fun step(description: String, actions: () -> Unit) {
        val exceptions: MutableList<Throwable> = mutableListOf()
        val stepInfo = stepInfoProducer.produceStepInfo(description)

        stepInterceptors.forEachSafely(exceptions) { it.run { interceptBefore(stepInfo) } }

        try {
            actions.invoke()
            stepInfoProducer.onStepFinished(stepInfo)

            stepInterceptors.forEach {
                invokeSafely(exceptions) { it.run { interceptAfterWithSuccess(stepInfo) } }
                invokeSafely(exceptions) { it.run { interceptAfterFinally(stepInfo) } }
            }
        } catch (throwable: Throwable) {
            stepInfoProducer.onStepFinished(stepInfo, throwable)
            stepInterceptors.forEach {
                invokeSafely(exceptions) { it.run { interceptAfterWithError(stepInfo, throwable) } }
                invokeSafely(exceptions) { it.run { interceptAfterFinally(stepInfo) } }
            }

            exceptions.add(throwable)
        }

        exceptions.throwAll()
    }

    /**
     * The representation of a composed [TestContext]'s steps.
     *
     * @param scenario the implementation of [BaseScenario].
     */
    fun scenario(scenario: BaseScenario<Data>) = scenario.invoke(this)
}