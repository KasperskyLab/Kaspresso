package com.kaspersky.kaspresso.interceptors.interactors.impl.flakysafety

import androidx.test.espresso.web.sugar.Web
import com.kaspersky.kaspresso.flakysafety.attempt
import com.kaspersky.kaspresso.interceptors.interactors.WebInteractor

class FlakySafeWebInteractor : WebInteractor {

    /**
     * Performs multiple attempts to interact an action or an assertion.
     *
     * @param execution a function-wrapper of an action or an assertion to be invoked.
     */
    override fun <R> interact(
        view: Any?,
        interaction: Web.WebInteraction<*>,
        interactable: () -> R
    ): R = attempt(action = interactable)
}