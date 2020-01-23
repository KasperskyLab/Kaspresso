package com.kaspersky.kaspresso.interceptors.behaviorkautomator

import com.kaspersky.components.kautomator.intercepting.interaction.UiObjectInteraction
import com.kaspersky.components.kautomator.intercepting.operation.UiObjectAction
import com.kaspersky.components.kautomator.intercepting.operation.UiObjectAssertion

/**
 * The derived from [KautomatorBehaviorInterceptor] interface for intercepting [UiObjectInteraction.perform] and
 * [UiObjectInteraction.check] behavior.
 */
interface ObjectBehaviorInterceptor :
    KautomatorBehaviorInterceptor<UiObjectInteraction, UiObjectAssertion, UiObjectAction>