package com.kaspersky.kaspresso.interceptors.interaction.impl

import androidx.test.espresso.web.assertion.WebAssertion
import androidx.test.espresso.web.assertion.WebAssertionProxy
import androidx.test.espresso.web.model.Atom
import androidx.test.espresso.web.sugar.Web
import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.interceptors.interaction.InteractionInterceptor
import com.kaspersky.kaspresso.interceptors.interactors.WebInteractor
import com.kaspersky.kaspresso.internal.extensions.espressoext.getMatcher
import com.kaspersky.kaspresso.proxy.AtomProxy

internal class WebInteractionInterceptor(
    configurator: Configurator
) : InteractionInterceptor<Web.WebInteraction<*>, Atom<*>, WebAssertion<*>>(configurator) {

    override fun interceptCheck(interaction: Web.WebInteraction<*>, assertion: WebAssertion<*>) {
        configurator.webInteractors.fold(
            initial = {
                interaction.check(
                    WebAssertionProxy(assertion, interaction.getMatcher(), configurator.webAssertionInterceptors)
                )
            },
            operation = { acc, webInteractor: WebInteractor -> { webInteractor.interact(interaction, acc) } }
        ).invoke()
    }

    override fun interceptPerform(interaction: Web.WebInteraction<*>, action: Atom<*>) {
        configurator.webInteractors.fold(
            initial = {
                interaction.perform(
                    AtomProxy(action, interaction.getMatcher(), configurator.atomInterceptors)
                )
            },
            operation = { acc, webInteractor: WebInteractor -> { webInteractor.interact(interaction, acc) } }
        ).invoke()
    }
}