package com.kaspersky.kaspresso.interceptors.interaction.impl.compose

import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.ViewInteraction
import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.interceptors.interaction.InteractionInterceptor
import com.kaspersky.kaspresso.interceptors.interactors.ViewInteractor
import com.kaspersky.kaspresso.interceptors.interactors.impl.failure.FailureLoggingViewInteractor
import com.kaspersky.kaspresso.interceptors.interactors.impl.flakysafety.FlakySafeViewInteractor
import com.kaspersky.kaspresso.proxy.ViewActionProxy
import com.kaspersky.kaspresso.proxy.ViewAssertionProxy

internal class ComposeViewInteractionInterceptor(
    configurator: Configurator
) : InteractionInterceptor<ViewInteraction, ViewAction, ViewAssertion>(configurator) {

    override fun interceptCheck(interaction: ViewInteraction, assertion: ViewAssertion) {
        configurator.viewInteractors
            .filter { it !is FlakySafeViewInteractor && it !is FailureLoggingViewInteractor }
            .fold(
                initial = {
                    interaction.check(ViewAssertionProxy(assertion, configurator.viewAssertionInterceptors))
                },
                operation = { acc, viewInteractor: ViewInteractor ->
                    { viewInteractor.interact(interaction, acc) }
                }
            ).invoke()
    }

    override fun interceptPerform(interaction: ViewInteraction, action: ViewAction) {
        configurator.viewInteractors.filter { it !is FlakySafeViewInteractor }.fold(
            initial = {
                interaction.perform(ViewActionProxy(action, configurator.viewActionInterceptors))
            },
            operation = { acc, viewInteractor: ViewInteractor ->
                { viewInteractor.interact(interaction, acc) }
            }
        ).invoke()
    }
}