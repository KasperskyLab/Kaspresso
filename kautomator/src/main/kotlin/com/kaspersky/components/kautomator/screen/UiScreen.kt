@file:Suppress("unused")
package com.kaspersky.components.kautomator.screen

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import com.kaspersky.components.kautomator.common.Environment
import com.kaspersky.components.kautomator.common.KautomatorInUnitTestException
import com.kaspersky.components.kautomator.common.environment
import com.kaspersky.components.kautomator.component.common.KautomatorMarker
import com.kaspersky.components.kautomator.intercept.base.UiInterceptor
import com.kaspersky.components.kautomator.intercept.delegate.UiDeviceInteractionDelegate
import com.kaspersky.components.kautomator.intercept.interaction.UiDeviceInteraction
import com.kaspersky.components.kautomator.intercept.interaction.UiObjectInteraction
import com.kaspersky.components.kautomator.intercept.operation.UiDeviceAction
import com.kaspersky.components.kautomator.intercept.operation.UiDeviceAssertion
import com.kaspersky.components.kautomator.intercept.operation.UiObjectAction
import com.kaspersky.components.kautomator.intercept.operation.UiObjectAssertion
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

        inline fun <reified T : UiScreen<T>> onUiScreen(noinline function: T.() -> Unit): T {
            return T::class.java
                .getDeclaredConstructor()
                .newInstance()
                .apply { this(function) }
        }
    }

    abstract val packageName: String

    override val view: UiDeviceInteractionDelegate by lazy(LazyThreadSafetyMode.NONE) {
        if (environment != Environment.AndroidRuntime) throw KautomatorInUnitTestException()

        UiDeviceInteractionDelegate(
            UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        )
    }

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
