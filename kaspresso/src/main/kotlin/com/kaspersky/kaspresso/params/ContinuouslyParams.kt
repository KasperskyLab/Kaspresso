package com.kaspersky.kaspresso.params

import kotlin.math.max

/**
 * The class that holds all the necessary for [com.kaspersky.kaspresso.flakysafety.ContinuouslyProviderImpl] parameters.
 */
class ContinuouslyParams private constructor(
    timeoutMs: Long,
    intervalMs: Long
) {

    companion object {
        fun kakaoInstance() = ContinuouslyParams(
            timeoutMs = 5_000,
            intervalMs = 500
        )
        fun kautomatorInstance() = ContinuouslyParams(
            timeoutMs = 15_000,
            intervalMs = 500
        )
        fun customInstance(timeoutMs: Long, intervalMs: Long) = ContinuouslyParams(
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

    fun createByMerging(continuouslyParams: ContinuouslyParams): ContinuouslyParams {
        return ContinuouslyParams(
            timeoutMs = max(this.timeoutMs, continuouslyParams.timeoutMs),
            intervalMs = max(this.intervalMs, continuouslyParams.intervalMs)
        )
    }
}