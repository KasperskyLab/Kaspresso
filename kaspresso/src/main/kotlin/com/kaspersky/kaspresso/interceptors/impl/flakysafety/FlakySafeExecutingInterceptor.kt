package com.kaspersky.kaspresso.interceptors.impl.flakysafety

import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.web.sugar.Web
import com.kaspersky.kaspresso.attempting.attempt
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
     * @return [ViewInteraction] as it is a result of [function] invocation.
     */
    override fun interceptAndExecute(
        function: () -> ViewInteraction
    ): ViewInteraction = attempt { function.invoke() }

    /**
     * Performs multiple attempts to execute web action or web assertion.
     *
     * @param function a function-wrapper of web action or web assertion to be invoked.
     * @return [Web.WebInteraction] as it is a result of [function] invocation.
     */
    override fun interceptAndExecuteWeb(
        function: () -> Web.WebInteraction<*>
    ): Web.WebInteraction<*> = attempt { function.invoke() }
}