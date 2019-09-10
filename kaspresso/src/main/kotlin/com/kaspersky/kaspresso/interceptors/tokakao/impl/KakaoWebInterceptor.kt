package com.kaspersky.kaspresso.interceptors.tokakao.impl

import androidx.test.espresso.web.assertion.WebAssertion
import androidx.test.espresso.web.assertion.WebAssertionProxy
import androidx.test.espresso.web.model.Atom
import androidx.test.espresso.web.sugar.Web
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.interceptors.behavior.WebBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.tokakao.KakaoInterceptor
import com.kaspersky.kaspresso.internal.extensions.espressoext.getMatcher
import com.kaspersky.kaspresso.proxy.AtomProxy

internal class KakaoWebInterceptor(
    kaspresso: Kaspresso
) : KakaoInterceptor<Web.WebInteraction<*>, Atom<*>, WebAssertion<*>>(kaspresso) {

    override fun interceptCheck(interaction: Web.WebInteraction<*>, assertion: WebAssertion<*>) {
        kaspresso.webBehaviorInterceptors.fold(
            initial = {
                interaction.check(
                    WebAssertionProxy(assertion, interaction.getMatcher(), kaspresso.webAssertionWatcherInterceptors)
                )
            },
            operation = { acc, webBehaviorInterceptor: WebBehaviorInterceptor ->
                { webBehaviorInterceptor.intercept(interaction, acc) }
            }
        ).invoke()
    }

    override fun interceptPerform(interaction: Web.WebInteraction<*>, action: Atom<*>) {
        kaspresso.webBehaviorInterceptors.fold(
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