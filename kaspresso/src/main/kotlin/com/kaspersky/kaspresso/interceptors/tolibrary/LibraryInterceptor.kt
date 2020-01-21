package com.kaspersky.kaspresso.interceptors.tolibrary

import com.kaspersky.kaspresso.kaspresso.Kaspresso

/**
 * The base class of libraries interceptors (Kakao/UiAutomatorDsl) for Kaspresso's implementations
 */
internal abstract class LibraryInterceptor<Interaction, Assertion, Action>(
    protected val kaspresso: Kaspresso
) {
    abstract fun interceptCheck(interaction: Interaction, assertion: Assertion)

    abstract fun interceptPerform(interaction: Interaction, action: Action)
}