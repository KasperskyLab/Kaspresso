package com.kaspersky.kaspresso.interceptors.behavior.impl.flakysafety

import androidx.test.espresso.ViewInteraction
import com.kaspersky.kaspresso.flakysafety.FlakySafetyProvider
import com.kaspersky.kaspresso.flakysafety.FlakySafetyProviderSimpleImpl
import com.kaspersky.kaspresso.interceptors.behavior.ViewBehaviorInterceptor
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso.params.FlakySafetyParams

/**
 * The implementation of [ViewBehaviorInterceptor] and [FlakySafetyProvider] interfaces.
 * Provides system flaky safety functionality for [ViewInteraction.perform] and [ViewInteraction.check] calls.
 */
class FlakySafeViewBehaviorInterceptor(
    params: FlakySafetyParams,
    logger: UiTestLogger
) : ViewBehaviorInterceptor,
    FlakySafetyProvider by FlakySafetyProviderSimpleImpl(params, logger) {

    /**
     * Wraps the given [action] invocation with the flaky safety.
     *
     * @param interaction the intercepted [ViewInteraction].
     * @param action the action to invoke.
     */
    override fun <T> intercept(interaction: ViewInteraction, action: () -> T): T = flakySafely(action = action)
}