package com.kaspersky.kaspresso.interceptors

import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.web.sugar.Web

/**
 * An interface for all executing interceptors, actually manages the execution of actions or assertions.
 */
interface ExecutingInterceptor {

    /**
     * Called to do some stuff and actually execute an action or an assertion.
     *
     * @param function a function-wrapper of an action or an assertion to be invoked.
     * @return [ViewInteraction] as it is a result of [function] invocation.
     */
    fun interceptAndExecute(function: () -> ViewInteraction): ViewInteraction

    /**
     * Called to do some stuff and actually execute web action or web assertion.
     *
     * @param function a function-wrapper of web action or web assertion to be invoked.
     * @return [Web.WebInteraction] as it is a result of [function] invocation.
     */
    fun interceptAndExecuteWeb(function: () -> Web.WebInteraction<*>): Web.WebInteraction<*>
}