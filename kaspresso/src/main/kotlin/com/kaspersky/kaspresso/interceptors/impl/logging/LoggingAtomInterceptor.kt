package com.kaspersky.kaspresso.interceptors.impl.logging

import android.support.test.espresso.web.model.Evaluation
import com.kaspersky.kaspresso.interceptors.AtomInterceptor
import com.kaspersky.kaspresso.logger.UiTestLogger

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
    override fun intercept(evaluation: Evaluation?) {
        logger.i("Web action: ${evaluation?.message ?: "null"}")
    }
}