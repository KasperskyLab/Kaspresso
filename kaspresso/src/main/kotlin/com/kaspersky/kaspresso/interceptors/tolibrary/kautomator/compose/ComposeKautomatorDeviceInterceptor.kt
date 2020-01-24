package com.kaspersky.kaspresso.interceptors.tolibrary.kautomator.compose

import com.kaspersky.components.kautomator.intercepting.interaction.UiDeviceInteraction
import com.kaspersky.components.kautomator.intercepting.operation.UiDeviceAction
import com.kaspersky.components.kautomator.intercepting.operation.UiDeviceAssertion
import com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.flakysafety.FlakySafeDeviceBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.tolibrary.LibraryInterceptor
import com.kaspersky.kaspresso.kaspresso.Kaspresso

internal class ComposeKautomatorDeviceInterceptor(
    kaspresso: Kaspresso
) : LibraryInterceptor<UiDeviceInteraction, UiDeviceAssertion, UiDeviceAction>(kaspresso) {

    override fun interceptCheck(interaction: UiDeviceInteraction, assertion: UiDeviceAssertion) {
        kaspresso.deviceBehaviorInterceptors
            .filter { it !is FlakySafeDeviceBehaviorInterceptor }
            .fold(
                initial = {
                    kaspresso.deviceWatcherInterceptors.forEach {
                        it.interceptCheck(interaction, assertion)
                    }
                    interaction.check(assertion)
                },
                operation = {
                        acc, deviceBehaviorInterceptor ->
                    { deviceBehaviorInterceptor.interceptCheck(interaction, assertion, acc) }
                }
            ).invoke()
    }

    override fun interceptPerform(interaction: UiDeviceInteraction, action: UiDeviceAction) {
        kaspresso.deviceBehaviorInterceptors
            .filter { it !is FlakySafeDeviceBehaviorInterceptor }
            .fold(
                initial = {
                    kaspresso.deviceWatcherInterceptors.forEach {
                        it.interceptPerform(interaction, action)
                    }
                    interaction.perform(action)
                },
                operation = {
                        acc, deviceBehaviorInterceptor ->
                    { deviceBehaviorInterceptor.interceptPerform(interaction, action, acc) }
                }
            ).invoke()
    }
}