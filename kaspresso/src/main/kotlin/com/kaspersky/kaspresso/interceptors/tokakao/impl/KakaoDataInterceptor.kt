package com.kaspersky.kaspresso.interceptors.tokakao.impl

import androidx.test.espresso.DataInteraction
import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewAssertion
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.interceptors.behavior.DataBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.tokakao.KakaoInterceptor
import com.kaspersky.kaspresso.proxy.DataAssertionProxy

internal class KakaoDataInterceptor(
    kaspresso: Kaspresso
) : KakaoInterceptor<DataInteraction, ViewAction, ViewAssertion>(kaspresso) {

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

    override fun interceptPerform(interaction: DataInteraction, action: ViewAction) = Unit
}