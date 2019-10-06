package com.kaspersky.kaspresso.interceptors.watcher.view.impl.caching

import android.view.View
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.assertion.getViewMatcher
import com.kaspersky.kaspresso.failure.describe.AssertionCache
import com.kaspersky.kaspresso.interceptors.watcher.view.ViewAssertionWatcherInterceptor

/**
 * The implementation of [ViewAssertionWatcherInterceptor] that obtains info about executing [ViewAssertion] for
 * failure logging.
 */
class CachingViewAssertionWatcherInterceptor(
    private val assertionCache: AssertionCache
) : ViewAssertionWatcherInterceptor {

    /**
     * Stores [viewAssertion] and it's [org.hamcrest.Matcher] to [AssertionCache] for
     * [com.kaspersky.kaspresso.failure.FailureLoggingProvider] usage.
     *
     * @param viewAssertion responsible for performing assertions on a [View] element.
     * @param view an Android [View], on which [viewAssertion] is performed.
     * @param exception indicates that a given matcher did not match any elements in the view hierarchy.
     */
    override fun intercept(viewAssertion: ViewAssertion, view: View?, exception: NoMatchingViewException?) {
        assertionCache.cachedViewAssertion = viewAssertion.javaClass
        assertionCache.cachedViewMatcher = viewAssertion.getViewMatcher()
    }
}