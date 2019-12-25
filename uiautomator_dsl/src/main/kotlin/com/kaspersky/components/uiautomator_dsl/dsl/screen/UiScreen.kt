package com.kaspersky.components.uiautomator_dsl.dsl.screen

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import com.kaspersky.components.uiautomator_dsl.dsl.common.UiAutomatorDslMarker
import com.kaspersky.components.uiautomator_dsl.intercepting.operations.*
import com.kaspersky.components.uiautomator_dsl.intercepting.interaction.UiDeviceInteraction
import com.kaspersky.components.uiautomator_dsl.intercepting.interaction.UiInteraction
import com.kaspersky.components.uiautomator_dsl.intercepting.intercept.Interceptor
import com.kaspersky.components.uiautomator_dsl.intercepting.proxy.UiDeviceProxy
import java.util.*

@Suppress("UNCHECKED_CAST")
@UiAutomatorDslMarker
open class UiScreen<out T : UiScreen<T>> : UiScreenActions {

    override val uiDeviceProxy: UiDeviceProxy = UiDeviceProxy(
        UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
    )

    private var uiInterceptor: Interceptor<UiInteraction, UiAssertion, UiAction>? = null
    private var uiDeviceInterceptor: Interceptor<UiDeviceInteraction, UiDeviceAssertion, UiDeviceAction>? = null
    private var isActive = false

    /**
     * Sets the interceptors for the screen.
     * Interceptors will be invoked on all interactions while the screen is active.
     *
     * The screen is considered `active` when it is invoked in one of the following ways:
     * ```
     * val screen = SomeScreen()
     *
     * screen { // Active
     *     view { click() }
     *     ...
     * } // Inactive
     *
     * // OR
     *
     * onScreen<SomeScreen>() { // Active
     *     view { click() }
     *     ...
     * } // Inactive
     * ```
     *
     * If you use nesting screens, all interceptors of the screens that became active will be invoked
     * in LIFO order (using Deque).
     *
     * @param configurator Configuration of the interceptors
     *
     * @see Interceptor
     */
    fun intercept(configurator: Interceptor.Configurator.() -> Unit) {
        if (isActive) {
            removeInterceptors()
        }

        Interceptor.Configurator().apply(configurator).configure().also { (uiInterceptor, uiDeviceInterceptor) ->
            this.uiInterceptor = uiInterceptor
            this.uiDeviceInterceptor = uiDeviceInterceptor
        }

        if (isActive) {
            addInterceptors()
        }
    }

    /**
     * Removes the interceptors from the screen.
     *
     * @see intercept
     * @see Interceptor
     */
    fun reset() {
        if (isActive) {
            removeInterceptors()
        }
        uiInterceptor = null
        uiDeviceInterceptor = null
    }

    operator fun invoke(function: T.() -> Unit) {
        isActive = true
        addInterceptors()

        function.invoke(this as T)

        isActive = false
        removeInterceptors()
    }

    private fun addInterceptors() {
        uiInterceptor?.let { UI_INTERCEPTORS.offerFirst(it) }
        uiDeviceInterceptor?.let { UI_DEVICE_INTERCEPTORS.offerFirst(it) }
    }

    private fun removeInterceptors() {
        uiInterceptor?.let { UI_INTERCEPTORS.removeFirstOccurrence(it) }
        uiDeviceInterceptor?.let { UI_DEVICE_INTERCEPTORS.removeFirstOccurrence(it) }
    }

    companion object {
        internal val UI_INTERCEPTORS: Deque<Interceptor<UiInteraction, UiAssertion, UiAction>> = LinkedList()
        internal val UI_DEVICE_INTERCEPTORS: Deque<Interceptor<UiDeviceInteraction, UiDeviceAssertion, UiDeviceAction>> = LinkedList()
    }
}
