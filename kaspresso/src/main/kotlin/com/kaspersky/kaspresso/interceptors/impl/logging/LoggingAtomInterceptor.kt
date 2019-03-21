package com.kaspersky.kaspresso.interceptors.impl.logging

import android.support.test.espresso.web.model.Evaluation
import com.kaspersky.kaspresso.interceptors.AtomInterceptor
import com.kaspersky.kaspresso.logger.UiTestLogger

class LoggingAtomInterceptor(
    private val logger: UiTestLogger
) : AtomInterceptor {

    override fun intercept(evaluation: Evaluation?) {
        logger.i("Web action: ${evaluation?.message ?: "null"}")
    }
}