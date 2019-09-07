package com.kaspersky.kaspresso.interceptors.behavior.impl.failure

import androidx.test.espresso.web.sugar.Web
import com.kaspersky.kaspresso.failure.FailureLoggingProvider
import com.kaspersky.kaspresso.failure.FailureLoggingProviderImpl
import com.kaspersky.kaspresso.interceptors.behavior.WebBehaviorInterceptor
import com.kaspersky.kaspresso.logger.UiTestLogger

class FailureLoggingWebBehaviorInterceptor(
    private val logger: UiTestLogger
) : WebBehaviorInterceptor,
    FailureLoggingProvider by FailureLoggingProviderImpl(logger) {

    override fun <R> intercept(interaction: Web.WebInteraction<*>, action: () -> R): R = withLoggingOnFailure(action)
}