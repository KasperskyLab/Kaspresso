package com.kaspersky.kaspresso.interceptors.interactors.impl.flakysafety

import androidx.test.espresso.DataInteraction
import com.kaspersky.kaspresso.flakysafety.attempt
import com.kaspersky.kaspresso.interceptors.interactors.DataInteractor

class FlakySafeDataInteractor : DataInteractor {

    /**
     * Performs multiple attempts to interact an action or an assertion.
     *
     * @param execution a function-wrapper of an action or an assertion to be invoked.
     */
    override fun <R> interact(
        view: Any?,
        interaction: DataInteraction,
        interactable: () -> R
    ): R = attempt(action = interactable)
}