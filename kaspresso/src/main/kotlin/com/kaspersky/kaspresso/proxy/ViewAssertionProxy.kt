package com.kaspersky.kaspresso.proxy

import android.view.View
import android.support.test.espresso.NoMatchingViewException
import android.support.test.espresso.ViewAssertion
import com.kaspersky.kaspresso.interceptors.ViewAssertionInterceptor

/**
 * A proxy-wrapper of [ViewAssertion] for interceptors calls.
 */
class ViewAssertionProxy(
    private val viewAssertion: ViewAssertion,
    private val interceptors: List<ViewAssertionInterceptor>
) : ViewAssertion {

    /**
     * Calls interceptors before [ViewAssertion.check] on wrapped [viewAssertion] is called.
     *
     * @param view the view, if one was found during the view interaction or null if it was not (which
     *     may be an acceptable option for an assertion).
     * @param noViewFoundException an exception detailing why the view could not be found or null if
     *     the view was found.
     */
    override fun check(view: View?, noViewFoundException: NoMatchingViewException?) {
        interceptors.forEach { it.intercept(viewAssertion, view, noViewFoundException) }
        viewAssertion.check(view, noViewFoundException)
    }
}