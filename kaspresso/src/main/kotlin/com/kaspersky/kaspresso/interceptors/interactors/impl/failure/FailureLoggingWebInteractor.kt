package com.kaspersky.kaspresso.interceptors.interactors.impl.failure

import androidx.test.espresso.web.sugar.Web
import com.kaspersky.kaspresso.failure.FailureLoggingProvider
import com.kaspersky.kaspresso.interceptors.interactors.WebInteractor
import com.kaspersky.kaspresso.logger.UiTestLogger

class FailureLoggingWebInteractor(
    override val logger: UiTestLogger
) : WebInteractor, FailureLoggingProvider {

    override fun <R> interact(interaction: Web.WebInteraction<*>, action: () -> R): R = withLoggingOnFailure(action)
}