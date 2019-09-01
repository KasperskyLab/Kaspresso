package com.kaspersky.kaspresso.interceptors.behavior

/**
 * An interface for all executing interceptors, actually manages the execution of actions or assertions.
 */
interface BehaviorInteractor<Interaction> {

    /**
     * Called to do some stuff and actually interact an action or an assertion.
     *
     * @param action a function-wrapper of an action or an assertion to be invoked.
     */
    fun <R> interact(interaction: Interaction, action: () -> R): R
}