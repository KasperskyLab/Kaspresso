package com.kaspersky.kaspresso.interceptors.to_kakao

import com.kaspersky.kaspresso.configurator.Configurator

abstract class KakaoInteractionInterceptor<Interaction, Action, Assertion>(
    val configurator: Configurator
) {
    abstract fun interceptCheck(interaction: Interaction, assertion: Assertion)

    abstract fun interceptPerform(interaction: Interaction, action: Action)
}