package com.kaspersky.kaspresso.flakysafety.algorithm

import com.kaspersky.kaspresso.internal.extensions.other.isAllowed
import com.kaspersky.kaspresso.internal.extensions.other.withMessage
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso.params.FlakySafetyParams
import java.util.Timer
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.schedule
import kotlin.concurrent.withLock

class FlakySafetyAlgorithm(
    private val logger: UiTestLogger
) {

    private val lock = ReentrantLock()
    private val condition = lock.newCondition()

    /**
     * Attempts to invoke the given [action].
     */
    fun <T> invokeFlakySafely(
        params: FlakySafetyParams,
        failureMessage: String? = null,
        action: () -> T
    ): T {
        var cachedError: Throwable
        val startTime = System.currentTimeMillis()

        do {
            try {
                return action.invoke()
            } catch (error: Throwable) {
                if (error.isAllowed(params.allowedExceptions)) {
                    cachedError = error
                    lock.withLock {
                        Timer().schedule(params.intervalMs) {
                            lock.withLock { condition.signalAll() }
                        }
                        condition.await()
                    }
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
