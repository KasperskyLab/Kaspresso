package com.kaspersky.kaspresso.params

/**
 * The class that holds all the necessary for [com.kaspersky.kaspresso.flakysafety.ContinuouslyProviderImpl] parameters.
 */
class ContinuouslyParams(
    /**
     * The timeout during which attempts will be made by the
     * [com.kaspersky.kaspresso.flakysafety.ContinuouslyProviderImpl].
     */
    val timeoutMs: Long,

    /**
     * The interval at which attempts will be made by the [com.kaspersky.kaspresso.flakysafety.ContinuouslyProviderImpl].
     */
    val intervalMs: Long
) {
    init {
        require(timeoutMs > 0) { "Timeout must be > 0" }
        require(intervalMs > 0) { "Interval must be > 0" }
        require(timeoutMs > intervalMs) { "Timeout must be > interval" }
    }

    companion object {
        const val defaultTimeoutMs: Long = 10_000L
        const val defaultIntervalMs: Long = 500L

        fun default() = ContinuouslyParams(
            timeoutMs = defaultTimeoutMs,
            intervalMs = defaultIntervalMs
        )

        fun custom(
            timeoutMs: Long = defaultTimeoutMs,
            intervalMs: Long = defaultIntervalMs
        ) = ContinuouslyParams(
            timeoutMs = timeoutMs,
            intervalMs = intervalMs
        )
    }
}
