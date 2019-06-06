package com.kaspersky.kaspresso.flakysafety

import com.kaspersky.kaspresso.configurator.ConfiguratorExt
import com.kaspersky.kaspresso.logger.UiTestLogger

/**
 * Waits for [timeoutMs] and invokes an [action].
 *
 * @param timeoutMs a time to wait in milliseconds.
 * @param logger a logger to log errors.
 * @param action an action that to be invoked.
 * @return [T] as it is a result of [action] invocation.
 */
fun <T> wait(
    timeoutMs: Long = ConfiguratorExt.attemptsTimeoutMs,
    logger: UiTestLogger = ConfiguratorExt.logger,
    action: () -> T
): T {
    logger.i("Waiting for $timeoutMs ms")
    Thread.sleep(timeoutMs)
    return action.invoke()
}