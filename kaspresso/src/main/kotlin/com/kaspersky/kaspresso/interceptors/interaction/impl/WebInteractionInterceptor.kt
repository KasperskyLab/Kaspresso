package com.kaspersky.kaspresso.interceptors.interaction.impl

import android.support.test.espresso.web.assertion.WebAssertion
import android.support.test.espresso.web.assertion.WebAssertionProxy
import android.support.test.espresso.web.model.Atom
import android.support.test.espresso.web.sugar.Web
import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.interceptors.interaction.InteractionInterceptor
import com.kaspersky.kaspresso.proxy.AtomProxy
import org.hamcrest.Matcher

internal class WebInteractionInterceptor(
    override val configurator: Configurator
) : InteractionInterceptor<Web.WebInteraction<*>, Atom<*>, WebAssertion<*>> {

    override fun interceptCheck(
        interaction: Web.WebInteraction<*>,
        assertion: WebAssertion<*>
    ) {
        val matcher: Matcher<*> =
            interaction.javaClass
                .getDeclaredField("viewMatcher")
                .apply { isAccessible = true }
                .get(interaction) as Matcher<*>

        execute {
            interaction.check(
                WebAssertionProxy(
                    assertion,
                    matcher,
                    configurator.webAssertionInterceptors
                )
            )
        }
    }

    override fun interceptPerform(
        interaction: Web.WebInteraction<*>,
        action: Atom<*>
    ) {
        execute {
            interaction.perform(
                AtomProxy(action, configurator.atomInterceptors)
            )
        }
    }
}