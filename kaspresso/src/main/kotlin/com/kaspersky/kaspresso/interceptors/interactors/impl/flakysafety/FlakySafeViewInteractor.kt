package com.kaspersky.kaspresso.interceptors.interactors.impl.flakysafety

import androidx.test.espresso.ViewInteraction
import com.kaspersky.kaspresso.flakysafety.attempt
import com.kaspersky.kaspresso.interceptors.interactors.ViewInteractor

/**
 * An implementation of [Interactor] that performs multiple attempts to interact an action or an assertion to
 * provide flaky safety.
 */
class FlakySafeViewInteractor : ViewInteractor {

    /**
     * Performs multiple attempts to interact an action or an assertion.
     *
     * @param execution a function-wrapper of an action or an assertion to be invoked.
     */
    override fun <R> interact(
        view: Any?,
        interaction: ViewInteraction,
        interactable: () -> R
    ): R = attempt(action = interactable)
}