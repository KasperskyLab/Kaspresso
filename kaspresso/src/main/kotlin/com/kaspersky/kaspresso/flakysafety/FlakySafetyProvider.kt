package com.kaspersky.kaspresso.flakysafety

import com.kaspersky.kaspresso.internal.exceptions.KaspressoAssertionError
import com.kaspersky.kaspresso.logger.UiTestLogger

interface FlakySafetyProvider {

    val params: FlakySafetyParams
    val logger: UiTestLogger

    fun <T> flakySafely(failureMessage: String? = null, action: () -> T): T {
        var cachedError: Throwable
        val startTime = System.currentTimeMillis()

        do {
            try {
                return action.invoke()
            } catch (error: Throwable) {
                if (params.isExceptionAllowed(error)) {
                    Thread.sleep(params.intervalMs)
                    cachedError = error
                } else {
                    throw error
                }
            }
        } while (System.currentTimeMillis() - startTime <= params.timeoutMs)

        logger.e(
            "All attempts to interact for ${params.timeoutMs} ms totally failed " +
                    "because of \n${cachedError.javaClass.simpleName}${cachedError.message?.let { ": $it" } ?: ""}"
        )

        throw failureMessage?.let { KaspressoAssertionError(it, cachedError) } ?: cachedError
    }
}

fun <T> FlakySafetyProvider?.flakySafelyIfNotNull(failureMessage: String? = null, action: () -> T): T =
    if (this != null) flakySafely(failureMessage, action) else action.invoke()