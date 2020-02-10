package com.kaspersky.kaspresso.interceptors.watcher.kautomator

import com.kaspersky.components.kautomator.intercept.interaction.UiObjectInteraction
import com.kaspersky.components.kautomator.intercept.operation.UiObjectAction
import com.kaspersky.components.kautomator.intercept.operation.UiObjectAssertion

/**
 * The derived from [KautomatorWatcherInterceptor] interface for intercepting (only watching) [UiObjectInteraction.perform] and
 * [UiObjectInteraction.check] behavior.
 */
interface ObjectWatcherInterceptor :
    KautomatorWatcherInterceptor<UiObjectInteraction, UiObjectAssertion, UiObjectAction>