package com.kaspersky.kaspresso.extensions.other

/**
 *  Converts milliseconds to triple of time components.
 *
 *  @return triple of minutes, seconds and millis.
 */
fun Long.toTime(): Triple<Long, Long, Long> {
    val millis = this % 1000
    val second = this / 1000 % 60
    val minute = this / (1000 * 60)
    return Triple(minute, second, millis)
}