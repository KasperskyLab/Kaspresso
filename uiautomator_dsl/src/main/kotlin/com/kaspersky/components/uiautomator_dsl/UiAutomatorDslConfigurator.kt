package com.kaspersky.components.uiautomator_dsl

import com.kaspersky.components.uiautomator_dsl.intercepting.action_and_assertion.UiDeviceAction
import com.kaspersky.components.uiautomator_dsl.intercepting.action_and_assertion.UiDeviceAssertion
import com.kaspersky.components.uiautomator_dsl.intercepting.action_and_assertion.UiObjectAction
import com.kaspersky.components.uiautomator_dsl.intercepting.action_and_assertion.UiObjectAssertion
import com.kaspersky.components.uiautomator_dsl.intercepting.interaction.UiDeviceInteraction
import com.kaspersky.components.uiautomator_dsl.intercepting.interaction.UiObjectInteraction
import com.kaspersky.components.uiautomator_dsl.intercepting.intercept.UiInterceptor

object UiAutomatorDslConfigurator {

    internal var uiObjectInterceptor: UiInterceptor<UiObjectInteraction, UiObjectAssertion, UiObjectAction>? = null
    internal var uiDeviceInterceptor: UiInterceptor<UiDeviceInteraction, UiDeviceAssertion, UiDeviceAction>? = null

    /**
     * Operator that allows usage of DSL style
     *
     * @param function tail lambda with receiver which is your UiAutomator DSL runtime
     */
    operator fun invoke(function: UiAutomatorDslConfigurator.() -> Unit) {
        function(this)
    }

    /**
     * Sets the interceptors for the whole UiAutomator DSL runtime.
     * Interceptors will be invoked on all of the interactions with the UiView instances.
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
     * Removes the interceptors from the UiAutomator DSL runtime.
     *
     * @see intercept
     * @see UiInterceptor
     */
    fun reset() {
        uiObjectInterceptor = null
        uiDeviceInterceptor = null
    }
}