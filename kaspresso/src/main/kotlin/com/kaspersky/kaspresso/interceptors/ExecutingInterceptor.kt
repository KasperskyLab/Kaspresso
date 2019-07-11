package com.kaspersky.kaspresso.interceptors

import android.support.test.espresso.ViewInteraction

/**
 * An interface for all executing interceptors, actually manages the execution of actions or assertions.
 */
interface ExecutingInterceptor {

    /**
     * Called to do some stuff and actually execute an action or an assertion.
     *
     * @param function a function-wrapper of an action or an assertion to be invoked.
     */
    fun <EspressoInteraction> interceptAndExecute(function: () -> EspressoInteraction)
}