package com.kaspersky.components.uiautomator_dsl

import com.kaspersky.components.uiautomator_dsl.intercepting.actions.*
import com.kaspersky.components.uiautomator_dsl.intercepting.intercept.Interceptor
import com.kaspersky.components.uiautomator_dsl.intercepting.interaction.UiDeviceInteraction
import com.kaspersky.components.uiautomator_dsl.intercepting.interaction.UiInteraction

object UiAutomatorConfigurator {

    internal var uiInterceptor: Interceptor<UiInteraction, UiAssertion, UiAction>? = null
    internal var uiDeviceInterceptor: Interceptor<UiDeviceInteraction, UiDeviceAssertion, UiDeviceAction>? = null

    /**
     * Operator that allows usage of DSL style
     *
     * @param function Tail lambda with receiver which is your Kakao runtime
     */
    operator fun invoke(function: UiAutomatorConfigurator.() -> Unit) {
        function(this)
    }

    /**
     * Sets the interceptors for the whole Kakao runtime.
     * Interceptors will be invoked on all of the interactions with the KView instances.
     *
     * @param configurator Configuration of the interceptors
     *
     * @see Interceptor
     */
    fun intercept(configurator: Interceptor.Configurator.() -> Unit) {
        Interceptor.Configurator().apply(configurator).configure().also { (uiInterceptor, uiDeviceInterceptor) ->
            this.uiInterceptor = uiInterceptor
            this.uiDeviceInterceptor = uiDeviceInterceptor
        }
    }

    /**
     * Removes the interceptors from the Kakao runtime.
     *
     * @see intercept
     * @see Interceptor
     */
    fun reset() {
        uiInterceptor = null
        uiDeviceInterceptor = null
    }

}