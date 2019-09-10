package com.kaspersky.kaspresso.interceptors.tokakao

import com.kaspersky.kaspresso.kaspresso.Kaspresso

abstract class KakaoInterceptor<Interaction, Action, Assertion>(
    protected val kaspresso: Kaspresso
) {
    abstract fun interceptCheck(interaction: Interaction, assertion: Assertion)

    abstract fun interceptPerform(interaction: Interaction, action: Action)
}