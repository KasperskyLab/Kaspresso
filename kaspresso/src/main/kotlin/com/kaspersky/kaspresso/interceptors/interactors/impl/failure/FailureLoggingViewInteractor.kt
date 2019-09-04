package com.kaspersky.kaspresso.interceptors.interactors.impl.failure

import androidx.test.espresso.ViewInteraction
import com.kaspersky.kaspresso.failure.FailureLoggingProvider
import com.kaspersky.kaspresso.interceptors.interactors.ViewInteractor
import com.kaspersky.kaspresso.logger.UiTestLogger

class FailureLoggingViewInteractor(
    override val logger: UiTestLogger
) : ViewInteractor, FailureLoggingProvider {

    override fun <R> interact(interaction: ViewInteraction, action: () -> R): R = withLoggingOnFailure(action)
}