package com.kaspersky.kaspresso.failure

import android.view.View
import org.hamcrest.Matcher

/**
 * An interface to provide the logging failures functionality.
 */
interface FailureLoggingProvider {

    /**
     * Invokes the given [action] and logs if it fails.
     *
     * @param action the action to invoke.
     *
     * @return the result of the [action] invocation.
     */
    fun <T> withLoggingOnFailure(action: () -> T): T

    /**
     * Logs the [error]'s stacktrace.
     *
     * @param error the error to get stacktrace from.
     */
    fun logStackTrace(error: Throwable)

    /**
     * Logs the [error] description got by [viewMatcher] and throws the [error].
     *
     * @param error the error to be described.
     * @param viewMatcher the view matcher to get description from.
     */
    fun logDescriptionAndThrow(error: Throwable?, viewMatcher: Matcher<View>?)
}