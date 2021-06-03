package com.kaspersky.kaspresso.internal.wait

import com.kaspersky.kaspresso.internal.extensions.other.isAllowed
import com.kaspersky.kaspresso.logger.UiTestLogger

/**
 * Waits for [timeoutMs] and invokes an [action].
 *
 * @param timeoutMs a time to wait in milliseconds.
 * @param logger a logger to log errors.
 * @param action an action that to be invoked.
 * @return [T] as it is a result of [action] invocation.
 */
internal fun <T> wait(
    timeoutMs: Long,
    logger: UiTestLogger,
    action: () -> T
): T {
    logger.i("Waiting for $timeoutMs ms")
    Thread.sleep(timeoutMs)
    return action.invoke()
}

internal fun <T> wait(
    timeoutMs: Long,
    logger: UiTestLogger,
    allowedExceptions: Set<Class<out Throwable>>,
    failureMessageSource: (Throwable) -> String?,
    action: (() -> T)?
): T? {
    logger.i("Waiting for $timeoutMs ms")
    return try {
        Thread.sleep(timeoutMs)
        action?.invoke()
    } catch (error: Throwable) {
        if (error.isAllowed(allowedExceptions)) {
            logger.e(failureMessageSource(error) ?: "An error occurred while waiting: ${error.javaClass.simpleName}")
            null
        } else {
            throw error
        }
    }
}
