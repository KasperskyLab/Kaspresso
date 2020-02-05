package com.kaspersky.kaspresso.params

/**
 * The class that holds all the necessary for [com.kaspersky.kaspresso.flakysafety.ContinuouslyProviderImpl] parameters.
 */
class ContinuouslyParams private constructor(
    timeoutMs: Long,
    intervalMs: Long
) {

    companion object {
        fun default() = ContinuouslyParams(
            timeoutMs = 10_000,
            intervalMs = 500
        )

        fun custom(timeoutMs: Long, intervalMs: Long) = ContinuouslyParams(
            timeoutMs = timeoutMs,
            intervalMs = intervalMs
        )
    }

    /**
     * The timeout during which attempts will be made by the
     * [com.kaspersky.kaspresso.flakysafety.ContinuouslyProviderImpl].
     */
    var timeoutMs: Long = timeoutMs
        set(value) {
            if (intervalMs >= value) throw IllegalArgumentException("An interval of attempts is shorter than timeout.")
            field = value
        }

    /**
     * The interval at which attempts will be made by the [com.kaspersky.kaspresso.flakysafety.ContinuouslyProviderImpl].
     */
    var intervalMs: Long = intervalMs
        set(value) {
            if (value >= timeoutMs) throw IllegalArgumentException("An interval of attempts is shorter than timeout.")
            field = value
        }
}