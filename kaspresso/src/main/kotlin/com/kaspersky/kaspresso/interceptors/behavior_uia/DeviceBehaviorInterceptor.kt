package com.kaspersky.kaspresso.interceptors.behavior_uia

import com.kaspersky.components.uiautomatordsl.intercepting.interaction.UiDeviceInteraction
import com.kaspersky.components.uiautomatordsl.intercepting.operation.UiDeviceAction
import com.kaspersky.components.uiautomatordsl.intercepting.operation.UiDeviceAssertion

interface DeviceBehaviorInterceptor :
    UiAutomatorDslBehaviorInterceptor<UiDeviceInteraction, UiDeviceAssertion, UiDeviceAction>