package com.kaspersky.kaspresso.interceptors.behaviorkautomator

/**
 * The interface for all interceptors that change the default interaction in Kautomator. Often it wraps the interaction calls.
 */
interface KautomatorBehaviorInterceptor<Interaction, Assertion, Action> {

    /**
     * Called to do some stuff and actually check an interaction with element.
     *
     * @param activity a function-wrapper of an action or an assertion to be invoked.
     */
    fun <T> interceptCheck(interaction: Interaction, assertion: Assertion, activity: () -> T): T

    /**
     * Called to do some stuff and actually perform an interaction with element.
     *
     * @param activity a function-wrapper of an action or an assertion to be invoked.
     */
    fun <T> interceptPerform(interaction: Interaction, action: Action, activity: () -> T): T
}