package com.kaspersky.kaspresso.internal.extensions.other

private const val SECOND_IN_MILLIS = 1000
private const val MINUTE_IN_SECONDS = 60

/**
 * Converts milliseconds to triple of time components.
 *
 * @return triple of minutes, seconds and millis.
 */
internal fun Long.toTime(): Triple<Long, Long, Long> {
    val millis = this % SECOND_IN_MILLIS
    val second = this / SECOND_IN_MILLIS % MINUTE_IN_SECONDS
    val minute = this / (SECOND_IN_MILLIS * MINUTE_IN_SECONDS)
    return Triple(minute, second, millis)
}
