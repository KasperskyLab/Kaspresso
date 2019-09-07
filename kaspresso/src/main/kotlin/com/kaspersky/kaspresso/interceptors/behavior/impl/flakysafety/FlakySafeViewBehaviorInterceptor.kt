package com.kaspersky.kaspresso.interceptors.behavior.impl.flakysafety

import androidx.test.espresso.ViewInteraction
import com.kaspersky.kaspresso.flakysafety.FlakySafetyParams
import com.kaspersky.kaspresso.flakysafety.FlakySafetyProvider
import com.kaspersky.kaspresso.flakysafety.FlakySafetyProviderImpl
import com.kaspersky.kaspresso.interceptors.behavior.ViewBehaviorInterceptor
import com.kaspersky.kaspresso.logger.UiTestLogger

/**
 * An implementation of [Interactor] that performs multiple attempts to interact an action or an assertion to
 * provide flaky safety.
 */
class FlakySafeViewBehaviorInterceptor(
    val params: FlakySafetyParams,
    val logger: UiTestLogger
) : ViewBehaviorInterceptor,
    FlakySafetyProvider by FlakySafetyProviderImpl(params, logger) {

    /**
     * Performs multiple attempts to interact an action or an assertion.
     *
     * @param execution a function-wrapper of an action or an assertion to be invoked.
     */
    override fun <R> intercept(interaction: ViewInteraction, action: () -> R): R = flakySafely(action = action)
}