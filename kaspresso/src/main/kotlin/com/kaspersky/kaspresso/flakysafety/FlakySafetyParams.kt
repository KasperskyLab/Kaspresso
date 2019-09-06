package com.kaspersky.kaspresso.flakysafety

import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.PerformException

class FlakySafetyParams(
    timeoutMs: Long = DEFAULT_TIMEOUT_MS,
    intervalMs: Long = DEFAULT_INTERVAL_MS,
    var allowedExceptions: MutableSet<Class<out Throwable>> =
        mutableSetOf(
            PerformException::class.java,
            NoMatchingViewException::class.java,
            AssertionError::class.java
        )
) {
    companion object {
        private const val DEFAULT_TIMEOUT_MS: Long = 2_000L
        private const val DEFAULT_INTERVAL_MS: Long = 500L
    }

    var timeoutMs: Long = timeoutMs
        set(value) {
            if (intervalMs >= value) throw IllegalArgumentException("An interval of attempts is shorter than timeout.")
            field = value
        }

    var intervalMs: Long = intervalMs
        set(value) {
            if (value >= timeoutMs) throw IllegalArgumentException("An interval of attempts is shorter than timeout.")
            field = value
        }
}