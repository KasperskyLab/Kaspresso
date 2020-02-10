package com.kaspersky.kaspresso.params

import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.PerformException
import androidx.test.uiautomator.StaleObjectException
import com.kaspersky.components.kautomator.intercept.exception.UnfoundedUiObjectException
import junit.framework.AssertionFailedError

/**
 * The class that holds all the necessary for [com.kaspersky.kaspresso.flakysafety.FlakySafetyProviderSimpleImpl] parameters.
 */
class FlakySafetyParams private constructor(
    timeoutMs: Long,
    intervalMs: Long,

    /**
     * The set of exceptions, if caught, the [com.kaspersky.kaspresso.flakysafety.FlakySafetyProviderSimpleImpl] will continue
     * to attempt.
     */
    val allowedExceptions: Set<Class<out Throwable>>
) {

    companion object {
        fun default() = FlakySafetyParams(
            timeoutMs = 10_000L,
            intervalMs = 500L,
            allowedExceptions = setOf(
                PerformException::class.java,
                NoMatchingViewException::class.java,
                AssertionError::class.java,
                AssertionFailedError::class.java,
                UnfoundedUiObjectException::class.java,
                StaleObjectException::class.java
            )
        )

        fun custom(
            timeoutMs: Long,
            intervalMs: Long,
            allowedExceptions: Set<Class<out Throwable>>
        ): FlakySafetyParams = FlakySafetyParams(timeoutMs, intervalMs, allowedExceptions)
    }

    /**
     * The timeout during which attempts will be made by the
     * [com.kaspersky.kaspresso.flakysafety.FlakySafetyProviderSimpleImpl].
     */
    var timeoutMs: Long = timeoutMs
        set(value) {
            if (intervalMs >= value) throw IllegalArgumentException("An interval of attempts is shorter than timeout.")
            field = value
        }

    /**
     * The interval at which attempts will be made by the
     * [com.kaspersky.kaspresso.flakysafety.FlakySafetyProviderSimpleImpl].
     */
    var intervalMs: Long = intervalMs
        set(value) {
            if (value >= timeoutMs) throw IllegalArgumentException("An interval of attempts is shorter than timeout.")
            field = value
        }
}