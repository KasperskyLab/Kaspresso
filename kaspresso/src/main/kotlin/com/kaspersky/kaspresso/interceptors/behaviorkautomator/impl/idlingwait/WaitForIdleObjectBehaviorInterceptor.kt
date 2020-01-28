package com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.idlingwait

import androidx.test.uiautomator.Configurator
import com.kaspersky.components.kautomator.intercepting.interaction.UiObjectInteraction
import com.kaspersky.components.kautomator.intercepting.operation.UiObjectAction
import com.kaspersky.components.kautomator.intercepting.operation.UiObjectAssertion
import com.kaspersky.kaspresso.flakysafety.FlakySafetyProvider
import com.kaspersky.kaspresso.idlewaiting.WaitForIdleProvider
import com.kaspersky.kaspresso.idlewaiting.WaitForIdleProviderImpl
import com.kaspersky.kaspresso.interceptors.behaviorkautomator.ObjectBehaviorInterceptor
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso.params.WaitForIdleParams

/**
 * The implementation of [ObjectBehaviorInterceptor] and [WaitForIdleProvider] interfaces.
 * Provides system flaky safety functionality for [UiObjectInteraction.perform] and [UiObjectInteraction.check] calls.
 */
class WaitForIdleObjectBehaviorInterceptor(
    configurator: Configurator,
    waitForIdleParams: WaitForIdleParams,
    logger: UiTestLogger
) : ObjectBehaviorInterceptor,
    WaitForIdleProvider by WaitForIdleProviderImpl(configurator, waitForIdleParams, logger) {

    /**
     * Wraps the given [activity] invocation with the flaky safety.
     *
     * @param interaction the intercepted [UiObjectInteraction].
     * @param assertion the intercepted [UiObjectAssertion].
     * @param activity the activity to invoke.
     */
    override fun <T> interceptCheck(
        interaction: UiObjectInteraction,
        assertion: UiObjectAssertion,
        activity: () -> T
    ): T = handleLongIdlingWait(action = activity)

    /**
     * Wraps the given [activity] invocation with the flaky safety.
     *
     * @param interaction the intercepted [UiObjectInteraction].
     * @param action the intercepted [UiObjectAction].
     * @param activity the activity to invoke.
     */
    override fun <T> interceptPerform(
        interaction: UiObjectInteraction,
        action: UiObjectAction,
        activity: () -> T
    ): T = handleLongIdlingWait(action = activity)
}