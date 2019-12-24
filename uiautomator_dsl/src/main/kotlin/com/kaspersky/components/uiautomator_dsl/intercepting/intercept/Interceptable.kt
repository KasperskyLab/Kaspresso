package com.kaspersky.components.uiautomator_dsl.intercepting.intercept

import com.kaspersky.components.uiautomator_dsl.intercepting.proxy.Proxy

interface Interceptable<INTERACTION, ASSERTION, ACTION> {
    val view: Proxy<INTERACTION, ASSERTION, ACTION>

    /**
     * Sets the interceptors for the instance.
     * Interceptors will be invoked on the objectInteraction with the KView.
     *
     * @param builder Builder of the interceptors
     *
     * @see Interceptor
     */
    fun intercept(builder: Interceptor.Builder<INTERACTION, ASSERTION, ACTION>.() -> Unit) {
        view.interceptor = Interceptor.Builder<INTERACTION, ASSERTION, ACTION>().apply(builder).build()
    }

    /**
     * Removes the interceptors from the instance.
     */
    fun reset() {
        view.interceptor = null
    }
}