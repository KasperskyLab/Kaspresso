package com.kaspersky.components.uiautomator_dsl.intercepting.delegate

import com.kaspersky.components.uiautomator_dsl.intercepting.intercept.UiInterceptor

interface UiDelegate<INTERACTION, ASSERTION, ACTION> {
    val interaction: INTERACTION
    var interceptor: UiInterceptor<INTERACTION, ASSERTION, ACTION>?

    fun screenInterceptors(): Iterable<UiInterceptor<INTERACTION, ASSERTION, ACTION>>
    fun kakaoInterceptor(): UiInterceptor<INTERACTION, ASSERTION, ACTION>?

    /**
     * Runs the interceptors available for the given delegate during the `check` operation.
     *
     * @return `true` if the call chain has been interrupted and there is no need to do Espresso call,
     *         false otherwise.
     */
    fun interceptCheck(assertion: ASSERTION): Boolean {
        fun intercept(uiInterceptor: UiInterceptor<INTERACTION, ASSERTION, ACTION>): Boolean {
            return interceptOnAll(uiInterceptor) || interceptOnCheck(uiInterceptor, assertion)
        }

        return interceptor?.let { intercept(it) } ?: false ||
                screenInterceptors().any { intercept(it) } ||
                kakaoInterceptor()?.let { intercept(it) } ?: false
    }

    /**
     * Runs the interceptors available for the given delegate during the `execute` operation.
     *
     * @return `true` if the call chain has been interrupted and there is no need to do Espresso call,
     *         false otherwise.
     */
    fun interceptPerform(action: ACTION): Boolean {
        fun intercept(uiInterceptor: UiInterceptor<INTERACTION, ASSERTION, ACTION>): Boolean {
            return interceptOnAll(uiInterceptor) || interceptOnPerform(uiInterceptor, action)
        }

        return interceptor?.let { intercept(it) } ?: false ||
                screenInterceptors().any { intercept(it) } ||
                kakaoInterceptor()?.let { intercept(it) } ?: false
    }

    private fun interceptOnAll(uiInterceptor: UiInterceptor<INTERACTION, ASSERTION, ACTION>): Boolean {
        return uiInterceptor.onAll?.let { (isOverride, interception) ->
            interception(interaction)
            isOverride
        } ?: false
    }

    private fun interceptOnCheck(uiInterceptor: UiInterceptor<INTERACTION, ASSERTION, ACTION>, assertion: ASSERTION): Boolean {
        return uiInterceptor.onCheck?.let { (isOverride, interception) ->
            interception(interaction, assertion)
            isOverride
        } ?: false
    }

    private fun interceptOnPerform(uiInterceptor: UiInterceptor<INTERACTION, ASSERTION, ACTION>, action: ACTION): Boolean {
        return uiInterceptor.onPerform?.let { (isOverride, interception) ->
            interception(interaction, action)
            isOverride
        } ?: false
    }
}