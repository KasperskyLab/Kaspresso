package com.kaspersky.components.uiautomator_dsl

import androidx.test.uiautomator.UiObject2
import com.kaspersky.components.uiautomator_dsl.intercept.Interceptor
import com.kaspersky.components.uiautomator_dsl.proxy.UiAction
import com.kaspersky.components.uiautomator_dsl.proxy.UiAssert

object UiAutomatorConfigurator {

    internal var uiInterceptor: Interceptor<UiObject2?, UiAssert, UiAction>? = null

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
        Interceptor.Configurator().apply(configurator).configure().also { (uiInterceptor) ->
            this.uiInterceptor = uiInterceptor
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
    }

}