package com.kaspersky.uitest_framework.interceptors.logging

import android.support.test.espresso.NoMatchingViewException
import android.support.test.espresso.ViewAssertion
import android.support.test.espresso.assertion.describe
import android.view.View
import com.kaspersky.uitest_framework.interceptors.ViewAssertionInterceptor
import com.kaspersky.uitest_framework.logger.UiTestLogger
import com.kaspersky.uitest_framework.util.describe

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