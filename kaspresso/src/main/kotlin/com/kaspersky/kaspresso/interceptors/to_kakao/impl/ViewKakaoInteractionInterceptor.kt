package com.kaspersky.kaspresso.interceptors.to_kakao.impl

import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.ViewInteraction
import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.interceptors.to_kakao.KakaoInteractionInterceptor
import com.kaspersky.kaspresso.interceptors.behavior.ViewBehaviorInteractor
import com.kaspersky.kaspresso.proxy.ViewActionProxy
import com.kaspersky.kaspresso.proxy.ViewAssertionProxy

internal class ViewKakaoInteractionInterceptor(
    configurator: Configurator
) : KakaoInteractionInterceptor<ViewInteraction, ViewAction, ViewAssertion>(configurator) {

    override fun interceptCheck(interaction: ViewInteraction, assertion: ViewAssertion) {
        configurator.viewInteractors.fold(
            { interaction.check(ViewAssertionProxy(assertion, configurator.viewAssertionWatcherInterceptors)) },
            { acc, viewInteractor: ViewBehaviorInteractor -> { viewInteractor.interact(interaction, acc) } }
        ).invoke()
    }

    override fun interceptPerform(interaction: ViewInteraction, action: ViewAction) {
        configurator.viewInteractors.fold(
            { interaction.perform(ViewActionProxy(action, configurator.viewActionWatcherInterceptors)) },
            { acc, viewInteractor: ViewBehaviorInteractor -> { viewInteractor.interact(interaction, acc) } }
        ).invoke()
    }
}