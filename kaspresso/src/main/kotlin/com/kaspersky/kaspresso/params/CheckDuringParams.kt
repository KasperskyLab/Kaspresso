package com.kaspersky.kaspresso.params

/**
 * The class that holds all the necessary for [com.kaspersky.kaspresso.flakysafety.CheckDuringProviderImpl] parameters.
 */
class CheckDuringParams(
    timeoutMs: Long = DEFAULT_TIMEOUT_MS,
    intervalMs: Long = DEFAULT_INTERVAL_MS
) {
    private companion object {
        private const val DEFAULT_TIMEOUT_MS: Long = 2_000L
        private const val DEFAULT_INTERVAL_MS: Long = 500L
    }

    /**
     * The timeout during which attempts will be made by the
     * [com.kaspersky.kaspresso.flakysafety.CheckDuringProviderImpl].
     */
    var timeoutMs: Long = timeoutMs
        set(value) {
            if (intervalMs >= value) throw IllegalArgumentException("An interval of attempts is shorter than timeout.")
            field = value
        }

    /**
     * The interval at which attempts will be made by the [com.kaspersky.kaspresso.flakysafety.CheckDuringProviderImpl].
     */
    var intervalMs: Long = intervalMs
        set(value) {
            if (value >= timeoutMs) throw IllegalArgumentException("An interval of attempts is shorter than timeout.")
            field = value
        }
}