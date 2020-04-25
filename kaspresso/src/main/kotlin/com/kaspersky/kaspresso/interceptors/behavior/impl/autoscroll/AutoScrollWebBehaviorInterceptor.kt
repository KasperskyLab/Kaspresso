package com.kaspersky.kaspresso.interceptors.behavior.impl.autoscroll

import androidx.test.espresso.web.sugar.Web
import com.kaspersky.kaspresso.autoscroll.AutoScrollProvider
import com.kaspersky.kaspresso.autoscroll.WebAutoScrollProviderImpl
import com.kaspersky.kaspresso.interceptors.behavior.WebBehaviorInterceptor
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso.params.AutoScrollParams

/**
 * The implementation of [WebBehaviorInterceptor] and [AutoScrollProvider] interfaces.
 * Provides autoscroll on failure functionality for [Web.WebInteraction.perform] and [Web.WebInteraction.check] calls.
 */
class AutoScrollWebBehaviorInterceptor(
    params: AutoScrollParams,
    logger: UiTestLogger
) : WebBehaviorInterceptor,
    AutoScrollProvider<Web.WebInteraction<*>> by WebAutoScrollProviderImpl(params, logger) {

    /**
     * Wraps the given [action] invocation with the autoscrolling on failure.
     *
     * @param interaction the intercepted [Web.WebInteraction].
     * @param action the action to invoke.
     */
    override fun <T> intercept(interaction: Web.WebInteraction<*>, action: () -> T): T =
        withAutoScroll(interaction, action)
}
