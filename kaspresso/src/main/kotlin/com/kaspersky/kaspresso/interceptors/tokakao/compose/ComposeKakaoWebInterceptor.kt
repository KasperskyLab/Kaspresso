package com.kaspersky.kaspresso.interceptors.tokakao.compose

import androidx.test.espresso.web.assertion.WebAssertion
import androidx.test.espresso.web.assertion.WebAssertionProxy
import androidx.test.espresso.web.model.Atom
import androidx.test.espresso.web.sugar.Web
import com.kaspersky.kaspresso.interceptors.behavior.WebBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behavior.impl.failure.FailureLoggingWebBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behavior.impl.flakysafety.FlakySafeWebBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.tokakao.KakaoInterceptor
import com.kaspersky.kaspresso.internal.extensions.espressoext.getMatcher
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.proxy.AtomProxy

/**
 * Kaspresso's implementation of Kakao's web interaction interceptor that used while composing multiple
 * actions or assertions.
 */
internal class ComposeKakaoWebInterceptor(
    kaspresso: Kaspresso
) : KakaoInterceptor<Web.WebInteraction<*>, Atom<*>, WebAssertion<*>>(kaspresso) {

    /**
     * Folds all [WebBehaviorInterceptor]'s
     * (except [FlakySafeWebBehaviorInterceptor] and [FailureLoggingWebBehaviorInterceptor])
     * one into another in the order from the first to the last with the actual
     * [androidx.test.espresso.web.sugar.Web.WebInteraction.check]  call as the initial, and invokes the resulting
     * lambda. [FlakySafeWebBehaviorInterceptor] and [FailureLoggingWebBehaviorInterceptor] are excepted because they
     * should not intercept each of composing actions or assertions but only the whole composed.
     */
    override fun interceptCheck(interaction: Web.WebInteraction<*>, assertion: WebAssertion<*>) {
        kaspresso.webBehaviorInterceptors
            .filter { it !is FlakySafeWebBehaviorInterceptor && it !is FailureLoggingWebBehaviorInterceptor }
            .fold(
                initial = {
                    interaction.check(
                        WebAssertionProxy(
                            assertion,
                            interaction.getMatcher(),
                            kaspresso.webAssertionWatcherInterceptors
                        )
                    )
                },
                operation = { acc, webBehaviorInterceptor: WebBehaviorInterceptor ->
                    { webBehaviorInterceptor.intercept(interaction, acc) }
                }
            ).invoke()
    }

    /**
     * Folds all [WebBehaviorInterceptor]'s
     * (except [FlakySafeWebBehaviorInterceptor] and [FailureLoggingWebBehaviorInterceptor])
     * one into another in the order from the first to the last with the actual
     * [androidx.test.espresso.web.sugar.Web.WebInteraction.perform] call as the initial, and invokes the resulting
     * lambda. [FlakySafeWebBehaviorInterceptor] and [FailureLoggingWebBehaviorInterceptor] are excepted because they
     * should not intercept each of composing actions or assertions but only the whole composed.
     */
    override fun interceptPerform(interaction: Web.WebInteraction<*>, action: Atom<*>) {
        kaspresso.webBehaviorInterceptors
            .filter { it !is FlakySafeWebBehaviorInterceptor && it !is FailureLoggingWebBehaviorInterceptor }
            .fold(
                initial = {
                    interaction.perform(
                        AtomProxy(action, interaction.getMatcher(), kaspresso.atomWatcherInterceptors)
                    )
                },
                operation = { acc, webBehaviorInterceptor: WebBehaviorInterceptor ->
                    { webBehaviorInterceptor.intercept(interaction, acc) }
                }
            ).invoke()
    }
}