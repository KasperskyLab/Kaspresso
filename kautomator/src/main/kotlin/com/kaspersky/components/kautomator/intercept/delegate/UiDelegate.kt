package com.kaspersky.components.kautomator.intercept.delegate

import com.kaspersky.components.kautomator.intercept.base.UiInterceptor

/**
 * Base delegate interface for Kautomator.
 *
 * Provides functionality of aggregating interceptors and invoking them on `check`
 * and `perform` invocations.
 *
 * @see UiInterceptor
 */
interface UiDelegate<Interaction, Assertion, Action> {
    val interaction: Interaction
    var interceptor: UiInterceptor<Interaction, Assertion, Action>?

    fun screenInterceptors(): Iterable<UiInterceptor<Interaction, Assertion, Action>>
    fun globalInterceptor(): UiInterceptor<Interaction, Assertion, Action>?

    /**
     * Runs the interceptors available for the given delegate during the `check` operation.
     *
     * @return `true` if the call chain has been interrupted and there is no need to do UiAutomator call,
     *         false otherwise.
     */
    fun interceptCheck(assertion: Assertion): Boolean {
        fun intercept(uiInterceptor: UiInterceptor<Interaction, Assertion, Action>): Boolean {
            return interceptOnAll(uiInterceptor) || interceptOnCheck(uiInterceptor, assertion)
        }

        return interceptor?.let { intercept(it) } ?: false ||
                screenInterceptors().any { intercept(it) } ||
                globalInterceptor()?.let { intercept(it) } ?: false
    }

    /**
     * Runs the interceptors available for the given delegate during the `execute` operation.
     *
     * @return `true` if the call chain has been interrupted and there is no need to do UiAutomator call,
     *         false otherwise.
     */
    fun interceptPerform(action: Action): Boolean {
        fun intercept(uiInterceptor: UiInterceptor<Interaction, Assertion, Action>): Boolean {
            return interceptOnAll(uiInterceptor) || interceptOnPerform(uiInterceptor, action)
        }

        return interceptor?.let { intercept(it) } ?: false ||
                screenInterceptors().any { intercept(it) } ||
                globalInterceptor()?.let { intercept(it) } ?: false
    }

    private fun interceptOnAll(uiInterceptor: UiInterceptor<Interaction, Assertion, Action>): Boolean {
        return uiInterceptor.onAll?.let { (isOverride, interception) ->
            interception(interaction)
            isOverride
        } ?: false
    }

    private fun interceptOnCheck(uiInterceptor: UiInterceptor<Interaction, Assertion, Action>, assertion: Assertion): Boolean {
        return uiInterceptor.onCheck?.let { (isOverride, interception) ->
            interception(interaction, assertion)
            isOverride
        } ?: false
    }

    private fun interceptOnPerform(uiInterceptor: UiInterceptor<Interaction, Assertion, Action>, action: Action): Boolean {
        return uiInterceptor.onPerform?.let { (isOverride, interception) ->
            interception(interaction, action)
            isOverride
        } ?: false
    }
}