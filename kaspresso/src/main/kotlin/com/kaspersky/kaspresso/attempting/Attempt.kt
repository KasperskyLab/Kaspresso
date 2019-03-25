package com.kaspersky.kaspresso.attempting

import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso.extensions.other.getStackTraceAsString

/**
 * Makes several attempts to invoke an action.
 *
 * @param timeoutMs a timeout for all attempts in milliseconds.
 * @param attemptsFrequencyMs a frequency of attempts in milliseconds.
 * @param logger a logger to log errors.
 * @param allowedExceptions exceptions that doesn't stop attempts.
 * @param action an action that is attempted to be invoked.
 * @return [T] as it is a result of [action] invocation.
 */
fun <T> attempt(
    timeoutMs: Long = Configurator.attemptsTimeoutMs,
    attemptsFrequencyMs: Long = Configurator.attemptsFrequencyMs,
    logger: UiTestLogger = Configurator.logger,
    allowedExceptions: Set<Class<out Throwable>> = Configurator.allowedExceptionsForAttempt,
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

    logger.e(
        "All attempts to interact for $timeoutMs ms totally failed " +
                "because of ${caughtAllowedException.javaClass.simpleName}"
    )

    logger.e(caughtAllowedException.getStackTraceAsString())

    throw caughtAllowedException
}