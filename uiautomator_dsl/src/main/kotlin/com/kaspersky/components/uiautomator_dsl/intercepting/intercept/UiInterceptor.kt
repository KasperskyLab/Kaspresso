package com.kaspersky.components.uiautomator_dsl.intercepting.intercept

import com.kaspersky.components.uiautomator_dsl.intercepting.action_and_assertion.*
import com.kaspersky.components.uiautomator_dsl.intercepting.interaction.UiDeviceInteraction
import com.kaspersky.components.uiautomator_dsl.intercepting.interaction.UiInteraction

class UiInterceptor <INTERACTION, ASSERTION, ACTION>(
    val onCheck: UiInterception<(INTERACTION, ASSERTION) -> Unit>?,
    val onPerform: UiInterception<(INTERACTION, ACTION) -> Unit>?,
    val onAll: UiInterception<(INTERACTION) -> Unit>?
) {
    /**
     * Builder class that is used to build a single instance of [UiInterceptor].
     *
     * @see UiInterceptor
     */
    class Builder<INTERACTION, ASSERTION, ACTION> {
        private var onCheck: UiInterception<(INTERACTION, ASSERTION) -> Unit>? = null
        private var onPerform: UiInterception<(INTERACTION, ACTION) -> Unit>? = null
        private var onAll: UiInterception<(INTERACTION) -> Unit>? = null

        /**
         * Sets the interceptor for the `check` operation for a given objectInteraction.
         * If overridden, breaks the call chain of operation and transfers the responsibility
         * to invoke the Espresso on the developer.
         *
         * @param isOverride if `true` - breaks the call chain, false otherwise
         * @param interceptor lambda with intercepting logic
         */
        fun onCheck(isOverride: Boolean = false, interceptor: (INTERACTION, ASSERTION) -> Unit) {
            onCheck = UiInterception(isOverride, interceptor)
        }

        /**
         * Sets the interceptor for the `execute` operation for a given objectInteraction.
         * If overridden, breaks the call chain of operation and transfers the responsibility
         * to invoke the Espresso on the developer.
         *
         * @param isOverride if `true` - breaks the call chain, false otherwise
         * @param interceptor lambda with intercepting logic
         */
        fun onPerform(isOverride: Boolean = false, interceptor: (INTERACTION, ACTION) -> Unit) {
            onPerform = UiInterception(isOverride, interceptor)
        }

        /**
         * Sets the interceptor for the `check` and `execute` operations for a given objectInteraction.
         * If overridden, breaks the call chain of operation and transfers the responsibility
         * to invoke the Espresso on the developer.
         *
         * This interceptor is prioritized and is being invoked first for both operations.
         *
         * @param isOverride if `true` - breaks the call chain, false otherwise
         * @param interceptor lambda with intercepting logic
         */
        fun onAll(isOverride: Boolean = false, interceptor: (INTERACTION) -> Unit) {
            onAll = UiInterception(isOverride, interceptor)
        }

        internal fun build(): UiInterceptor<INTERACTION, ASSERTION, ACTION> = UiInterceptor(onCheck, onPerform, onAll)
    }

    /**
     * Configuration class that is used for building interceptors on the
     * [Kakao][com.agoda.kakao.Kakao] runtime and [Screen][com.agoda.kakao.screen.Screen] levels.
     *
     * @see com.agoda.kakao.Kakao
     * @see com.agoda.kakao.screen.Screen
     */
    class Configurator {
        private var uiInterceptor: UiInterceptor<UiInteraction, UiAssertion, UiAction>? = null
        private var uiDeviceInterceptor: UiInterceptor<UiDeviceInteraction, UiDeviceAssertion, UiDeviceAction>? = null

        /**
         * Setups the interceptor for `check` and `execute` operations happening through [ViewInteraction]
         *
         * @param builder Builder of interceptor for [ViewInteraction]
         */
        fun onUiInteraction(builder: Builder<UiInteraction, UiAssertion, UiAction>.() -> Unit) {
            uiInterceptor = Builder<UiInteraction, UiAssertion, UiAction>().apply(builder).build()
        }

        fun onUiDeviceInteraction(builder: Builder<UiDeviceInteraction, UiDeviceAssertion, UiDeviceAction>.() -> Unit) {
            uiDeviceInterceptor = Builder<UiDeviceInteraction, UiDeviceAssertion, UiDeviceAction>().apply(builder).build()
        }

        internal fun configure() = Configuration(uiInterceptor, uiDeviceInterceptor)
    }

    data class Configuration(
        val uiUiInterceptor: UiInterceptor<UiInteraction, UiAssertion, UiAction>?,
        val uiDeviceInterceptor: UiInterceptor<UiDeviceInteraction, UiDeviceAssertion, UiDeviceAction>?
    )
}