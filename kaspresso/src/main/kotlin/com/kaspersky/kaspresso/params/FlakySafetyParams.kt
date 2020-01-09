package com.kaspersky.kaspresso.params

import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.PerformException
import androidx.test.uiautomator.StaleObjectException
import com.kaspersky.components.kautomator.intercepting.exception.UnfoundedUiObjectException
import junit.framework.AssertionFailedError

/**
 * The class that holds all the necessary for [com.kaspersky.kaspresso.flakysafety.FlakySafetyProviderImpl] parameters.
 */
class FlakySafetyParams private constructor(
    timeoutMs: Long,
    intervalMs: Long,

    /**
     * The set of exceptions, if caught, the [com.kaspersky.kaspresso.flakysafety.FlakySafetyProviderImpl] will continue
     * to attempt.
     */
    val allowedExceptions: Set<Class<out Throwable>>
) {

    companion object {
        fun kakaoInstance(): FlakySafetyParams {
            return FlakySafetyParams(
                timeoutMs = 5_000L,
                intervalMs = 500L,
                allowedExceptions = setOf(
                    PerformException::class.java,
                    NoMatchingViewException::class.java,
                    AssertionError::class.java,
                    AssertionFailedError::class.java
                )
            )
        }

        fun kautomatorInstance(): FlakySafetyParams {
            return FlakySafetyParams(
                timeoutMs = 15_000L,
                intervalMs = 1_000L,
                allowedExceptions = setOf(
                    PerformException::class.java,
                    AssertionError::class.java,
                    AssertionFailedError::class.java,
                    UnfoundedUiObjectException::class.java,
                    StaleObjectException::class.java
                )
            )
        }

        fun customInstance(
            timeoutMs: Long,
            intervalMs: Long,
            allowedExceptions: Set<Class<out Throwable>>
        ): FlakySafetyParams = FlakySafetyParams(timeoutMs, intervalMs, allowedExceptions)
    }

    /**
     * The timeout during which attempts will be made by the
     * [com.kaspersky.kaspresso.flakysafety.FlakySafetyProviderImpl].
     */
    var timeoutMs: Long = timeoutMs
        set(value) {
            if (intervalMs >= value) throw IllegalArgumentException("An interval of attempts is shorter than timeout.")
            field = value
        }

    /**
     * The interval at which attempts will be made by the [com.kaspersky.kaspresso.flakysafety.FlakySafetyProviderImpl].
     */
    var intervalMs: Long = intervalMs
        set(value) {
            if (value >= timeoutMs) throw IllegalArgumentException("An interval of attempts is shorter than timeout.")
            field = value
        }
}