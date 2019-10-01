package com.kaspersky.kaspresso.interceptors.behavior.impl.failure

import androidx.test.espresso.web.sugar.Web
import com.kaspersky.kaspresso.failure.FailureLoggingProvider
import com.kaspersky.kaspresso.failure.FailureLoggingProviderImpl
import com.kaspersky.kaspresso.interceptors.behavior.WebBehaviorInterceptor
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso.failure.describe.FailedWebAssertionDescriber

/**
 * The implementation of [WebBehaviorInterceptor] and [FailureLoggingProvider] interfaces.
 * Provides failure logging functionality for [Web.WebInteraction.perform] and [Web.WebInteraction.check] calls.
 */
class FailureLoggingWebBehaviorInterceptor(
    logger: UiTestLogger,
    failedWebAssertionDescriber: FailedWebAssertionDescriber? = null
) : WebBehaviorInterceptor,
    FailureLoggingProvider<Web.WebInteraction<*>> by FailureLoggingProviderImpl(logger, failedWebAssertionDescriber) {

    /**
     * Wraps the given [action] invocation with the failure logging.
     *
     * @param interaction the intercepted [Web.WebInteraction].
     * @param action the action to invoke.
     */
    override fun <T> intercept(interaction: Web.WebInteraction<*>, action: () -> T): T =
        withLoggingOnFailure(interaction, action)
}