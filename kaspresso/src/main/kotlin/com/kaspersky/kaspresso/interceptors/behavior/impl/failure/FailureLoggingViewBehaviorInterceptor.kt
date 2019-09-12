package com.kaspersky.kaspresso.interceptors.behavior.impl.failure

import android.support.test.espresso.ViewInteraction
import com.kaspersky.kaspresso.failure.FailureLoggingProvider
import com.kaspersky.kaspresso.failure.FailureLoggingProviderImpl
import com.kaspersky.kaspresso.interceptors.behavior.ViewBehaviorInterceptor
import com.kaspersky.kaspresso.logger.UiTestLogger

/**
 * The implementation of [ViewBehaviorInterceptor] and [FailureLoggingProvider] interfaces.
 * Provides failure logging functionality for [ViewInteraction.perform] and [ViewInteraction.check] calls.
 */
class FailureLoggingViewBehaviorInterceptor(
    logger: UiTestLogger
) : ViewBehaviorInterceptor,
    FailureLoggingProvider by FailureLoggingProviderImpl(logger) {

    /**
     * Wraps the given [action] invocation with the failure logging.
     *
     * @param interaction the intercepted [ViewInteraction].
     * @param action the action to invoke.
     */
    override fun <T> intercept(interaction: ViewInteraction, action: () -> T): T = withLoggingOnFailure(action)
}