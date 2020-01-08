package com.kaspersky.kaspresso.interceptors.watcher.uiautomator_dsl

interface UiAutomatorDslWatcherInterceptor<Interaction, Assertion, Action> {

    fun interceptCheck(interaction: Interaction, assertion: Assertion)

    fun interceptPerform(interaction: Interaction, action: Action)
}