package com.kaspersky.kaspresso.interceptors.watcher.uiautomator_dsl

interface UiAutomatorDslWatcherInterceptor<INTERACTION, ASSERTION, ACTION> {

    fun interceptCheck(interaction: INTERACTION, assertion: ASSERTION)

    fun interceptPerform(interaction: INTERACTION, action: ACTION)
}