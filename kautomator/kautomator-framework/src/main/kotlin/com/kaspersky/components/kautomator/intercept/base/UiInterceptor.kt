package com.kaspersky.components.kautomator.intercept.base

import com.kaspersky.components.kautomator.intercept.interaction.UiDeviceInteraction
import com.kaspersky.components.kautomator.intercept.interaction.UiObjectInteraction
import com.kaspersky.components.kautomator.intercept.operation.UiDeviceAction
import com.kaspersky.components.kautomator.intercept.operation.UiDeviceAssertion
import com.kaspersky.components.kautomator.intercept.operation.UiObjectAction
import com.kaspersky.components.kautomator.intercept.operation.UiObjectAssertion

/**
 * Base class for intercepting the call chain from Kautomator to UiAutomator.
 *
 * Interceptors can be provided through [KautomatorConfigurator][com.kaspersky.components.kautomator.KautomatorConfigurator] runtime,
 * different [UiScreens][com.kaspersky.components.kautomator.component.screen.UiScreen] as well as [UiViews][com.kaspersky.components.kautomator.component.common.views.UiBaseView].
 *
 * Interceptors are stacked during the runtime for any UiAutomator_DSL-UiAutomator `check` and `perform` operations.
 * The stack ordering is following: UiView interceptor -> UiScreen interceptors -> UiAutomatorDsl interceptor.
 *
 * Any of the interceptors in the chain can break the chain call by setting `isOverride` to true
 * in [onCheck][Builder.onCheck], [onPerform][Builder.onPerform] or [onAll][Builder.onAll] interception
 * functions during the configuration. Doing this will not only prevent underlying
 * interceptors from being invoked, but prevents UiAutomator from executing the operation. In that case,
 * responsibility for actually making UiAutomator call lies on developer.
 *
 * For each operation the interceptor invocation cycle will be as follows:
 * ```
 * // For check operation
 * onAll?.invoke()
 * onCheck?.invoke()
 *
 * // For perform operation
 * onAll?.invoke()
 * onPerform?.invoke()
 * ```
 *
 * @see com.kaspersky.components.kautomator.KautomatorConfigurator
 * @see com.kaspersky.components.kautomator.component.screen.UiScreen
 * @see com.kaspersky.components.kautomator.component.common.views.UiBaseView
 */
class UiInterceptor <Interaction, Assertion, Action>(
    val onCheck: UiInterception<(Interaction, Assertion) -> Unit>?,
    val onPerform: UiInterception<(Interaction, Action) -> Unit>?,
    val onAll: UiInterception<(Interaction) -> Unit>?
) {
    /**
     * Builder class that is used to build a single instance of [UiInterceptor].
     *
     * @see UiInterceptor
     */
    class Builder<Interaction, Assertion, Action> {
        private var onCheck: UiInterception<(Interaction, Assertion) -> Unit>? = null
        private var onPerform: UiInterception<(Interaction, Action) -> Unit>? = null
        private var onAll: UiInterception<(Interaction) -> Unit>? = null

        /**
         * Sets the interceptor for the `check` operation for a given interaction.
         * If overridden, breaks the call chain of operation and transfers the responsibility
         * to invoke the UiAutomator on the developer.
         *
         * @param isOverride if `true` - breaks the call chain, false otherwise
         * @param interceptor lambda with intercepting logic
         */
        fun onCheck(isOverride: Boolean = false, interceptor: (Interaction, Assertion) -> Unit) {
            onCheck = UiInterception(isOverride, interceptor)
        }

        /**
         * Sets the interceptor for the `execute` operation for a given interaction.
         * If overridden, breaks the call chain of operation and transfers the responsibility
         * to invoke the UiAutomator on the developer.
         *
         * @param isOverride if `true` - breaks the call chain, false otherwise
         * @param interceptor lambda with intercepting logic
         */
        fun onPerform(isOverride: Boolean = false, interceptor: (Interaction, Action) -> Unit) {
            onPerform = UiInterception(isOverride, interceptor)
        }

        /**
         * Sets the interceptor for the `check` and `execute` operations for a given interaction.
         * If overridden, breaks the call chain of operation and transfers the responsibility
         * to invoke the UiAutomator on the developer.
         *
         * This interceptor is prioritized and is being invoked first for both operations.
         *
         * @param isOverride if `true` - breaks the call chain, false otherwise
         * @param interceptor lambda with intercepting logic
         */
        fun onAll(isOverride: Boolean = false, interceptor: (Interaction) -> Unit) {
            onAll = UiInterception(isOverride, interceptor)
        }

        internal fun build(): UiInterceptor<Interaction, Assertion, Action> = UiInterceptor(onCheck, onPerform, onAll)
    }

    /**
     * Configuration class that is used for building interceptors on the
     * [KautomatorConfigurator][com.kaspersky.components.kautomator.KautomatorConfigurator] runtime and [UiScreens][com.kaspersky.components.kautomator.component.screen.UiScreen] levels.
     *
     * @see com.kaspersky.components.kautomator.KautomatorConfigurator
     * @see com.kaspersky.components.kautomator.component.screen.UiScreen
     */
    class Configurator {
        private var uiObjectInterceptor: UiInterceptor<UiObjectInteraction, UiObjectAssertion, UiObjectAction>? = null
        private var uiDeviceInterceptor: UiInterceptor<UiDeviceInteraction, UiDeviceAssertion, UiDeviceAction>? = null

        /**
         * Setups the interceptor for `check` and `execute` operations happening through [UiObjectInteraction]
         *
         * @param builder Builder of interceptor for [UiObjectInteraction]
         */
        fun onUiInteraction(builder: Builder<UiObjectInteraction, UiObjectAssertion, UiObjectAction>.() -> Unit) {
            uiObjectInterceptor = Builder<UiObjectInteraction, UiObjectAssertion, UiObjectAction>().apply(builder).build()
        }

        /**
         * Setups the interceptor for `check` and `execute` operations happening through [UiDeviceInteraction]
         *
         * @param builder Builder of interceptor for [UiDeviceInteraction]
         */
        fun onUiDeviceInteraction(builder: Builder<UiDeviceInteraction, UiDeviceAssertion, UiDeviceAction>.() -> Unit) {
            uiDeviceInterceptor = Builder<UiDeviceInteraction, UiDeviceAssertion, UiDeviceAction>().apply(builder).build()
        }

        internal fun configure() = Configuration(uiObjectInterceptor, uiDeviceInterceptor)
    }

    data class Configuration(
        val uiUiObjectObjectInterceptor: UiInterceptor<UiObjectInteraction, UiObjectAssertion, UiObjectAction>?,
        val uiDeviceInterceptor: UiInterceptor<UiDeviceInteraction, UiDeviceAssertion, UiDeviceAction>?
    )
}
