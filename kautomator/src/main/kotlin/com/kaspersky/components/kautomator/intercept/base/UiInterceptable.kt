package com.kaspersky.components.kautomator.intercept.base

import com.kaspersky.components.kautomator.intercept.delegate.UiDelegate

interface UiInterceptable<Interaction, Assertion, Action> {
    val view: UiDelegate<Interaction, Assertion, Action>

    /**
     * Sets the interceptors for the instance.
     * Interceptors will be invoked on the interaction with the UiView.
     *
     * @param builder Builder of the interceptors
     *
     * @see UiInterceptor
     */
    fun intercept(builder: UiInterceptor.Builder<Interaction, Assertion, Action>.() -> Unit) {
        view.interceptor = UiInterceptor.Builder<Interaction, Assertion, Action>().apply(builder).build()
    }

    /**
     * Removes the interceptors from the instance.
     */
    fun reset() {
        view.interceptor = null
    }
}