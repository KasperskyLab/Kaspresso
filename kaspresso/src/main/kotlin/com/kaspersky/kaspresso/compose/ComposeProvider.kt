package com.kaspersky.kaspresso.compose

import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.ViewInteraction
import com.agoda.kakao.common.actions.BaseActions
import com.agoda.kakao.common.assertions.BaseAssertions
import com.agoda.kakao.intercept.Interceptable
import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.failure.FailureLoggingProvider
import com.kaspersky.kaspresso.failure.withLoggingOnFailureIfNotNull
import com.kaspersky.kaspresso.flakysafety.FlakySafetyProvider
import com.kaspersky.kaspresso.flakysafety.flakySafelyIfNotNull
import com.kaspersky.kaspresso.interceptors.behavior.ViewBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behavior.impl.failure.FailureLoggingViewBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behavior.impl.flakysafety.FlakySafeViewBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.tokakao.compose.ComposeKakaoViewInterceptor
import com.kaspersky.kaspresso.interceptors.tokakao.impl.KakaoViewInterceptor

interface ComposeProvider {

    val configurator: Configurator

    fun <T> compose(block: ComponentPack<T>.() -> Unit): Unit
            where T : BaseActions, T : BaseAssertions, T : Interceptable<ViewInteraction, ViewAssertion, ViewAction> {

        val components: List<Component<T>> = ComponentPack<T>().apply(block).build()

        components.forEach { it.element.setComposeInterception() }

        val (flakySafetyProvider, failureLoggingProvider) = getProviders()

        failureLoggingProvider.withLoggingOnFailureIfNotNull {
            flakySafetyProvider.flakySafelyIfNotNull {
                invokeComposed(components)
            }
        }

        components.forEach { it.element.setInterception() }
    }

    fun <T> T.compose(block: ActionsPack<T>.() -> Unit): Unit
            where T : BaseActions, T : BaseAssertions, T : Interceptable<ViewInteraction, ViewAssertion, ViewAction> {

        val actions: List<T.() -> Unit> = ActionsPack<T>().apply(block).build()

        setComposeInterception()

        val (flakySafetyProvider, failureLoggingProvider) = getProviders()

        failureLoggingProvider.withLoggingOnFailureIfNotNull {
            flakySafetyProvider.flakySafelyIfNotNull {
                invokeComposed(this, actions)
            }
        }

        setInterception()
    }

    private fun getProviders(): Pair<FlakySafetyProvider?, FailureLoggingProvider?> {
        var flakySafetyProvider: FlakySafetyProvider? = null
        var failureLoggingProvider: FailureLoggingProvider? = null

        configurator.viewBehaviorInterceptors.forEach { viewBehaviorInterceptor: ViewBehaviorInterceptor ->
            when (viewBehaviorInterceptor) {
                is FlakySafeViewBehaviorInterceptor -> flakySafetyProvider = viewBehaviorInterceptor
                is FailureLoggingViewBehaviorInterceptor -> failureLoggingProvider = viewBehaviorInterceptor
            }
        }

        return Pair(flakySafetyProvider, failureLoggingProvider)
    }

    private fun <T> T.setComposeInterception(): Unit
            where T : BaseActions, T : BaseAssertions, T : Interceptable<ViewInteraction, ViewAssertion, ViewAction> {

        val interceptor = ComposeKakaoViewInterceptor(configurator)

        intercept {
            onCheck(true, interceptor::interceptCheck)
            onPerform(true, interceptor::interceptPerform)
        }
    }

    private fun <T> T.setInterception(): Unit
            where T : BaseActions, T : BaseAssertions, T : Interceptable<ViewInteraction, ViewAssertion, ViewAction> {

        val interceptor = KakaoViewInterceptor(configurator)

        intercept {
            onCheck(true, interceptor::interceptCheck)
            onPerform(true, interceptor::interceptPerform)
        }
    }
}