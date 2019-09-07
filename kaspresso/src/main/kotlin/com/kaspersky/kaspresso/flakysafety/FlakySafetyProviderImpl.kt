package com.kaspersky.kaspresso.flakysafety

import com.kaspersky.kaspresso.internal.exceptions.KaspressoAssertionError
import com.kaspersky.kaspresso.internal.extensions.other.isAllowed
import com.kaspersky.kaspresso.logger.UiTestLogger

class FlakySafetyProviderImpl(
    private val params: FlakySafetyParams,
    private val logger: UiTestLogger
) : FlakySafetyProvider {

    override fun <T> flakySafely(failureMessage: String?, action: () -> T): T {
        var cachedError: Throwable
        val startTime = System.currentTimeMillis()

        do {
            try {
                return action.invoke()
            } catch (error: Throwable) {
                if (error.isAllowed(params.allowedExceptions)) {
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