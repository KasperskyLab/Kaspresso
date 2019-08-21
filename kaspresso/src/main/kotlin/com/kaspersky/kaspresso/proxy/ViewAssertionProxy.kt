package com.kaspersky.kaspresso.proxy

import android.view.View
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.ViewInteraction
import com.kaspersky.kaspresso.interceptors.view.ViewAssertionInterceptor
import com.kaspersky.kaspresso.interceptors.interactors.ViewInteractor

/**
 * A proxy-wrapper of [ViewAssertion] for interceptors calls.
 */
class ViewAssertionProxy(
    private val viewAssertion: ViewAssertion,
    override val interaction: ViewInteraction,
    private val interceptors: List<ViewAssertionInterceptor>,
    override val interactors: List<ViewInteractor>
) : ViewAssertion, InteractionProxy<ViewInteraction> {

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
        interact(view) { viewAssertion.check(view, noViewFoundException) }
    }
}