package com.kaspersky.kaspresso.interceptors.tolibrary

/**
 * The base class of libraries interceptors (Kakao/UiAutomatorDsl) for Kaspresso's implementations
 */
internal interface LibraryInterceptor<Interaction, Assertion, Action> {

    fun interceptCheck(interaction: Interaction, assertion: Assertion)

    fun interceptPerform(interaction: Interaction, action: Action)
}
