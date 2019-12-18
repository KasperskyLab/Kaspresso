package com.kaspersky.components.uiautomator_dsl.intercept

import androidx.test.uiautomator.UiObject2
import com.kaspersky.components.uiautomator_dsl.proxy.UiAction
import com.kaspersky.components.uiautomator_dsl.proxy.UiAssert

class Interceptor <INTERACTION, ASSERTION, ACTION>(
    val onCheck: Interception<(INTERACTION, ASSERTION) -> Unit>?,
    val onPerform: Interception<(INTERACTION, ACTION) -> Unit>?,
    val onAll: Interception<(INTERACTION) -> Unit>?
) {
    /**
     * Builder class that is used to build a single instance of [Interceptor].
     *
     * @see Interceptor
     */
    class Builder<INTERACTION, ASSERTION, ACTION> {
        private var onCheck: Interception<(INTERACTION, ASSERTION) -> Unit>? = null
        private var onPerform: Interception<(INTERACTION, ACTION) -> Unit>? = null
        private var onAll: Interception<(INTERACTION) -> Unit>? = null

        /**
         * Sets the interceptor for the `check` operation for a given interaction.
         * If overridden, breaks the call chain of operation and transfers the responsibility
         * to invoke the Espresso on the developer.
         *
         * @param isOverride if `true` - breaks the call chain, false otherwise
         * @param interceptor lambda with intercepting logic
         */
        fun onCheck(isOverride: Boolean = false, interceptor: (INTERACTION, ASSERTION) -> Unit) {
            onCheck = Interception(isOverride, interceptor)
        }

        /**
         * Sets the interceptor for the `perform` operation for a given interaction.
         * If overridden, breaks the call chain of operation and transfers the responsibility
         * to invoke the Espresso on the developer.
         *
         * @param isOverride if `true` - breaks the call chain, false otherwise
         * @param interceptor lambda with intercepting logic
         */
        fun onPerform(isOverride: Boolean = false, interceptor: (INTERACTION, ACTION) -> Unit) {
            onPerform = Interception(isOverride, interceptor)
        }

        /**
         * Sets the interceptor for the `check` and `perform` operations for a given interaction.
         * If overridden, breaks the call chain of operation and transfers the responsibility
         * to invoke the Espresso on the developer.
         *
         * This interceptor is prioritized and is being invoked first for both operations.
         *
         * @param isOverride if `true` - breaks the call chain, false otherwise
         * @param interceptor lambda with intercepting logic
         */
        fun onAll(isOverride: Boolean = false, interceptor: (INTERACTION) -> Unit) {
            onAll = Interception(isOverride, interceptor)
        }

        internal fun build(): Interceptor<INTERACTION, ASSERTION, ACTION> = Interceptor(onCheck, onPerform, onAll)
    }

    /**
     * Configuration class that is used for building interceptors on the
     * [Kakao][com.agoda.kakao.Kakao] runtime and [Screen][com.agoda.kakao.screen.Screen] levels.
     *
     * @see com.agoda.kakao.Kakao
     * @see com.agoda.kakao.screen.Screen
     */
    class Configurator {
        private var uiInterceptor: Interceptor<UiObject2?, UiAssert, UiAction>? = null

        /**
         * Setups the interceptor for `check` and `perform` operations happening through [ViewInteraction]
         *
         * @param builder Builder of interceptor for [ViewInteraction]
         */
        fun onUiInteraction(builder: Builder<UiObject2?, UiAssert, UiAction>.() -> Unit) {
            uiInterceptor = Builder<UiObject2?, UiAssert, UiAction>().apply(builder).build()
        }

        internal fun configure() = Configuration(
            uiInterceptor
        )
    }

    data class Configuration(
        val uiInterceptor: Interceptor<UiObject2?, UiAssert, UiAction>?
    )
}