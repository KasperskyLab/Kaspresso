package com.kaspersky.kaspresso.interceptors.behavior.impl.flakysafety

import androidx.test.espresso.web.sugar.Web
import com.kaspersky.kaspresso.flakysafety.FlakySafetyProvider
import com.kaspersky.kaspresso.flakysafety.FlakySafetyProviderSimpleImpl
import com.kaspersky.kaspresso.interceptors.behavior.WebBehaviorInterceptor
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso.params.FlakySafetyParams

/**
 * The implementation of [WebBehaviorInterceptor] and [FlakySafetyProvider] interfaces.
 * Provides system flaky safety functionality for [Web.WebInteraction.perform] and [Web.WebInteraction.check] calls.
 */
class FlakySafeWebBehaviorInterceptor(
    params: FlakySafetyParams,
    logger: UiTestLogger
) : WebBehaviorInterceptor,
    FlakySafetyProvider by FlakySafetyProviderSimpleImpl(params, logger) {

    /**
     * Wraps the given [action] invocation with the flaky safety.
     *
     * @param interaction the intercepted [Web.WebInteraction].
     * @param action the action to invoke.
     */
    override fun <T> intercept(interaction: Web.WebInteraction<*>, action: () -> T): T = flakySafely(action = action)
}
