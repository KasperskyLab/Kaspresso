package com.kaspersky.kaspresso.flakysafety

import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.exceptions.KaspressoAssertionError
import com.kaspersky.kaspresso.extensions.other.getStackTraceAsString
import com.kaspersky.kaspresso.logger.UiTestLogger

/**
 * Makes several attempts to invoke an action.
 *
 * @param timeoutMs a timeout for all attempts in milliseconds.
 * @param intervalMs an interval between attempts in milliseconds.
 * @param message a message of an AssertionError if it's set to anything but null.
 * @param logger a logger to log errors.
 * @param allowedExceptions exceptions that doesn't stop attempts.
 * @param action an action that is attempted to be invoked.
 * @return [T] as it is a result of [action] invocation.
 */
fun <T> attempt(
    timeoutMs: Long = Configurator.attemptsTimeoutMs,
    intervalMs: Long = Configurator.attemptsIntervalMs,
    message: String? = null,
    logger: UiTestLogger = Configurator.logger,
    allowedExceptions: Set<Class<out Throwable>> = Configurator.allowedExceptionsForAttempt,
    action: () -> T
): T {
    if (intervalMs >= timeoutMs) {
        throw IllegalArgumentException("The interval of attempts should be longer than the timeout of all attempts")
    }

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
                    Thread.sleep(intervalMs)
                    caughtAllowedException = e
                }
                else -> {
                    throw e
                }
            }
        }
    } while (System.currentTimeMillis() - startTime <= timeoutMs)

    logger.e(
        "All attempts to interact for $timeoutMs ms totally failed " +
                "because of ${caughtAllowedException.javaClass.simpleName}"
    )

    logger.e(caughtAllowedException.getStackTraceAsString())

    failAttempt(message, caughtAllowedException)
}

/**
 *  Throws [KaspressoAssertionError] if message is specified, or [caughtAllowedException] otherwise.
 */
private fun failAttempt(message: String?, caughtAllowedException: Throwable): Nothing {
    if (message != null) {
        throw KaspressoAssertionError(message, caughtAllowedException)
    }

    throw caughtAllowedException
}
