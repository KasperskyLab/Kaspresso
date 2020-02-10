package com.kaspersky.kaspresso.interceptors.watcher.kautomator

import com.kaspersky.components.kautomator.intercept.interaction.UiDeviceInteraction
import com.kaspersky.components.kautomator.intercept.operation.UiDeviceAction
import com.kaspersky.components.kautomator.intercept.operation.UiDeviceAssertion

/**
 * The derived from [KautomatorWatcherInterceptor] interface for intercepting (only watching) [UiDeviceInteraction.perform] and
 * [UiDeviceInteraction.check] behavior.
 */
interface DeviceWatcherInterceptor :
    KautomatorWatcherInterceptor<UiDeviceInteraction, UiDeviceAssertion, UiDeviceAction>