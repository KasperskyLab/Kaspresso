package com.kaspersky.kaspresso.proxy

import android.view.View
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import com.kaspersky.kaspresso.interceptors.watcher.view.ViewAssertionWatcherInterceptor

/**
 * A proxy-wrapper of [ViewAssertion] for watcherInterceptors calls.
 */
class ViewAssertionProxy(
    private val viewAssertion: ViewAssertion,
    private val watcherInterceptors: List<ViewAssertionWatcherInterceptor>
) : ViewAssertion {

    /**
     * Calls watcherInterceptors before [ViewAssertion.check] on wrapped [viewAssertion] is called.
     *
     * @param view the view, if one was found during the view to_kakao or null if it was not (which
     *     may be an acceptable option for an assertion).
     * @param noViewFoundException an exception detailing why the view could not be found or null if
     *     the view was found.
     */
    override fun check(view: View?, noViewFoundException: NoMatchingViewException?) {
        watcherInterceptors.forEach { it.intercept(viewAssertion, view, noViewFoundException) }
        viewAssertion.check(view, noViewFoundException)
    }
}