package com.kaspersky.kaspresso.interceptors.behavior.impl.failure

import androidx.test.espresso.ViewInteraction
import com.kaspersky.kaspresso.failure.FailureLoggingProvider
import com.kaspersky.kaspresso.failure.FailureLoggingProviderImpl
import com.kaspersky.kaspresso.interceptors.behavior.ViewBehaviorInterceptor
import com.kaspersky.kaspresso.logger.UiTestLogger

class FailureLoggingViewBehaviorInterceptor(
    logger: UiTestLogger
) : ViewBehaviorInterceptor,
    FailureLoggingProvider by FailureLoggingProviderImpl(logger) {

    override fun <R> intercept(interaction: ViewInteraction, action: () -> R): R = withLoggingOnFailure(action)
}