package com.kaspersky.components.composesupport.interceptors.behavior

interface ComposeBehaviorInterceptor<Interaction, Assertion, Action> {

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
