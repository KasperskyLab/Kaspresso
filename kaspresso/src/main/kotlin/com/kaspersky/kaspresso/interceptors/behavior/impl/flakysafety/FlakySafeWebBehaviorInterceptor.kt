package com.kaspersky.kaspresso.interceptors.behavior.impl.flakysafety

import androidx.test.espresso.web.sugar.Web
import com.kaspersky.kaspresso.flakysafety.attempt
import com.kaspersky.kaspresso.interceptors.behavior.WebBehaviorInterceptor

class FlakySafeWebBehaviorInterceptor : WebBehaviorInterceptor {

    /**
     * Performs multiple attempts to interact an action or an assertion.
     *
     * @param execution a function-wrapper of an action or an assertion to be invoked.
     */
    override fun <R> interact(interaction: Web.WebInteraction<*>, action: () -> R): R = attempt(action = action)
}