package com.kaspersky.kaspresso.interceptors.tolibrary.kautomator

import com.kaspersky.components.kautomator.intercept.interaction.UiObjectInteraction
import com.kaspersky.components.kautomator.intercept.operation.UiObjectAction
import com.kaspersky.components.kautomator.intercept.operation.UiObjectAssertion
import com.kaspersky.kaspresso.interceptors.behaviorkautomator.ObjectBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.tolibrary.LibraryInterceptor
import com.kaspersky.kaspresso.interceptors.watcher.kautomator.ObjectWatcherInterceptor

/**
 * Kaspresso's implementation of Kautomator's UiObjectInteraction interceptor.
 */
internal class KautomatorObjectInterceptor(
    private val objectBehaviorInterceptors: List<ObjectBehaviorInterceptor>,
    private val objectWatcherInterceptors: List<ObjectWatcherInterceptor>
) : LibraryInterceptor<UiObjectInteraction, UiObjectAssertion, UiObjectAction>() {

    /**
     * Folds all [ObjectBehaviorInterceptor]'s one into another in the order from the first to the last with the actual
     * [UiObjectInteraction.check] call as the initial, and invokes the resulting lambda.
     * Also, adds a call of all [ObjectWatcherInterceptor] in the initial lambda
     */
    override fun interceptCheck(interaction: UiObjectInteraction, assertion: UiObjectAssertion) {
        objectBehaviorInterceptors.fold(
            initial = {
                objectWatcherInterceptors.forEach {
                    it.interceptCheck(interaction, assertion)
                }
                interaction.check(assertion)
            },
            operation = {
                acc, objectBehaviorInterceptor ->
                { objectBehaviorInterceptor.interceptCheck(interaction, assertion, acc) }
            }
        ).invoke()
    }

    /**
     * Folds all [ObjectBehaviorInterceptor]'s one into another in the order from the first to the last with the actual
     * [UiObjectInteraction.perform] call as the initial, and invokes the resulting lambda.
     * Also, adds a call of [ObjectWatcherInterceptor] in the initial lambda
     */
    override fun interceptPerform(interaction: UiObjectInteraction, action: UiObjectAction) {
        objectBehaviorInterceptors.fold(
            initial = {
                objectWatcherInterceptors.forEach {
                    it.interceptPerform(interaction, action)
                }
                interaction.perform(action)
            },
            operation = {
                    acc, objectBehaviorInterceptor ->
                { objectBehaviorInterceptor.interceptPerform(interaction, action, acc) }
            }
        ).invoke()
    }
}