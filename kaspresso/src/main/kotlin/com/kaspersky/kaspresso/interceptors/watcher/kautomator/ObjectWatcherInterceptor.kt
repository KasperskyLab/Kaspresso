package com.kaspersky.kaspresso.interceptors.watcher.kautomator

import com.kaspersky.components.kautomator.intercepting.interaction.UiObjectInteraction
import com.kaspersky.components.kautomator.intercepting.operation.UiObjectAction
import com.kaspersky.components.kautomator.intercepting.operation.UiObjectAssertion

interface ObjectWatcherInterceptor :
    KautomatorWatcherInterceptor<UiObjectInteraction, UiObjectAssertion, UiObjectAction>