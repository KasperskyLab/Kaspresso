package com.kaspersky.kaspresso.flakysafety

import com.kaspersky.kaspresso.internal.extensions.other.isAllowed
import com.kaspersky.kaspresso.internal.extensions.other.withMessage
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso.params.FlakySafetyParams
import java.util.Timer
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.schedule
import kotlin.concurrent.withLock

/**
 * The implementation of the [FlakySafetyProvider] interface.
 */
class FlakySafetyProviderImpl(
    private val params: FlakySafetyParams,
    private val logger: UiTestLogger
) : FlakySafetyProvider {

    private val lock = ReentrantLock()
    private val condition = lock.newCondition()

    /**
     * Invokes the given [action] flaky safely.
     *
     * @param action the action to invoke.
     *
     * @return the [action] invocation result.
     *
     * @throws Throwable if all attempts failed.
     */
    @Throws(Throwable::class)
    override fun <T> flakySafely(action: () -> T): T = invokeFlakySafely(params, null, action)

    /**
     * Invokes the given [action] flaky safely.
     *
     * @param timeoutMs the timeout during which attempts will be made.
     * @param intervalMs the interval at which attempts will be made.
     * @param allowedExceptions the set of exceptions, if caught, attempt will continue.
     * @param failureMessage the message to log on failure.
     * @param action the action to invoke.
     *
     * @return the [action] invocation result.
     *
     * @throws Throwable if all attempts failed.
     */
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

    /**
     * Attempts to invoke the given [action].
     */
    private fun <T> invokeFlakySafely(params: FlakySafetyParams, failureMessage: String?, action: () -> T): T {
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