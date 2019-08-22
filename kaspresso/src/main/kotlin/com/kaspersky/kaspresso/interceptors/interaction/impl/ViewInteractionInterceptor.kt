package com.kaspersky.kaspresso.interceptors.interaction.impl

import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.ViewInteraction
import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.interceptors.interaction.InteractionInterceptor
import com.kaspersky.kaspresso.interceptors.interactors.ViewInteractor
import com.kaspersky.kaspresso.proxy.ViewActionProxy
import com.kaspersky.kaspresso.proxy.ViewAssertionProxy

internal class ViewInteractionInterceptor(
    configurator: Configurator
) : InteractionInterceptor<ViewInteraction, ViewAction, ViewAssertion>(configurator) {

    override fun interceptCheck(interaction: ViewInteraction, assertion: ViewAssertion) {
        configurator.viewInteractors.fold(
            { interaction.check(ViewAssertionProxy(assertion, configurator.viewAssertionInterceptors)) },
            { acc, viewInteractor: ViewInteractor -> { viewInteractor.interact(interaction, acc) } }
        ).invoke()
    }

    override fun interceptPerform(interaction: ViewInteraction, action: ViewAction) {
        configurator.viewInteractors.fold(
            { interaction.perform(ViewActionProxy(action, configurator.viewActionInterceptors)) },
            { acc, viewInteractor: ViewInteractor -> { viewInteractor.interact(interaction, acc) } }
        ).invoke()
    }
}