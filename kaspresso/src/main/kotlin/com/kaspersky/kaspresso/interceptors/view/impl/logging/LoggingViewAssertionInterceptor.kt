package com.kaspersky.kaspresso.interceptors.view.impl.logging

import android.support.test.espresso.NoMatchingViewException
import android.support.test.espresso.ViewAssertion
import android.support.test.espresso.assertion.describe
import android.view.View
import com.kaspersky.kaspresso.extensions.other.describe
import com.kaspersky.kaspresso.interceptors.view.ViewAssertionInterceptor
import com.kaspersky.kaspresso.logger.UiTestLogger

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