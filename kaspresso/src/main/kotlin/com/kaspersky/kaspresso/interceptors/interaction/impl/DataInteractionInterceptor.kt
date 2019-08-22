package com.kaspersky.kaspresso.interceptors.interaction.impl

import androidx.test.espresso.DataInteraction
import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewAssertion
import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.interceptors.interaction.InteractionInterceptor
import com.kaspersky.kaspresso.interceptors.interactors.DataInteractor
import com.kaspersky.kaspresso.proxy.DataAssertionProxy

internal class DataInteractionInterceptor(
    configurator: Configurator
) : InteractionInterceptor<DataInteraction, ViewAction, ViewAssertion>(configurator) {

    override fun interceptCheck(interaction: DataInteraction, assertion: ViewAssertion) {
        configurator.dataInteractors.fold(
            { interaction.check(DataAssertionProxy(assertion, configurator.viewAssertionInterceptors)) },
            { acc, dataInteractor: DataInteractor -> { dataInteractor.interact(interaction, acc) } }
        ).invoke()
    }

    override fun interceptPerform(interaction: DataInteraction, action: ViewAction) = Unit
}