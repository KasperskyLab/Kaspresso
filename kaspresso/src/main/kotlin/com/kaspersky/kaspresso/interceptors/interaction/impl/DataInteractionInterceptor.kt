package com.kaspersky.kaspresso.interceptors.interaction.impl

import android.support.test.espresso.DataInteraction
import android.support.test.espresso.ViewAction
import android.support.test.espresso.ViewAssertion
import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.interceptors.interaction.InteractionInterceptor
import com.kaspersky.kaspresso.proxy.ViewAssertionProxy

internal class DataInteractionInterceptor(
    override val configurator: Configurator
) : InteractionInterceptor<DataInteraction, ViewAction, ViewAssertion> {

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