package com.kaspersky.kaspresso.interceptors.view.impl.flakysafety

import com.kaspersky.kaspresso.flakysafety.attempt
import com.kaspersky.kaspresso.interceptors.view.ExecutingInterceptor

/**
 * An implementation of [ExecutingInterceptor] that performs multiple attempts to execute an action or an assertion to
 * provide flaky safety.
 */
class FlakySafeExecutingInterceptor : ExecutingInterceptor {

    /**
     * Performs multiple attempts to execute an action or an assertion.
     *
     * @param executable a function-wrapper of an action or an assertion to be invoked.
     */
    override fun <Interaction> interceptAndExecute(executable: () -> Interaction) = attempt(action = executable)
}