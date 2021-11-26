package com.kaspersky.kaspresso.interceptors.behavior.impl.failure

import androidx.test.espresso.web.sugar.Web
import com.kaspersky.kaspresso.failure.FailureLoggingProvider
import com.kaspersky.kaspresso.failure.FailureLoggingProviderImpl
import com.kaspersky.kaspresso.interceptors.behavior.WebBehaviorInterceptor
import com.kaspersky.kaspresso.logger.UiTestLogger

/**
 * The implementation of [WebBehaviorInterceptor] and [FailureLoggingProvider] interfaces.
 * Provides failure logging functionality for [Web.WebInteraction.perform] and [Web.WebInteraction.check] calls.
 *
 * Important!
 * By default, the interceptor is not used in Kaspresso because this one pollutes logs by error messages that may confuse a user.
 * If you desire to change result log (especially in case of an error) we recommend to use [FailureLoggingProvider] directly.
 */
class FailureLoggingWebBehaviorInterceptor(
    logger: UiTestLogger
) : WebBehaviorInterceptor,
    FailureLoggingProvider by FailureLoggingProviderImpl(logger) {

    /**
     * Wraps the given [action] invocation with the failure logging.
     *
     * @param interaction the intercepted [Web.WebInteraction].
     * @param action the action to invoke.
     */
    override fun <T> intercept(interaction: Web.WebInteraction<*>, action: () -> T): T = withLoggingOnFailure(action)
}
