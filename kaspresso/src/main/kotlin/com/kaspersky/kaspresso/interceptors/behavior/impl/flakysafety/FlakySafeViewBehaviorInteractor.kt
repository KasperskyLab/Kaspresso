package com.kaspersky.kaspresso.interceptors.behavior.impl.flakysafety

import androidx.test.espresso.ViewInteraction
import com.kaspersky.kaspresso.flakysafety.attempt
import com.kaspersky.kaspresso.interceptors.behavior.ViewBehaviorInteractor

/**
 * An implementation of [Interactor] that performs multiple attempts to interact an action or an assertion to
 * provide flaky safety.
 */
class FlakySafeViewBehaviorInteractor : ViewBehaviorInteractor {

    /**
     * Performs multiple attempts to interact an action or an assertion.
     *
     * @param execution a function-wrapper of an action or an assertion to be invoked.
     */
    override fun <R> interact(interaction: ViewInteraction, action: () -> R): R = attempt(action = action)
}