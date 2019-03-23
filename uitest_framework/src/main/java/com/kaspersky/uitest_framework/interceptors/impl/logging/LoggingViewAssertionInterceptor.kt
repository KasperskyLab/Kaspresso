package com.kaspersky.uitest_framework.interceptors.impl.logging

import android.view.View
import android.support.test.espresso.NoMatchingViewException
import android.support.test.espresso.ViewAssertion
import android.support.test.espresso.assertion.describe
import com.kaspersky.uitest_framework.interceptors.ViewAssertionInterceptor
import com.kaspersky.uitest_framework.logger.UiTestLogger
import com.kaspersky.uitest_framework.util.describe

/**
 * An implementation of [ViewAssertionInterceptor] that logs info about [ViewAssertion].
 */
class LoggingViewAssertionInterceptor(
    private val uiTestLogger: UiTestLogger
) : ViewAssertionInterceptor {

    /**
     * Writes info to [uiTestLogger].
     *
     * @param viewAssertion responsible for performing assertions on a [View] element.
     * @param view an Android [View], on which [viewAssertion] is performed.
     * @param exception indicates that a given matcher did not match any elements in the view hierarchy.
     */
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