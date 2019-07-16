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
    configurator: Configurator
) : InteractionInterceptor<Web.WebInteraction<*>, Atom<*>, WebAssertion<*>>(configurator) {

    override fun interceptCheck(
        interaction: Web.WebInteraction<*>,
        assertion: WebAssertion<*>
    ) {
        interaction.check(
            WebAssertionProxy(
                assertion,
                interaction.getMatcher(),
                interaction,
                configurator.webAssertionInterceptors,
                configurator.webInteractors
            )
        )
    }

    override fun interceptPerform(
        interaction: Web.WebInteraction<*>,
        action: Atom<*>
    ) {
        interaction.perform(
            AtomProxy(
                action,
                interaction.getMatcher(),
                interaction,
                configurator.atomInterceptors,
                configurator.webInteractors
            )
        )
    }

    private fun Web.WebInteraction<*>.getMatcher(): Matcher<*> {
        return javaClass
            .getDeclaredField("viewMatcher")
            .apply { isAccessible = true }
            .get(this) as Matcher<*>
    }
}