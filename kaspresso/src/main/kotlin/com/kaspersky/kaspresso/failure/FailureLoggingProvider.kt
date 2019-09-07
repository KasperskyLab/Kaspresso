package com.kaspersky.kaspresso.failure

import android.view.View
import org.hamcrest.Matcher

interface FailureLoggingProvider {

    fun <T> withLoggingOnFailure(action: () -> T): T

    fun logStackTrace(error: Throwable)

    @Throws(Throwable::class)
    fun logDescriptionAndThrow(error: Throwable?, viewMatcher: Matcher<View>?)
}

fun <T> FailureLoggingProvider?.withLoggingOnFailureIfNotNull(action: () -> T): T =
    if (this != null) withLoggingOnFailure(action) else action.invoke()