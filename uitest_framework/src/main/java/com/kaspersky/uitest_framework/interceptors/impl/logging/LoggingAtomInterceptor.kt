package com.kaspersky.uitest_framework.interceptors.impl.logging

import android.support.test.espresso.web.model.Evaluation
import com.kaspersky.uitest_framework.interceptors.AtomInterceptor
import com.kaspersky.uitest_framework.logger.UiTestLogger

/**
 * An implementation of [AtomInterceptor] that logs info about web action.
 */
class LoggingAtomInterceptor(
    private val uiTestLogger: UiTestLogger
) : AtomInterceptor {

    /**
     * Writes info to [uiTestLogger].
     *
     * @param evaluation represents the results of a Javascript execution.
     */
    override fun intercept(evaluation: Evaluation?) {
        uiTestLogger.i("Web action: ${evaluation?.message ?: "null"}")
    }
}