package com.kaspersky.kaspresso.interceptors.watcher.uiautomator_dsl

import com.kaspersky.components.uiautomatordsl.intercepting.interaction.UiDeviceInteraction
import com.kaspersky.components.uiautomatordsl.intercepting.operation.UiDeviceAction
import com.kaspersky.components.uiautomatordsl.intercepting.operation.UiDeviceAssertion

interface DeviceWatcherInterceptor :
    UiAutomatorDslWatcherInterceptor<UiDeviceInteraction, UiDeviceAssertion, UiDeviceAction>