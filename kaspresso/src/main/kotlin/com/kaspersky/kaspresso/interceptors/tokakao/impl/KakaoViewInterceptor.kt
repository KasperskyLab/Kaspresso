package com.kaspersky.kaspresso.interceptors.tokakao.impl

import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.ViewInteraction
import com.kaspersky.kaspresso.interceptors.behavior.ViewBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.tokakao.KakaoInterceptor
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.proxy.ViewActionProxy
import com.kaspersky.kaspresso.proxy.ViewAssertionProxy

/**
 * Kaspresso's implementation of Kakao's view interaction interceptor.
 */
internal class KakaoViewInterceptor(
    kaspresso: Kaspresso
) : KakaoInterceptor<ViewInteraction, ViewAction, ViewAssertion>(kaspresso) {

    /**
     * Folds all [ViewBehaviorInterceptor]'s one into another with the actual [interaction] "check" call as the initial,
     * and invokes the resulting lambda.
     */
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

    /**
     * Folds all [ViewBehaviorInterceptor]'s one into another with the actual [interaction] "perform" call as the initial,
     * and invokes the resulting lambda.
     */
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