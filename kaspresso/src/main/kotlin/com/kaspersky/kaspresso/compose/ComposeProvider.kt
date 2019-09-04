package com.kaspersky.kaspresso.compose

import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.ViewInteraction
import com.agoda.kakao.common.actions.BaseActions
import com.agoda.kakao.common.assertions.BaseAssertions
import com.agoda.kakao.intercept.Interceptable
import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.failure.FailureLoggingProvider
import com.kaspersky.kaspresso.flakysafety.FlakySafetyProvider
import com.kaspersky.kaspresso.interceptors.interaction.impl.ViewInteractionInterceptor
import com.kaspersky.kaspresso.interceptors.interaction.impl.compose.ComposeViewInteractionInterceptor
import com.kaspersky.kaspresso.interceptors.interactors.impl.failure.FailureLoggingViewInteractor
import com.kaspersky.kaspresso.interceptors.interactors.impl.flakysafety.FlakySafeViewInteractor

interface ComposeProvider {

    val configurator: Configurator

    private val flakySafetyProvider: FlakySafetyProvider?
        get() = configurator.viewInteractors.find { it is FlakySafeViewInteractor } as FlakySafetyProvider?

    private val failureLoggingProvider: FailureLoggingProvider?
        get() = configurator.viewInteractors.find { it is FailureLoggingViewInteractor } as FailureLoggingProvider?

    fun <T> compose(block: ComponentPack<T>.() -> Unit): Unit
            where T : BaseActions, T : BaseAssertions, T : Interceptable<ViewInteraction, ViewAssertion, ViewAction> {

        val components: List<Component<T>> = ComponentPack<T>().apply(block).build()

        components.forEach { it.element.setComposeInterception() }

        val composedAction: () -> Unit = {
            flakySafetyProvider
                ?.flakySafely { invokeComposed(components) }
                ?: invokeComposed(components)
        }

        failureLoggingProvider
            ?.withLoggingOnFailure(composedAction)
            ?: composedAction.invoke()


        components.forEach { it.element.setInterception() }
    }

    fun <T> T.compose(block: ActionsPack<T>.() -> Unit): Unit
            where T : BaseActions, T : BaseAssertions, T : Interceptable<ViewInteraction, ViewAssertion, ViewAction> {

        val actions: List<T.() -> Unit> = ActionsPack<T>().apply(block).build()

        setComposeInterception()

        val composedAction: () -> Unit = {
            flakySafetyProvider
                ?.flakySafely { invokeComposed(this, actions) }
                ?: invokeComposed(this, actions)
        }

        failureLoggingProvider
            ?.withLoggingOnFailure(composedAction)
            ?: composedAction.invoke()

        setInterception()
    }

    private fun <T> T.setComposeInterception(): Unit
            where T : BaseActions, T : BaseAssertions, T : Interceptable<ViewInteraction, ViewAssertion, ViewAction> {

        val interceptor = ComposeViewInteractionInterceptor(configurator)

        intercept {
            onCheck(true, interceptor::interceptCheck)
            onPerform(true, interceptor::interceptPerform)
        }
    }

    private fun <T> T.setInterception(): Unit
            where T : BaseActions, T : BaseAssertions, T : Interceptable<ViewInteraction, ViewAssertion, ViewAction> {

        val interceptor = ViewInteractionInterceptor(configurator)

        intercept {
            onCheck(true, interceptor::interceptCheck)
            onPerform(true, interceptor::interceptPerform)
        }
    }
}