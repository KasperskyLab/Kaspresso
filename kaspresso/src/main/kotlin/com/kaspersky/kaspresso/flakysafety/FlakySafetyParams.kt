package com.kaspersky.kaspresso.flakysafety

import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.PerformException
import com.kaspersky.kaspresso.internal.exceptions.ThrowableWithInteraction

/**
 * The class that holds all the necessary for [FlakySafetyProvider] parameters.
 */
class FlakySafetyParams(
    timeoutMs: Long = DEFAULT_TIMEOUT_MS,
    intervalMs: Long = DEFAULT_INTERVAL_MS,

    /**
     * The set of exceptions, if caught, the [FlakySafetyProvider] will continue to attempt.
     */
    var allowedExceptions: MutableSet<Class<out Throwable>> =
        mutableSetOf(
            PerformException::class.java,
            NoMatchingViewException::class.java,
            AssertionError::class.java,
            ThrowableWithInteraction::class.java
        )
) {
    private companion object {
        private const val DEFAULT_TIMEOUT_MS: Long = 2_000L
        private const val DEFAULT_INTERVAL_MS: Long = 500L
    }

    /**
     * The timeout during which attempts will be made by the [FlakySafetyProvider].
     */
    var timeoutMs: Long = timeoutMs
        set(value) {
            if (intervalMs >= value) throw IllegalArgumentException("An interval of attempts is shorter than timeout.")
            field = value
        }

    /**
     * The interval at which attempts will be made by the [FlakySafetyProvider].
     */
    var intervalMs: Long = intervalMs
        set(value) {
            if (value >= timeoutMs) throw IllegalArgumentException("An interval of attempts is shorter than timeout.")
            field = value
        }
}