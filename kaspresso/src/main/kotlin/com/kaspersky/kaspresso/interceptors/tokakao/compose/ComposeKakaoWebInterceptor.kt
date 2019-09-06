package com.kaspersky.kaspresso.interceptors.tokakao.compose

import androidx.test.espresso.web.assertion.WebAssertion
import androidx.test.espresso.web.assertion.WebAssertionProxy
import androidx.test.espresso.web.model.Atom
import androidx.test.espresso.web.sugar.Web
import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.interceptors.behavior.WebBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behavior.impl.failure.FailureLoggingWebBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behavior.impl.flakysafety.FlakySafeWebBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.tokakao.KakaoInterceptor
import com.kaspersky.kaspresso.internal.extensions.espressoext.getMatcher
import com.kaspersky.kaspresso.proxy.AtomProxy

internal class ComposeKakaoWebInterceptor(
    configurator: Configurator
) : KakaoInterceptor<Web.WebInteraction<*>, Atom<*>, WebAssertion<*>>(configurator) {

    override fun interceptCheck(interaction: Web.WebInteraction<*>, assertion: WebAssertion<*>) {
        configurator.webBehaviorInterceptors
            .filter { it !is FlakySafeWebBehaviorInterceptor && it !is FailureLoggingWebBehaviorInterceptor }
            .fold(
                initial = {
                    interaction.check(
                        WebAssertionProxy(
                            assertion,
                            interaction.getMatcher(),
                            configurator.webAssertionWatcherInterceptors
                        )
                    )
                },
                operation = { acc, webBehaviorInterceptor: WebBehaviorInterceptor ->
                    { webBehaviorInterceptor.intercept(interaction, acc) }
                }
            ).invoke()
    }

    override fun interceptPerform(interaction: Web.WebInteraction<*>, action: Atom<*>) {
        configurator.webBehaviorInterceptors
            .filter { it !is FlakySafeWebBehaviorInterceptor && it !is FailureLoggingWebBehaviorInterceptor }
            .fold(
                initial = {
                    interaction.perform(
                        AtomProxy(action, interaction.getMatcher(), configurator.atomWatcherInterceptors)
                    )
                },
                operation = { acc, webBehaviorInterceptor: WebBehaviorInterceptor ->
                    { webBehaviorInterceptor.intercept(interaction, acc) }
                }
            ).invoke()
    }
}