package com.kaspersky.kaspresso.interceptors.behavior

/**
 * The interface for all interceptors that change the default interaction in Kakao=>Espresso. Often it wraps the interaction calls.
 */
interface BehaviorInterceptor<Interaction> {

    /**
     * Called to do some stuff and actually perform an interaction with element.
     *
     * @param action a function-wrapper of an action or an assertion to be invoked.
     */
    fun <T> intercept(interaction: Interaction, action: () -> T): T
}