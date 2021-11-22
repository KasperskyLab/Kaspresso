package com.kaspersky.components.composesupport.interceptors.watcher

import io.github.kakaocup.compose.intercept.interaction.ComposeInteraction

/**
 * The interface for all interceptors that are watching the default interaction in Kautomator.
 */
interface ComposeWatcherInterceptor<Interaction, Assertion, Action> {

    /**
     * Called to do some stuff before [ComposeInteraction.check] is actually called.
     *
     * @param interaction a Kakao-Compose ComposeInteraction on which [assertion] is performed
     * @param assertion responsible for performing an activity (assertion) on the given [interaction]
     */
    fun interceptCheck(interaction: Interaction, assertion: Assertion)

    /**
     * Called to do some stuff before [ComposeInteraction.perform] is actually called.
     *
     * @param interaction a Kakao-Compose ComposeInteraction on which [action] is performed
     * @param action responsible for performing an activity (action) on the given [interaction]
     */
    fun interceptPerform(interaction: Interaction, action: Action)
}
