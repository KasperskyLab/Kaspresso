package com.kaspersky.components.uiautomator_dsl

import com.kaspersky.components.uiautomator_dsl.intercepting.action_and_assertion.*
import com.kaspersky.components.uiautomator_dsl.intercepting.intercept.UiInterceptor
import com.kaspersky.components.uiautomator_dsl.intercepting.interaction.UiDeviceInteraction
import com.kaspersky.components.uiautomator_dsl.intercepting.interaction.UiObjectInteraction

object UiAutomatorConfigurator {

    internal var uiObjectInterceptor: UiInterceptor<UiObjectInteraction, UiObjectAssertion, UiObjectAction>? = null
    internal var uiDeviceInterceptor: UiInterceptor<UiDeviceInteraction, UiDeviceAssertion, UiDeviceAction>? = null

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
     * @see UiInterceptor
     */
    fun intercept(configurator: UiInterceptor.Configurator.() -> Unit) {
        UiInterceptor.Configurator().apply(configurator).configure().also { (uiInterceptor, uiDeviceInterceptor) ->
            this.uiObjectInterceptor = uiInterceptor
            this.uiDeviceInterceptor = uiDeviceInterceptor
        }
    }

    /**
     * Removes the interceptors from the Kakao runtime.
     *
     * @see intercept
     * @see UiInterceptor
     */
    fun reset() {
        uiObjectInterceptor = null
        uiDeviceInterceptor = null
    }

}