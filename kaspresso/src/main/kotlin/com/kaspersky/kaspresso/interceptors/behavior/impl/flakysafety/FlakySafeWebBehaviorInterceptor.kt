package com.kaspersky.kaspresso.interceptors.behavior.impl.flakysafety

import androidx.test.espresso.web.sugar.Web
import com.kaspersky.kaspresso.flakysafety.FlakySafetyParams
import com.kaspersky.kaspresso.flakysafety.FlakySafetyProvider
import com.kaspersky.kaspresso.interceptors.behavior.WebBehaviorInterceptor
import com.kaspersky.kaspresso.logger.UiTestLogger

class FlakySafeWebBehaviorInterceptor(
    override val params: FlakySafetyParams,
    override val logger: UiTestLogger
) : WebBehaviorInterceptor, FlakySafetyProvider {

    /**
     * Performs multiple attempts to interact an action or an assertion.
     *
     * @param execution a function-wrapper of an action or an assertion to be invoked.
     */
    override fun <R> interact(interaction: Web.WebInteraction<*>, action: () -> R): R = flakySafely(action = action)
}