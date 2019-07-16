package com.kaspersky.kaspresso.interceptors.interaction.impl

import android.support.test.espresso.DataInteraction
import android.support.test.espresso.ViewAction
import android.support.test.espresso.ViewAssertion
import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.interceptors.interaction.InteractionInterceptor
import com.kaspersky.kaspresso.proxy.DataAssertionProxy

internal class DataInteractionInterceptor(
    configurator: Configurator
) : InteractionInterceptor<DataInteraction, ViewAction, ViewAssertion>(configurator) {

    override fun interceptCheck(
        interaction: DataInteraction,
        assertion: ViewAssertion
    ) {
        interaction.check(
            DataAssertionProxy(assertion, interaction, configurator.viewAssertionInterceptors, configurator.dataInteractors)
        )
    }

    override fun interceptPerform(
        interaction: DataInteraction,
        action: ViewAction
    ) = Unit
}