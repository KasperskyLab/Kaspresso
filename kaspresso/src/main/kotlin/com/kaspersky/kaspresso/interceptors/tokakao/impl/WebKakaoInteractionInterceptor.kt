package com.kaspersky.kaspresso.interceptors.tokakao.impl

import androidx.test.espresso.web.assertion.WebAssertion
import androidx.test.espresso.web.assertion.WebAssertionProxy
import androidx.test.espresso.web.model.Atom
import androidx.test.espresso.web.sugar.Web
import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.interceptors.behavior.WebBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.tokakao.KakaoInteractionInterceptor
import com.kaspersky.kaspresso.internal.extensions.espressoext.getMatcher
import com.kaspersky.kaspresso.proxy.AtomProxy

internal class WebKakaoInteractionInterceptor(
    configurator: Configurator
) : KakaoInteractionInterceptor<Web.WebInteraction<*>, Atom<*>, WebAssertion<*>>(configurator) {

    override fun interceptCheck(interaction: Web.WebInteraction<*>, assertion: WebAssertion<*>) {
        configurator.webBehaviorInterceptors.fold(
            initial = {
                interaction.check(
                    WebAssertionProxy(assertion, interaction.getMatcher(), configurator.webAssertionWatcherInterceptors)
                )
            },
            operation = { acc, webInteractor: WebBehaviorInterceptor ->
                { webInteractor.interact(interaction, acc) }
            }
        ).invoke()
    }

    override fun interceptPerform(interaction: Web.WebInteraction<*>, action: Atom<*>) {
        configurator.webBehaviorInterceptors.fold(
            initial = {
                interaction.perform(
                    AtomProxy(action, interaction.getMatcher(), configurator.atomWatcherInterceptors)
                )
            },
            operation = { acc, webInteractor: WebBehaviorInterceptor ->
                { webInteractor.interact(interaction, acc) }
            }
        ).invoke()
    }
}