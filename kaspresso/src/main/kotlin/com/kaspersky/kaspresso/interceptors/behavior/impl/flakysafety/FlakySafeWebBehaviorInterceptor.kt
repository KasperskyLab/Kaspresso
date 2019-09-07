package com.kaspersky.kaspresso.interceptors.behavior.impl.flakysafety

import androidx.test.espresso.web.sugar.Web
import com.kaspersky.kaspresso.flakysafety.FlakySafetyParams
import com.kaspersky.kaspresso.flakysafety.FlakySafetyProvider
import com.kaspersky.kaspresso.flakysafety.FlakySafetyProviderImpl
import com.kaspersky.kaspresso.interceptors.behavior.WebBehaviorInterceptor
import com.kaspersky.kaspresso.logger.UiTestLogger

class FlakySafeWebBehaviorInterceptor(
    params: FlakySafetyParams,
    logger: UiTestLogger
) : WebBehaviorInterceptor,
    FlakySafetyProvider by FlakySafetyProviderImpl(params, logger) {

    /**
     * Performs multiple attempts to interact an action or an assertion.
     *
     * @param execution a function-wrapper of an action or an assertion to be invoked.
     */
    override fun <T> intercept(interaction: Web.WebInteraction<*>, action: () -> T): T = flakySafely(action = action)
}