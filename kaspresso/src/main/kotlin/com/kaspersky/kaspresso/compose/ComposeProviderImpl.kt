package com.kaspersky.kaspresso.compose

import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.ViewInteraction
import com.agoda.kakao.common.actions.BaseActions
import com.agoda.kakao.common.assertions.BaseAssertions
import com.agoda.kakao.intercept.Interceptable
import com.kaspersky.kaspresso.compose.pack.ActionsOnElementsPack
import com.kaspersky.kaspresso.compose.pack.ActionsPack
import com.kaspersky.kaspresso.failure.FailureLoggingProvider
import com.kaspersky.kaspresso.failure.withLoggingOnFailureIfNotNull
import com.kaspersky.kaspresso.flakysafety.FlakySafetyProvider
import com.kaspersky.kaspresso.flakysafety.flakySafelyIfNotNull
import com.kaspersky.kaspresso.interceptors.behavior.ViewBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behavior.impl.failure.FailureLoggingViewBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behavior.impl.flakysafety.FlakySafeViewBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.tokakao.compose.ComposeKakaoViewInterceptor
import com.kaspersky.kaspresso.interceptors.tokakao.impl.KakaoViewInterceptor
import com.kaspersky.kaspresso.kaspresso.Kaspresso

/**
 * The implementation of the [ComposeProvider] interface.
 */
class ComposeProviderImpl(
    private val kaspresso: Kaspresso
) : ComposeProvider {

    /**
     * Composes a [block] of actions with their views to invoke on in one composite action that succeeds if at least
     * one of it's parts succeeds.
     *
     * @param block the actions to compose.
     */
    override fun compose(block: ActionsOnElementsPack.() -> Unit) {
        val (elements, actions) = ActionsOnElementsPack().apply(block).build()

        elements.forEach { it.setComposeInterception() }

        val (flakySafetyProvider, failureLoggingProvider) = getProviders()

        failureLoggingProvider.withLoggingOnFailureIfNotNull {
            flakySafetyProvider.flakySafelyIfNotNull {
                invokeComposed(actions, kaspresso.libLogger)
            }
        }

        elements.forEach { it.setInterception() }
    }

    /**
     * Composes a [block] of actions on the given view of type [T] in one composite action that succeeds if at least
     * one of it's parts succeeds.
     *
     * @param block the actions to compose.
     */
    override fun <T> T.compose(block: ActionsPack<T>.() -> Unit): Unit
            where T : BaseActions, T : BaseAssertions, T : Interceptable<ViewInteraction, ViewAssertion, ViewAction> {

        val actions: List<() -> Unit> = ActionsPack(this).apply(block).build()

        setComposeInterception()

        val (flakySafetyProvider, failureLoggingProvider) = getProviders()

        failureLoggingProvider.withLoggingOnFailureIfNotNull {
            flakySafetyProvider.flakySafelyIfNotNull {
                invokeComposed(actions, kaspresso.libLogger)
            }
        }

        setInterception()
    }

    private fun getProviders(): Pair<FlakySafetyProvider?, FailureLoggingProvider?> {
        var flakySafetyProvider: FlakySafetyProvider? = null
        var failureLoggingProvider: FailureLoggingProvider? = null

        kaspresso.viewBehaviorInterceptors.forEach { viewBehaviorInterceptor: ViewBehaviorInterceptor ->
            when (viewBehaviorInterceptor) {
                is FlakySafeViewBehaviorInterceptor -> flakySafetyProvider = viewBehaviorInterceptor
                is FailureLoggingViewBehaviorInterceptor -> failureLoggingProvider = viewBehaviorInterceptor
            }
        }

        return Pair(flakySafetyProvider, failureLoggingProvider)
    }

    private fun Interceptable<ViewInteraction, ViewAssertion, ViewAction>.setComposeInterception() {
        val interceptor = ComposeKakaoViewInterceptor(kaspresso)

        intercept {
            onCheck(true, interceptor::interceptCheck)
            onPerform(true, interceptor::interceptPerform)
        }
    }

    private fun Interceptable<ViewInteraction, ViewAssertion, ViewAction>.setInterception() {
        val interceptor = KakaoViewInterceptor(kaspresso)

        intercept {
            onCheck(true, interceptor::interceptCheck)
            onPerform(true, interceptor::interceptPerform)
        }
    }
}