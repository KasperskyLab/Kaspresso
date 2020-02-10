package com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.loader

import com.kaspersky.components.kautomator.intercept.interaction.UiObjectInteraction
import com.kaspersky.components.kautomator.intercept.operation.UiObjectAction
import com.kaspersky.components.kautomator.intercept.operation.UiObjectAssertion
import com.kaspersky.kaspresso.interceptors.behaviorkautomator.ObjectBehaviorInterceptor
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso.uiobjectloader.UiObjectLoaderProvider
import com.kaspersky.kaspresso.uiobjectloader.UiObjectLoaderProviderImpl

/**
 * The implementation of [ObjectBehaviorInterceptor] and [UiObjectLoaderProvider] interfaces.
 * Provides system flaky safety functionality for [UiObjectInteraction.perform] and [UiObjectInteraction.check] calls.
 */
class UiObjectLoaderBehaviorInterceptor(
    private val logger: UiTestLogger
) : ObjectBehaviorInterceptor,
    UiObjectLoaderProvider by UiObjectLoaderProviderImpl(logger) {

    /**
     * Wraps the given [activity] invocation with the UiObject2 repeated loading.
     *
     * @param interaction the intercepted [UiObjectInteraction].
     * @param assertion the intercepted [UiObjectAssertion].
     * @param activity the activity to invoke.
     */
    override fun <T> interceptCheck(
        interaction: UiObjectInteraction,
        assertion: UiObjectAssertion,
        activity: () -> T
    ): T = handleUiObjectAbsence(interaction = interaction, action = activity)

    /**
     * Wraps the given [activity] invocation with the UiObject2 repeated loading.
     *
     * @param interaction the intercepted [UiObjectInteraction].
     * @param action the intercepted [UiObjectAction].
     * @param activity the activity to invoke.
     */
    override fun <T> interceptPerform(
        interaction: UiObjectInteraction,
        action: UiObjectAction,
        activity: () -> T
    ): T = handleUiObjectAbsence(interaction = interaction, action = activity)
}