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
        val message: String =
            if ((evaluation != null) and evaluation!!.hasMessage()) {
                "with message=\"${evaluation.message}\""
            } else {
                ""
            }

        logger.i("${atomProxy.describe()} $message".trimEnd())
    }
}