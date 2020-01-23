package com.kaspersky.kaspresso.interceptors.behaviorkautomator

import com.kaspersky.components.kautomator.intercepting.interaction.UiDeviceInteraction
import com.kaspersky.components.kautomator.intercepting.operation.UiDeviceAction
import com.kaspersky.components.kautomator.intercepting.operation.UiDeviceAssertion

/**
 * The derived from [KautomatorBehaviorInterceptor] interface for intercepting [UiDeviceInteraction.perform] and
 * [UiDeviceInteraction.check] behavior.
 */
interface DeviceBehaviorInterceptor :
    KautomatorBehaviorInterceptor<UiDeviceInteraction, UiDeviceAssertion, UiDeviceAction>