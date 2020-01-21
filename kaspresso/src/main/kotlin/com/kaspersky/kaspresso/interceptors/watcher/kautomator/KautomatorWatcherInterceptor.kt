package com.kaspersky.kaspresso.interceptors.watcher.kautomator

interface KautomatorWatcherInterceptor<Interaction, Assertion, Action> {

    fun interceptCheck(interaction: Interaction, assertion: Assertion)

    fun interceptPerform(interaction: Interaction, action: Action)
}