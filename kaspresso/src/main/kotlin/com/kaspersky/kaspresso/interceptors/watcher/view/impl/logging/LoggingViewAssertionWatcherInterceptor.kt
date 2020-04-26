package com.kaspersky.kaspresso.interceptors.watcher.view.impl.logging

import android.view.View
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.assertion.describe
import com.kaspersky.kaspresso.interceptors.watcher.view.ViewAssertionWatcherInterceptor
import com.kaspersky.kaspresso.internal.extensions.other.describe
import com.kaspersky.kaspresso.logger.UiTestLogger

/**
 * The implementation of [ViewAssertionWatcherInterceptor] that logs info about [ViewAssertion].
 */
class LoggingViewAssertionWatcherInterceptor(
    private val logger: UiTestLogger
) : ViewAssertionWatcherInterceptor {

    /**
     * Writes info to [logger].
     *
     * @param viewAssertion responsible for performing assertions on a [View] element.
     * @param view an Android [View], on which [viewAssertion] is performed.
     * @param exception indicates that a given matcher did not match any elements in the view hierarchy.
     */
    override fun intercept(viewAssertion: ViewAssertion, view: View?, exception: NoMatchingViewException?) {
        logger.i(exception?.viewMatcherDescription ?: "${viewAssertion.describe()} on ${view.describe()}")
    }
}
