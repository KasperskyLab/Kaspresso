package com.kaspersky.kaspresso.interceptors.tokakao.impl

import androidx.test.espresso.web.assertion.WebAssertion
import androidx.test.espresso.web.assertion.WebAssertionProxy
import androidx.test.espresso.web.model.Atom
import androidx.test.espresso.web.sugar.Web
import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.interceptors.behavior.WebBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.tokakao.KakaoInteractionInterceptor
import com.kaspersky.kaspresso.proxy.AtomProxy
import org.hamcrest.Matcher

internal class WebKakaoInteractionInterceptor(
    configurator: Configurator
) : KakaoInteractionInterceptor<Web.WebInteraction<*>, Atom<*>, WebAssertion<*>>(configurator) {

    override fun interceptCheck(interaction: Web.WebInteraction<*>, assertion: WebAssertion<*>) {
        configurator.webBehaviorInterceptors.fold(
            {
                interaction.check(
                    WebAssertionProxy(assertion, interaction.getMatcher(), configurator.webAssertionWatcherInterceptors)
                )
            },
            { acc, webInteractor: WebBehaviorInterceptor -> { webInteractor.interact(interaction, acc) } }
        ).invoke()
    }

    override fun interceptPerform(interaction: Web.WebInteraction<*>, action: Atom<*>) {
        configurator.webBehaviorInterceptors.fold(
            { interaction.perform(AtomProxy(action, interaction.getMatcher(), configurator.atomWatcherInterceptors)) },
            { acc, webInteractor: WebBehaviorInterceptor -> { webInteractor.interact(interaction, acc) } }
        ).invoke()
    }

    private fun Web.WebInteraction<*>.getMatcher(): Matcher<*> {
        return javaClass
            .getDeclaredField("viewMatcher")
            .apply { isAccessible = true }
            .get(this) as Matcher<*>
    }
}