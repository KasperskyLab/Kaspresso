package com.kaspersky.kaspresso.interceptors.watcher.uiautomator_dsl

import com.kaspersky.components.uiautomatordsl.intercepting.operation.UiDeviceAction
import com.kaspersky.components.uiautomatordsl.intercepting.operation.UiDeviceAssertion
import com.kaspersky.components.uiautomatordsl.intercepting.interaction.UiDeviceInteraction

interface DeviceWatcherInterceptor :
    UiAutomatorDslWatcherInterceptor<UiDeviceInteraction, UiDeviceAssertion, UiDeviceAction>