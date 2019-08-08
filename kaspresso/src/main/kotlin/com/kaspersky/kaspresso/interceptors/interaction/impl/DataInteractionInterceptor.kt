package com.kaspersky.kaspresso.interceptors.interaction.impl

import androidx.test.espresso.DataInteraction
import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewAssertion
import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.interceptors.interaction.InteractionInterceptor
import com.kaspersky.kaspresso.proxy.ViewAssertionProxy

internal class DataInteractionInterceptor(
    configurator: Configurator
) : InteractionInterceptor<DataInteraction, ViewAction, ViewAssertion>(configurator) {

    override fun interceptCheck(
        interaction: DataInteraction,
        assertion: ViewAssertion
    ) {
        execute {
            interaction.check(
                ViewAssertionProxy(assertion, configurator.viewAssertionInterceptors)
            )
        }
    }

    override fun interceptPerform(
        interaction: DataInteraction,
        action: ViewAction
    ) = Unit
}