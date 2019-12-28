package com.kaspersky.components.uiautomator_dsl.intercepting.intercept

import com.kaspersky.components.uiautomator_dsl.intercepting.delegate.UiDelegate

interface UiInterceptable<INTERACTION, ASSERTION, ACTION> {
    val view: UiDelegate<INTERACTION, ASSERTION, ACTION>

    /**
     * Sets the interceptors for the instance.
     * Interceptors will be invoked on the interaction with the UiView.
     *
     * @param builder Builder of the interceptors
     *
     * @see UiInterceptor
     */
    fun intercept(builder: UiInterceptor.Builder<INTERACTION, ASSERTION, ACTION>.() -> Unit) {
        view.interceptor = UiInterceptor.Builder<INTERACTION, ASSERTION, ACTION>().apply(builder).build()
    }

    /**
     * Removes the interceptors from the instance.
     */
    fun reset() {
        view.interceptor = null
    }
}