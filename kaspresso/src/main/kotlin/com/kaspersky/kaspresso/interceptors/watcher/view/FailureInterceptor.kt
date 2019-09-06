package com.kaspersky.kaspresso.interceptors.watcher.view

import android.view.View
import org.hamcrest.Matcher

/**
 * An interface for all failure interceptors, called on failures.
 */
interface FailureInterceptor {

    /**
     * Reference to this method is provided as [androidx.test.espresso.FailureHandler] and is called on failures,
     * to do some stuff and throw an exception further.
     *
     * @param error the reason of failure.
     * @param viewMatcher a matcher, which corresponded to the view on which the error occurred.
     */
    fun interceptAndThrow(error: Throwable, viewMatcher: Matcher<View>)
}