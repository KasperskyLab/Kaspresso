package com.kaspersky.kaspresso.flakysafety

import com.kaspersky.kaspresso.internal.extensions.other.isAllowed
import com.kaspersky.kaspresso.internal.extensions.other.withMessage
import com.kaspersky.kaspresso.logger.UiTestLogger

class FlakySafetyProviderImpl(
    private val params: FlakySafetyParams,
    private val logger: UiTestLogger
) : FlakySafetyProvider {

    @Throws(Throwable::class)
    override fun <T> flakySafely(action: () -> T): T = invokeFlakySafely(params, null, action)

    @Throws(Throwable::class)
    override fun <T> flakySafely(
        timeoutMs: Long?,
        intervalMs: Long?,
        allowedExceptions: MutableSet<Class<out Throwable>>?,
        failureMessage: String?,
        action: () -> T
    ): T = invokeFlakySafely(
        params = FlakySafetyParams(
            timeoutMs ?: params.timeoutMs,
            intervalMs ?: params.intervalMs,
            allowedExceptions ?: params.allowedExceptions
        ),
        failureMessage = failureMessage,
        action = action
    )

    private fun <T> invokeFlakySafely(params: FlakySafetyParams, failureMessage: String?, action: () -> T): T {
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
                    "because of ${cachedError.javaClass.simpleName}"
        )

        throw cachedError.withMessage(failureMessage)
    }
}