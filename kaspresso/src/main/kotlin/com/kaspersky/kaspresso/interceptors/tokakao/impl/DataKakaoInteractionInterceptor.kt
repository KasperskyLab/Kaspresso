package com.kaspersky.kaspresso.interceptors.tokakao.impl

import androidx.test.espresso.DataInteraction
import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewAssertion
import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.interceptors.behavior.DataBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.tokakao.KakaoInteractionInterceptor
import com.kaspersky.kaspresso.proxy.DataAssertionProxy

internal class DataKakaoInteractionInterceptor(
    configurator: Configurator
) : KakaoInteractionInterceptor<DataInteraction, ViewAction, ViewAssertion>(configurator) {

    override fun interceptCheck(interaction: DataInteraction, assertion: ViewAssertion) {
        configurator.dataBehaviorInterceptors.fold(
            initial = {
                interaction.check(
                    DataAssertionProxy(assertion, configurator.viewAssertionWatcherInterceptors)
                )
            },
            operation = { acc, dataInteractor: DataBehaviorInterceptor ->
                { dataInteractor.interact(interaction, acc) }
            }
        ).invoke()
    }

    override fun interceptPerform(interaction: DataInteraction, action: ViewAction) = Unit
}