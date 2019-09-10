package com.kaspersky.kaspresso.interceptors.tokakao.impl

import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.ViewInteraction
import com.kaspersky.kaspresso.interceptors.behavior.ViewBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.tokakao.KakaoInterceptor
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.proxy.ViewActionProxy
import com.kaspersky.kaspresso.proxy.ViewAssertionProxy

internal class KakaoViewInterceptor(
    kaspresso: Kaspresso
) : KakaoInterceptor<ViewInteraction, ViewAction, ViewAssertion>(kaspresso) {

    override fun interceptCheck(interaction: ViewInteraction, assertion: ViewAssertion) {
        kaspresso.viewBehaviorInterceptors.fold(
            initial = {
                interaction.check(
                    ViewAssertionProxy(assertion, kaspresso.viewAssertionWatcherInterceptors)
                )
            },
            operation = { acc, viewBehaviorInterceptor: ViewBehaviorInterceptor ->
                { viewBehaviorInterceptor.intercept(interaction, acc) }
            }
        ).invoke()
    }

    override fun interceptPerform(interaction: ViewInteraction, action: ViewAction) {
        kaspresso.viewBehaviorInterceptors.fold(
            initial = {
                interaction.perform(
                    ViewActionProxy(action, kaspresso.viewActionWatcherInterceptors)
                )
            },
            operation = { acc, viewBehaviorInterceptor: ViewBehaviorInterceptor ->
                { viewBehaviorInterceptor.intercept(interaction, acc) }
            }
        ).invoke()
    }
}