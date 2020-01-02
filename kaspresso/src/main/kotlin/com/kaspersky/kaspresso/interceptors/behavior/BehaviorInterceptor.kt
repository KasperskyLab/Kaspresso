package com.kaspersky.kaspresso.interceptors.behavior

/**
 * The interface for all interceptors that change the default interaction. Often it wraps the interaction calls.
 */
interface BehaviorInterceptor<INTERACTION> {

    /**
     * Called to do some stuff and actually perform an interaction with element.
     *
     * @param action a function-wrapper of an action or an assertion to be invoked.
     */
    fun <T> intercept(interaction: INTERACTION, action: () -> T): T
}