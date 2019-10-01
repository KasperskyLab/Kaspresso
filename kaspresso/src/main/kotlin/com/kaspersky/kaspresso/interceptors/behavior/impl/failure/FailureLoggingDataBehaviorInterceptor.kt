package com.kaspersky.kaspresso.interceptors.behavior.impl.failure

import androidx.test.espresso.DataInteraction
import com.kaspersky.kaspresso.failure.FailureLoggingProvider
import com.kaspersky.kaspresso.failure.FailureLoggingProviderImpl
import com.kaspersky.kaspresso.interceptors.behavior.DataBehaviorInterceptor
import com.kaspersky.kaspresso.logger.UiTestLogger

/**
 * The implementation of [DataBehaviorInterceptor] and [FailureLoggingProvider] interfaces.
 * Provides failure logging functionality for [DataInteraction.check] calls.
 */
class FailureLoggingDataBehaviorInterceptor(
    logger: UiTestLogger
) : DataBehaviorInterceptor,
    FailureLoggingProvider<DataInteraction> by FailureLoggingProviderImpl(logger) {

    /**
     * Wraps the given [action] invocation with the failure logging.
     *
     * @param interaction the intercepted [DataInteraction].
     * @param action the action to invoke.
     */
    override fun <T> intercept(interaction: DataInteraction, action: () -> T): T =
        withLoggingOnFailure(interaction, action)
}