package com.kaspersky.kaspresso.interceptors.tolibrary.uiautomator_dsl

import com.kaspersky.components.uiautomatordsl.intercepting.interaction.UiObjectInteraction
import com.kaspersky.components.uiautomatordsl.intercepting.operation.UiObjectAction
import com.kaspersky.components.uiautomatordsl.intercepting.operation.UiObjectAssertion
import com.kaspersky.kaspresso.interceptors.tolibrary.LibraryInterceptor
import com.kaspersky.kaspresso.kaspresso.Kaspresso

internal class UiAutomatorDslObjectInterceptor(
    kaspresso: Kaspresso
) : LibraryInterceptor<UiObjectInteraction, UiObjectAssertion, UiObjectAction>(kaspresso) {

    override fun interceptCheck(interaction: UiObjectInteraction, assertion: UiObjectAssertion) {
        kaspresso.objectBehaviorInterceptors.fold(
            initial = {
                kaspresso.objectWatcherInterceptors.forEach {
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

    override fun interceptPerform(interaction: UiObjectInteraction, action: UiObjectAction) {
        kaspresso.objectBehaviorInterceptors.fold(
            initial = {
                kaspresso.objectWatcherInterceptors.forEach {
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