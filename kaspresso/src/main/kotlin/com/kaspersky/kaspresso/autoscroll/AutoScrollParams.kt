package com.kaspersky.kaspresso.autoscroll

import androidx.test.espresso.PerformException
import io.reactivex.exceptions.CompositeException
import junit.framework.AssertionFailedError

class AutoScrollParams(
    val allowedExceptions: MutableSet<Class<out Throwable>> =
        mutableSetOf(
            PerformException::class.java,
            AssertionFailedError::class.java
        )
) {
    fun isExceptionAllowed(error: Throwable): Boolean {
        return if (error is CompositeException) {
            error.exceptions.find { composedException: Throwable ->
                allowedExceptions.find { it.isAssignableFrom(composedException.javaClass) } != null
            } != null
        } else {
            allowedExceptions.find { it.isAssignableFrom(error.javaClass) } != null
        }
    }
}