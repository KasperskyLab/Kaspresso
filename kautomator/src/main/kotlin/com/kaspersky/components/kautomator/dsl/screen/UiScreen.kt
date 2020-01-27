package com.kaspersky.components.kautomator.dsl.screen

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import com.kaspersky.components.kautomator.dsl.common.KautomatorMarker
import com.kaspersky.components.kautomator.intercepting.delegate.UiDeviceDelegate
import com.kaspersky.components.kautomator.intercepting.interaction.UiDeviceInteraction
import com.kaspersky.components.kautomator.intercepting.interaction.UiObjectInteraction
import com.kaspersky.components.kautomator.intercepting.intercept.UiInterceptor
import com.kaspersky.components.kautomator.intercepting.operation.UiDeviceAction
import com.kaspersky.components.kautomator.intercepting.operation.UiDeviceAssertion
import com.kaspersky.components.kautomator.intercepting.operation.UiObjectAction
import com.kaspersky.components.kautomator.intercepting.operation.UiObjectAssertion
import java.util.Deque
import java.util.LinkedList

/**
 * Container class for UiAutomator elements.
 *
 * This class groups UI elements and grants access to basic actions,
 * such as pressBack
 *
 * @param T type of your screen, done to enable invoke() for its children
 *
 * @see UiScreenActions
 */
@Suppress("UNCHECKED_CAST")
@KautomatorMarker
abstract class UiScreen<out T : UiScreen<T>> : UiScreenActions {

    companion object {
        internal val UI_OBJECT_INTERCEPTORS: Deque<UiInterceptor<UiObjectInteraction, UiObjectAssertion, UiObjectAction>> = LinkedList()
        internal val UI_DEVICE_INTERCEPTORS: Deque<UiInterceptor<UiDeviceInteraction, UiDeviceAssertion, UiDeviceAction>> = LinkedList()
    }

    abstract val packageName: String

    override val view: UiDeviceDelegate = UiDeviceDelegate(
        UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
    )

    private var uiObjectInterceptor: UiInterceptor<UiObjectInteraction, UiObjectAssertion, UiObjectAction>? = null
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
            this.uiObjectInterceptor = uiInterceptor
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
        uiObjectInterceptor = null
        uiDeviceInterceptor = null
    }

    /**
     * Operator that allows usage of DSL style
     *
     * @param function Tail lambda with receiver which is your screen
     */
    operator fun invoke(function: T.() -> Unit) {
        isActive = true
        addInterceptors()

        function.invoke(this as T)

        isActive = false
        removeInterceptors()
    }

    private fun addInterceptors() {
        uiObjectInterceptor?.let { UI_OBJECT_INTERCEPTORS.offerFirst(it) }
        uiDeviceInterceptor?.let { UI_DEVICE_INTERCEPTORS.offerFirst(it) }
    }

    private fun removeInterceptors() {
        uiObjectInterceptor?.let { UI_OBJECT_INTERCEPTORS.removeFirstOccurrence(it) }
        uiDeviceInterceptor?.let { UI_DEVICE_INTERCEPTORS.removeFirstOccurrence(it) }
    }
}
