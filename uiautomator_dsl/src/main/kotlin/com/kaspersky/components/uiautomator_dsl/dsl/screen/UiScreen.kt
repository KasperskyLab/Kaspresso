package com.kaspersky.components.uiautomator_dsl.dsl.screen

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import com.kaspersky.components.uiautomator_dsl.dsl.common.UiAutomatorDslMarker
import com.kaspersky.components.uiautomator_dsl.intercepting.action_and_assertion.*
import com.kaspersky.components.uiautomator_dsl.intercepting.interaction.UiDeviceInteraction
import com.kaspersky.components.uiautomator_dsl.intercepting.interaction.UiInteraction
import com.kaspersky.components.uiautomator_dsl.intercepting.intercept.UiInterceptor
import com.kaspersky.components.uiautomator_dsl.intercepting.delegate.UiDeviceDelegate
import java.util.*

@Suppress("UNCHECKED_CAST")
@UiAutomatorDslMarker
open class UiScreen<out T : UiScreen<T>> : UiScreenActions {

    override val proxyDevice: UiDeviceDelegate = UiDeviceDelegate(
        UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
    )

    private var uiInterceptor: UiInterceptor<UiInteraction, UiAssertion, UiAction>? = null
    private var uiDeviceInterceptor: UiInterceptor<UiDeviceInteraction, UiDeviceAssertion, UiDeviceAction>? = null
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
     * @see UiInterceptor
     */
    fun intercept(configurator: UiInterceptor.Configurator.() -> Unit) {
        if (isActive) {
            removeInterceptors()
        }

        UiInterceptor.Configurator().apply(configurator).configure().also { (uiInterceptor, uiDeviceInterceptor) ->
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
     * @see UiInterceptor
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
        internal val UI_INTERCEPTORS: Deque<UiInterceptor<UiInteraction, UiAssertion, UiAction>> = LinkedList()
        internal val UI_DEVICE_INTERCEPTORS: Deque<UiInterceptor<UiDeviceInteraction, UiDeviceAssertion, UiDeviceAction>> = LinkedList()
    }
}
