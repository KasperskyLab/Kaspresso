package com.kaspersky.components.uiautomator_dsl.dsl.screen

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import com.kaspersky.components.uiautomator_dsl.dsl.common.UiAutomatorDslMarker
import com.kaspersky.components.uiautomator_dsl.intercepting.actions.UiAction
import com.kaspersky.components.uiautomator_dsl.intercepting.asserts.UiAssert
import com.kaspersky.components.uiautomator_dsl.intercepting.interaction.UiObjectInteraction
import com.kaspersky.components.uiautomator_dsl.intercepting.intercept.Interceptor
import java.util.*

@Suppress("UNCHECKED_CAST")
@UiAutomatorDslMarker
open class UiScreen<out T : UiScreen<T>> : UiScreenActions {

    private var uiObjectInterceptor: Interceptor<UiObjectInteraction, UiAssert, UiAction>? = null
    private var isActive = false

    override val uiDevice: UiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

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

        Interceptor.Configurator().apply(configurator).configure().also { (uiInterceptor) ->
            this.uiObjectInterceptor = uiInterceptor
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
        uiObjectInterceptor = null
    }

    operator fun invoke(function: T.() -> Unit) {
        isActive = true
        addInterceptors()

        function.invoke(this as T)

        isActive = false
        removeInterceptors()
    }

    private fun addInterceptors() {
        uiObjectInterceptor?.let { UI_OBJECT_INTERCEPTORS.offerFirst(it) }
    }

    private fun removeInterceptors() {
        uiObjectInterceptor?.let { UI_OBJECT_INTERCEPTORS.removeFirstOccurrence(it) }
    }

    companion object {
        internal val UI_OBJECT_INTERCEPTORS: Deque<Interceptor<UiObjectInteraction, UiAssert, UiAction>> = LinkedList()
    }
}
