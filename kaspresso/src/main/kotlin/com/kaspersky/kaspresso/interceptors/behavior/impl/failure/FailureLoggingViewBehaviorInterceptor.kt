package com.kaspersky.kaspresso.interceptors.behavior.impl.failure

import androidx.test.espresso.ViewInteraction
import com.kaspersky.kaspresso.failure.FailureLoggingProvider
import com.kaspersky.kaspresso.failure.FailureLoggingProviderImpl
import com.kaspersky.kaspresso.interceptors.behavior.ViewBehaviorInterceptor
import com.kaspersky.kaspresso.logger.UiTestLogger

/**
 * The implementation of [ViewBehaviorInterceptor] and [FailureLoggingProvider] interfaces.
 * Provides failure logging functionality for [ViewInteraction.perform] and [ViewInteraction.check] calls.
 *
 * Important!
 * By default, the interceptor is not used in Kaspresso because this one pollutes logs by error messages that may confuse a user.
 * If you desire to change result log (especially in case of an error) we recommend to use [FailureLoggingProvider] directly.
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
