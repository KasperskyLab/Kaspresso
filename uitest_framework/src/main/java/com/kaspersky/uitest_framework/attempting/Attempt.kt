package com.kaspersky.uitest_framework.attempting

import com.kaspersky.uitest_framework.Configuration
import com.kaspersky.uitest_framework.util.getStackTraceAsString

fun <T> attempt(
        timeoutMs: Long = Configuration.attemptsTimeoutMs,
        attemptsFrequencyMs: Long = Configuration.attemptsFrequencyMs,
        allowedExceptions: Set<Class<out Throwable>> = Configuration.allowedExceptionsForAttempt,
        action: () -> T
): T {
    var timer = 0L
    var caughtAllowedException: Throwable

    val startTime = System.currentTimeMillis()

    do {
        try {
            return action.invoke()
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

    Configuration.logger.e(
            "All attempts to interact for $timeoutMs ms totally failed " +
                    "because of ${caughtAllowedException::class.simpleName}"
    )

    Configuration.logger.e(caughtAllowedException.getStackTraceAsString())

    throw caughtAllowedException
}