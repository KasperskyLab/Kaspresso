package com.kaspersky.kaspresso.systemsafety

import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.PerformException
import io.reactivex.exceptions.CompositeException

class SystemDialogSafetyParams(
    val allowedExceptions: MutableSet<Class<out Throwable>> =
        mutableSetOf(
            PerformException::class.java,
            NoMatchingViewException::class.java,
            AssertionError::class.java
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