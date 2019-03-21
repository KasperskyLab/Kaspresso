package com.kaspersky.kaspresso.interceptors.impl.logging

import android.view.View
import android.support.test.espresso.NoMatchingViewException
import android.support.test.espresso.ViewAssertion
import android.support.test.espresso.assertion.describe
import com.kaspersky.kaspresso.interceptors.ViewAssertionInterceptor
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso.util.describe

/**
 * An implementation of [ViewAssertionInterceptor] that logs info about [ViewAssertion].
 */
class LoggingViewAssertionInterceptor(
    private val logger: UiTestLogger
) : ViewAssertionInterceptor {

    /**
     * Writes info to [logger].
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

        logger.i(text)
    }
}