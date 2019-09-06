package com.kaspersky.kaspresso.interceptors.behavior.impl.failure

import androidx.test.espresso.DataInteraction
import com.kaspersky.kaspresso.failure.FailureLoggingProvider
import com.kaspersky.kaspresso.interceptors.behavior.DataBehaviorInterceptor
import com.kaspersky.kaspresso.logger.UiTestLogger

class FailureLoggingDataBehaviorInterceptor(
    override val logger: UiTestLogger
) : DataBehaviorInterceptor, FailureLoggingProvider {

    override fun <R> intercept(interaction: DataInteraction, action: () -> R): R = withLoggingOnFailure(action)
}