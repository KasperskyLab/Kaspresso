package com.kaspersky.kaspresso.interceptors.tokakao.impl

import android.support.test.espresso.DataInteraction
import android.support.test.espresso.ViewAction
import android.support.test.espresso.ViewAssertion
import com.kaspersky.kaspresso.interceptors.behavior.DataBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.tokakao.KakaoInterceptor
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.proxy.DataAssertionProxy

/**
 * Kaspresso's implementation of Kakao's data interaction interceptor.
 */
internal class KakaoDataInterceptor(
    kaspresso: Kaspresso
) : KakaoInterceptor<DataInteraction, ViewAction, ViewAssertion>(kaspresso) {

    /**
     * Folds all [DataBehaviorInterceptor]'s one into another in the order from the first to the last with the actual
     * [androidx.test.espresso.DataInteraction.check] call as the initial, and invokes the resulting lambda.
     */
    override fun interceptCheck(interaction: DataInteraction, assertion: ViewAssertion) {
        kaspresso.dataBehaviorInterceptors.fold(
            initial = {
                interaction.check(
                    DataAssertionProxy(assertion, kaspresso.viewAssertionWatcherInterceptors)
                )
            },
            operation = { acc, dataBehaviorInterceptor: DataBehaviorInterceptor ->
                { dataBehaviorInterceptor.intercept(interaction, acc) }
            }
        ).invoke()
    }

    /**
     * Empty.
     */
    override fun interceptPerform(interaction: DataInteraction, action: ViewAction) = Unit
}