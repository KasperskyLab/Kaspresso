package com.kaspersky.kaspresso.interceptors.interaction.impl

import android.support.test.espresso.ViewAction
import android.support.test.espresso.ViewAssertion
import android.support.test.espresso.ViewInteraction
import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.interceptors.interaction.InteractionInterceptor
import com.kaspersky.kaspresso.proxy.ViewActionProxy
import com.kaspersky.kaspresso.proxy.ViewAssertionProxy

internal class ViewInteractionInterceptor(
    configurator: Configurator
) : InteractionInterceptor<ViewInteraction, ViewAction, ViewAssertion>(configurator) {

    override fun interceptCheck(
        interaction: ViewInteraction,
        assertion: ViewAssertion
    ) {
        execute {
            interaction.check(
                ViewAssertionProxy(assertion, configurator.viewAssertionInterceptors)
            )
        }
    }

    override fun interceptPerform(
        interaction: ViewInteraction,
        action: ViewAction
    ) {
        execute {
            interaction.perform(
                ViewActionProxy(action, configurator.viewActionInterceptors)
            )
        }
    }
}