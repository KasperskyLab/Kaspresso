package com.kaspersky.kaspresso.interceptors.interactors.impl.flakysafety

import androidx.test.espresso.DataInteraction
import com.kaspersky.kaspresso.flakysafety.FlakySafetyParams
import com.kaspersky.kaspresso.flakysafety.FlakySafetyProvider
import com.kaspersky.kaspresso.interceptors.interactors.DataInteractor
import com.kaspersky.kaspresso.logger.UiTestLogger

class FlakySafeDataInteractor(
    override val params: FlakySafetyParams,
    override val logger: UiTestLogger
) : DataInteractor, FlakySafetyProvider {

    /**
     * Performs multiple attempts to interact an action or an assertion.
     *
     * @param execution a function-wrapper of an action or an assertion to be invoked.
     */
    override fun <R> interact(interaction: DataInteraction, action: () -> R): R = flakySafely(action = action)
}