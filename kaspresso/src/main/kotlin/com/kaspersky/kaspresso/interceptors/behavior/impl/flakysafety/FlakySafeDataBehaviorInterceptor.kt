package com.kaspersky.kaspresso.interceptors.behavior.impl.flakysafety

import androidx.test.espresso.DataInteraction
import com.kaspersky.kaspresso.flakysafety.FlakySafetyParams
import com.kaspersky.kaspresso.flakysafety.FlakySafetyProvider
import com.kaspersky.kaspresso.flakysafety.FlakySafetyProviderImpl
import com.kaspersky.kaspresso.interceptors.behavior.DataBehaviorInterceptor
import com.kaspersky.kaspresso.logger.UiTestLogger

class FlakySafeDataBehaviorInterceptor(
    params: FlakySafetyParams,
    logger: UiTestLogger
) : DataBehaviorInterceptor,
    FlakySafetyProvider by FlakySafetyProviderImpl(params, logger) {

    /**
     * Performs multiple attempts to interact an action or an assertion.
     *
     * @param execution a function-wrapper of an action or an assertion to be invoked.
     */
    override fun <R> intercept(interaction: DataInteraction, action: () -> R): R = flakySafely(action = action)
}