package com.kaspersky.kaspresso.interceptors.tokakao

import com.kaspersky.kaspresso.kaspresso.Kaspresso

/**
 * The base class for Kaspresso's implementations of Kakao's interceptors.
 */
abstract class KakaoInterceptor<Interaction, Action, Assertion>(
    protected val kaspresso: Kaspresso
) {
    abstract fun interceptCheck(interaction: Interaction, assertion: Assertion)

    abstract fun interceptPerform(interaction: Interaction, action: Action)
}