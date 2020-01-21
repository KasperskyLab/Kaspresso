package com.kaspersky.kaspresso.interceptors.watcher.kautomator

import com.kaspersky.components.kautomator.intercepting.interaction.UiDeviceInteraction
import com.kaspersky.components.kautomator.intercepting.operation.UiDeviceAction
import com.kaspersky.components.kautomator.intercepting.operation.UiDeviceAssertion

interface DeviceWatcherInterceptor :
    KautomatorWatcherInterceptor<UiDeviceInteraction, UiDeviceAssertion, UiDeviceAction>