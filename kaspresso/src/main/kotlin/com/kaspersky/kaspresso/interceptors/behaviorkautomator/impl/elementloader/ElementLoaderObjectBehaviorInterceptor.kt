package com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.elementloader

import com.kaspersky.components.kautomator.intercept.interaction.UiObjectInteraction
import com.kaspersky.components.kautomator.intercept.operation.UiObjectAction
import com.kaspersky.components.kautomator.intercept.operation.UiObjectAssertion
import com.kaspersky.kaspresso.elementloader.ElementLoaderProvider
import com.kaspersky.kaspresso.elementloader.ElementLoaderProviderImpl
import com.kaspersky.kaspresso.interceptors.behaviorkautomator.ObjectBehaviorInterceptor
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso.params.ElementLoaderParams

/**
 * The implementation of [ObjectBehaviorInterceptor] and [ElementLoaderProvider] interfaces.
 * Provides element reloading on failure functionality for [UiObjectInteraction.perform] and [UiObjectInteraction.check] calls.
 */
class ElementLoaderObjectBehaviorInterceptor(
    logger: UiTestLogger,
    params: ElementLoaderParams
) : ObjectBehaviorInterceptor,
    ElementLoaderProvider by ElementLoaderProviderImpl(logger, params) {

    /**
     * Wraps the given [assertion] invocation with the element reloading on failure.
     *
     * @param interaction the intercepted [UiObjectInteraction].
     * @param assertion the assertion to invoke.
     */
    override fun <T> interceptCheck(
        interaction: UiObjectInteraction,
        assertion: UiObjectAssertion,
        activity: () -> T
    ): T = passAction(
        elementLoader = { interaction.reFindUiObject() },
        action = activity
    )

    /**
     * Wraps the given [action] invocation with the element reloading on failure.
     *
     * @param interaction the intercepted [UiObjectInteraction].
     * @param action the assertion to invoke.
     */
    override fun <T> interceptPerform(
        interaction: UiObjectInteraction,
        action: UiObjectAction,
        activity: () -> T
    ): T = passAction(
        elementLoader = { interaction.reFindUiObject() },
        action = activity
    )
}
