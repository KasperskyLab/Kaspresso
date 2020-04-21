package com.kaspersky.kaspresso.interceptors.behaviorkautomator

import com.kaspersky.components.kautomator.intercept.interaction.UiObjectInteraction
import com.kaspersky.components.kautomator.intercept.operation.UiObjectAction
import com.kaspersky.components.kautomator.intercept.operation.UiObjectAssertion

/**
 * The derived from [KautomatorBehaviorInterceptor] interface for intercepting [UiObjectInteraction.perform] and
 * [UiObjectInteraction.check] behavior.
 */
interface ObjectBehaviorInterceptor :
    KautomatorBehaviorInterceptor<UiObjectInteraction, UiObjectAssertion, UiObjectAction>