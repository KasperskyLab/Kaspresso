package com.kaspersky.kaspresso.interceptors.impl.logging

import android.view.View
import android.support.test.espresso.NoMatchingViewException
import android.support.test.espresso.ViewAssertion
import android.support.test.espresso.assertion.describe
import com.kaspersky.kaspresso.interceptors.ViewAssertionInterceptor
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso.util.describe

class LoggingViewAssertionInterceptor(
    private val uiTestLogger: UiTestLogger
) : ViewAssertionInterceptor {

    override fun intercept(
        viewAssertion: ViewAssertion,
        view: View?,
        exception: NoMatchingViewException?
    ) {
        val text = exception?.viewMatcherDescription
            ?: "${viewAssertion.describe()} on ${view.describe()}"

        uiTestLogger.i(text)
    }
}