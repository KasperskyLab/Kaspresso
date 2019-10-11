package com.kaspersky.kaspresso.flakysafety

import com.kaspersky.kaspresso.internal.extensions.other.withMessage
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso.params.CheckDuringParams
import java.util.Timer
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.schedule
import kotlin.concurrent.withLock

/**
 * The implementation of the [CheckDuringProvider] interface.
 */
class CheckDuringProviderImpl(
    private val params: CheckDuringParams,
    private val logger: UiTestLogger
) : CheckDuringProvider {

    private val lock = ReentrantLock()
    private val condition = lock.newCondition()

    /**
     * Invokes the given [action] during set timeout.
     *
     * @param action the action to invoke.
     *
     * @return the [action] invocation result.
     *
     * @throws Throwable if any of attempts failed.
     */
    @Throws(Throwable::class)
    override fun <T> checkDuring(action: () -> T) = invokeCheckDuring(params, null, action)

    /**
     * Invokes the given [action] during set timeout.
     *
     * @param timeoutMs the timeout during which attempts will be made.
     * @param intervalMs the interval at which attempts will be made.
     * @param failureMessage the message to log on failure.
     * @param action the action to invoke.
     *
     * @return the [action] invocation result.
     *
     * @throws Throwable if any of attempts failed.
     */
    @Throws(Throwable::class)
    override fun <T> checkDuring(
        timeoutMs: Long?,
        intervalMs: Long?,
        failureMessage: String?,
        action: () -> T
    ) = invokeCheckDuring(
        params = CheckDuringParams(
            timeoutMs ?: params.timeoutMs,
            intervalMs ?: params.intervalMs
        ),
        failureMessage = failureMessage,
        action = action
    )

    /**
     * Attempts to invoke the given [action].
     */
    private fun <T> invokeCheckDuring(params: CheckDuringParams, failureMessage: String?, action: () -> T) {
        val startTime = System.currentTimeMillis()

        do {
            try {
                action.invoke()
            } catch (error: Throwable) {
                throw if (failureMessage != null) error.withMessage(failureMessage) else error
            }
            lock.withLock {
                Timer().schedule(params.intervalMs) {
                    lock.withLock { condition.signalAll() }
                }
                condition.await()
            }
        } while (System.currentTimeMillis() - startTime <= params.timeoutMs)

        logger.i(
            "All attempts to interact for ${params.timeoutMs} ms passed"
        )
    }
}