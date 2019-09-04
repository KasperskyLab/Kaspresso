package com.kaspersky.kaspresso.interceptors.interactors.impl.failure

import androidx.test.espresso.DataInteraction
import com.kaspersky.kaspresso.failure.FailureLoggingProvider
import com.kaspersky.kaspresso.interceptors.interactors.DataInteractor
import com.kaspersky.kaspresso.logger.UiTestLogger

class FailureLoggingDataInteractor(
    override val logger: UiTestLogger
) : DataInteractor, FailureLoggingProvider {

    override fun <R> interact(interaction: DataInteraction, action: () -> R): R = withLoggingOnFailure(action)
}