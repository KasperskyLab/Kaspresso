package com.kaspersky.kaspresso.flakysafety

import com.kaspersky.kaspresso.internal.exceptions.KaspressoAssertionError
import com.kaspersky.kaspresso.logger.UiTestLogger

interface FlakySafetyProvider {

    val params: FlakySafetyParams
    val logger: UiTestLogger

    fun <T> flakySafely(failureMessage: String? = null, action: () -> T): T {
        var caughtAllowedException: Throwable
        val startTime = System.currentTimeMillis()

        do {
            try {
                return action.invoke()
            } catch (error: Throwable) {
                if (params.isExceptionAllowed(error)) {
                    Thread.sleep(params.intervalMs)
                    caughtAllowedException = error
                } else {
                    throw error
                }
            }
        } while (System.currentTimeMillis() - startTime <= params.timeoutMs)

        logger.e(
            "All attempts to interact for ${params.timeoutMs} ms totally failed " +
                    "because of ${caughtAllowedException.javaClass.simpleName}"
        )

        throw failureMessage?.let { KaspressoAssertionError(it, caughtAllowedException) } ?: caughtAllowedException
    }
}

fun <T> FlakySafetyProvider?.flakySafelyIfNotNull(failureMessage: String? = null, action: () -> T): T =
    if (this != null) flakySafely(failureMessage, action) else action.invoke()