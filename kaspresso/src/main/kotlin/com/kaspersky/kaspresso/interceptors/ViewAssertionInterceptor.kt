package com.kaspersky.kaspresso.interceptors

import android.view.View
import android.support.test.espresso.NoMatchingViewException
import android.support.test.espresso.ViewAssertion

/**
 * An interface for all view action interceptors, used in [com.kaspersky.kaspresso.proxy.ViewAssertionProxy].
 */
interface ViewAssertionInterceptor {

    /**
     * Called to do some stuff before [android.support.test.espresso.ViewAssertion.check] is actually called.
     *
     * @param viewAssertion responsible for performing assertions on a [View] element.
     * @param view an Android [View], on which [viewAssertion] is performed.
     * @param exception indicates that a given matcher did not match any elements in the view hierarchy.
     */
    fun intercept(
        viewAssertion: ViewAssertion,
        view: View?,
        exception: NoMatchingViewException?
    )
}