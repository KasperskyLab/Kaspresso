package com.kaspersky.kaspresso.interceptors.watcher.kautomator

import com.kaspersky.components.kautomator.intercept.interaction.UiInteraction

/**
 * The interface for all interceptors that are watching the default interaction in Kautomator.
 */
interface KautomatorWatcherInterceptor<Interaction, Assertion, Action> {

    /**
     * Called to do some stuff before [UiInteraction.check] is actually called.
     *
     * @param interaction a Kautomator UiInteraction on which [assertion] is performed
     * @param assertion responsible for performing an activity (assertion) on the given [interaction]
     */
    fun interceptCheck(interaction: Interaction, assertion: Assertion)

    /**
     * Called to do some stuff before [UiInteraction.perform] is actually called.
     *
     * @param interaction a Kautomator UiInteraction on which [action] is performed
     * @param action responsible for performing an activity (action) on the given [interaction]
     */
    fun interceptPerform(interaction: Interaction, action: Action)
}