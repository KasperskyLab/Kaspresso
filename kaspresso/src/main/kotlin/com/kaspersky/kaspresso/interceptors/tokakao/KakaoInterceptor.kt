package com.kaspersky.kaspresso.interceptors.tokakao

import com.kaspersky.kaspresso.configurator.Configurator

abstract class KakaoInterceptor<Interaction, Action, Assertion>(
    val configurator: Configurator
) {
    abstract fun interceptCheck(interaction: Interaction, assertion: Assertion)

    abstract fun interceptPerform(interaction: Interaction, action: Action)
}