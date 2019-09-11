package com.kaspersky.kaspresso.interceptors.tokakao.compose

import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.ViewInteraction
import com.kaspersky.kaspresso.interceptors.behavior.ViewBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behavior.impl.failure.FailureLoggingViewBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behavior.impl.flakysafety.FlakySafeViewBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.tokakao.KakaoInterceptor
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.proxy.ViewActionProxy
import com.kaspersky.kaspresso.proxy.ViewAssertionProxy

/**
 * Kaspresso's implementation of Kakao's view interaction interceptor that used while composing multiple
 * actions or assertions.
 */
internal class ComposeKakaoViewInterceptor(
    kaspresso: Kaspresso
) : KakaoInterceptor<ViewInteraction, ViewAction, ViewAssertion>(kaspresso) {

    /**
     * Folds all [ViewBehaviorInterceptor]'s
     * (except [FlakySafeViewBehaviorInterceptor] and [FailureLoggingViewBehaviorInterceptor])
     * one into another in the order from the first to the last with the actual
     * [androidx.test.espresso.ViewInteraction.check] call as the initial, and invokes the resulting lambda.
     * [FlakySafeViewBehaviorInterceptor] and [FailureLoggingViewBehaviorInterceptor] are excepted because they should
     * not intercept each of composing actions or assertions but only the whole composed.
     */
    override fun interceptCheck(interaction: ViewInteraction, assertion: ViewAssertion) {
        kaspresso.viewBehaviorInterceptors
            .filter { it !is FlakySafeViewBehaviorInterceptor && it !is FailureLoggingViewBehaviorInterceptor }
            .fold(
                initial = {
                    interaction.check(ViewAssertionProxy(assertion, kaspresso.viewAssertionWatcherInterceptors))
                },
                operation = { acc, viewBehaviorInterceptor: ViewBehaviorInterceptor ->
                    { viewBehaviorInterceptor.intercept(interaction, acc) }
                }
            ).invoke()
    }

    /**
     * Folds all [ViewBehaviorInterceptor]'s
     * (except [FlakySafeViewBehaviorInterceptor] and [FailureLoggingViewBehaviorInterceptor])
     * one into another in the order from the first to the last  with the actual
     * [androidx.test.espresso.ViewInteraction.perform] call as the initial, and invokes the resulting lambda.
     * [FlakySafeViewBehaviorInterceptor] and [FailureLoggingViewBehaviorInterceptor] are excepted because they should
     * not intercept each of composing actions or assertions but only the whole composed.
     */
    override fun interceptPerform(interaction: ViewInteraction, action: ViewAction) {
        kaspresso.viewBehaviorInterceptors
            .filter { it !is FlakySafeViewBehaviorInterceptor && it !is FailureLoggingViewBehaviorInterceptor }
            .fold(
                initial = {
                    interaction.perform(ViewActionProxy(action, kaspresso.viewActionWatcherInterceptors))
                },
                operation = { acc, viewBehaviorInterceptor: ViewBehaviorInterceptor ->
                    { viewBehaviorInterceptor.intercept(interaction, acc) }
                }
            ).invoke()
    }
}