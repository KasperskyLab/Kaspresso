package com.kaspersky.kaspresso.interceptors.watcher.uiautomator_dsl

import com.kaspersky.components.uiautomatordsl.intercepting.interaction.UiObjectInteraction
import com.kaspersky.components.uiautomatordsl.intercepting.operation.UiObjectAction
import com.kaspersky.components.uiautomatordsl.intercepting.operation.UiObjectAssertion

interface ObjectWatcherInterceptor :
    UiAutomatorDslWatcherInterceptor<UiObjectInteraction, UiObjectAssertion, UiObjectAction>