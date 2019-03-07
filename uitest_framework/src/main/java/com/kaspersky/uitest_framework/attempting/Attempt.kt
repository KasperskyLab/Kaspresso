package com.kaspersky.uitest_framework.attempting

import com.kaspersky.uitest_framework.Configuration

fun attempt(
        timeoutMs: Long = Configuration.attemptsTimeoutMs,
        attemptsFrequencyMs: Long = Configuration.attemptsFrequencyMs,
        allowedExceptions: Set<Class<out Throwable>> = Configuration.allowedExceptionsForAttempt,
        action: () -> Unit
) {
    var timer = 0L
    var caughtAllowedException: Throwable

    val startTime = System.currentTimeMillis()

    do {
        try {
            action.invoke()
            return
        } catch (e: Throwable) {
            val isExceptionAllowed =
                    allowedExceptions.find { it.isAssignableFrom(e.javaClass) } != null

            when {
                isExceptionAllowed -> {
                    Thread.sleep(attemptsFrequencyMs)
                    timer += attemptsFrequencyMs
                    caughtAllowedException = e
                }
                else -> {
                    throw e
                }
            }
        }
    } while (timer <= timeoutMs && System.currentTimeMillis() - startTime <= timeoutMs)

    throw caughtAllowedException
}