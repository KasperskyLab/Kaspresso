package com.kaspersky.kaspresso.interceptors.view.impl.logging

import android.support.test.espresso.web.model.Evaluation
import com.kaspersky.kaspresso.interceptors.view.AtomInterceptor
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso.proxy.AtomProxy

/**
 * An implementation of [AtomInterceptor] that logs info about web action.
 */
class LoggingAtomInterceptor(
    private val logger: UiTestLogger
) : AtomInterceptor {

    /**
     * Writes info to [logger].
     *
     * @param evaluation represents the results of a Javascript execution.
     */
    override fun intercept(atomProxy: AtomProxy<*>, evaluation: Evaluation?) {

        var message = ""
        var status = ""
        var result = ""

        evaluation?.let { eval: Evaluation ->
            if (eval.hasMessage()) message = "with message=\"${eval.message}\""
            status = "with status=\"${eval.status}\""
            eval.value?.let { result = "with result=\"$it\"" }
        }

        logger.i("${atomProxy.describe()} ${message}completed $status $result".trimEnd())
    }
}