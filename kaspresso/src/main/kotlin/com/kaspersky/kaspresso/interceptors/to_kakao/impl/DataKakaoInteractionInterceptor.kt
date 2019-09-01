package com.kaspersky.kaspresso.interceptors.to_kakao.impl

import androidx.test.espresso.DataInteraction
import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewAssertion
import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.interceptors.to_kakao.KakaoInteractionInterceptor
import com.kaspersky.kaspresso.interceptors.behavior.DataBehaviorInteractor
import com.kaspersky.kaspresso.proxy.DataAssertionProxy

internal class DataKakaoInteractionInterceptor(
    configurator: Configurator
) : KakaoInteractionInterceptor<DataInteraction, ViewAction, ViewAssertion>(configurator) {

    override fun interceptCheck(interaction: DataInteraction, assertion: ViewAssertion) {
        configurator.dataInteractors.fold(
            { interaction.check(DataAssertionProxy(assertion, configurator.viewAssertionWatcherInterceptors)) },
            { acc, dataInteractor: DataBehaviorInteractor -> { dataInteractor.interact(interaction, acc) } }
        ).invoke()
    }

    override fun interceptPerform(interaction: DataInteraction, action: ViewAction) = Unit
}