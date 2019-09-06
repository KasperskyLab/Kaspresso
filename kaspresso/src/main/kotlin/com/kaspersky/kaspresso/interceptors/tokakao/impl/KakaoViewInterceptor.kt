package com.kaspersky.kaspresso.interceptors.tokakao.impl

import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.ViewInteraction
import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.interceptors.behavior.ViewBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.tokakao.KakaoInterceptor
import com.kaspersky.kaspresso.proxy.ViewActionProxy
import com.kaspersky.kaspresso.proxy.ViewAssertionProxy

internal class KakaoViewInterceptor(
    configurator: Configurator
) : KakaoInterceptor<ViewInteraction, ViewAction, ViewAssertion>(configurator) {

    override fun interceptCheck(interaction: ViewInteraction, assertion: ViewAssertion) {
        configurator.viewBehaviorInterceptors.fold(
            initial = {
                interaction.check(
                    ViewAssertionProxy(assertion, configurator.viewAssertionWatcherInterceptors)
                )
            },
            operation = { acc, viewBehaviorInterceptor: ViewBehaviorInterceptor ->
                { viewBehaviorInterceptor.intercept(interaction, acc) }
            }
        ).invoke()
    }

    override fun interceptPerform(interaction: ViewInteraction, action: ViewAction) {
        configurator.viewBehaviorInterceptors.fold(
            initial = {
                interaction.perform(
                    ViewActionProxy(action, configurator.viewActionWatcherInterceptors)
                )
            },
            operation = { acc, viewBehaviorInterceptor: ViewBehaviorInterceptor ->
                { viewBehaviorInterceptor.intercept(interaction, acc) }
            }
        ).invoke()
    }
}