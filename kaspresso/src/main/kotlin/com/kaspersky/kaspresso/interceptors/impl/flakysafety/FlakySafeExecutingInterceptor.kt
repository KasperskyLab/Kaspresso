package com.kaspersky.kaspresso.interceptors.impl.flakysafety

import com.kaspersky.kaspresso.flakysafety.attempt
import com.kaspersky.kaspresso.interceptors.ExecutingInterceptor

/**
 * An implementation of [ExecutingInterceptor] that performs multiple attempts to execute an action or an assertion to
 * provide flaky safety.
 */
class FlakySafeExecutingInterceptor : ExecutingInterceptor {

    /**
     * Performs multiple attempts to execute an action or an assertion.
     *
     * @param function a function-wrapper of an action or an assertion to be invoked.
     */
    override fun <EspressoInteraction> interceptAndExecute(
        function: () -> EspressoInteraction
    ) {
        attempt { function.invoke() }
    }

}