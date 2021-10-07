package com.kaspersky.kaspresso.flakysafety

import com.kaspersky.kaspresso.internal.extensions.other.withMessage
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.params.ContinuouslyParams
import java.util.Timer
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.schedule
import kotlin.concurrent.withLock

/**
 * The implementation of the [ContinuouslyProvider] interface.
 */
class ContinuouslyProviderImpl(
    private val kaspresso: Kaspresso
) : ContinuouslyProvider {

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
    override fun <T> continuously(action: () -> T): T = invokeContinuously(getParams(), null, action)

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
    override fun <T> continuously(
        timeoutMs: Long?,
        intervalMs: Long?,
        failureMessage: String?,
        action: () -> T
    ): T {
        return invokeContinuously(
            params = getParams(timeoutMs, intervalMs),
            failureMessage = failureMessage,
            action = action
        )
    }

    private fun getParams(timeoutMs: Long? = null, intervalMs: Long? = null): ContinuouslyParams {
        val defaultParams = kaspresso.params.continuouslyParams
        return ContinuouslyParams(
            timeoutMs = timeoutMs ?: defaultParams.timeoutMs,
            intervalMs = intervalMs ?: defaultParams.intervalMs
        )
    }

    /**
     * Attempts to invoke the given [action].
     */
    private fun <T> invokeContinuously(params: ContinuouslyParams, failureMessage: String?, action: () -> T): T {
        val startTime = System.currentTimeMillis()

        do {
            try {
                action.invoke()
            } catch (error: Throwable) {
                kaspresso.libLogger.e(
                    "Continuous interaction for ${params.timeoutMs} ms failed " +
                            "because of ${error.javaClass.simpleName}"
                )
                throw if (failureMessage != null) error.withMessage(failureMessage) else error
            }
            lock.withLock {
                Timer().schedule(params.intervalMs) {
                    lock.withLock { condition.signalAll() }
                }
                condition.await()
            }
        } while (System.currentTimeMillis() - startTime <= params.timeoutMs)

        kaspresso.libLogger.i(
            "Continuous interaction during the ${params.timeoutMs} succeeded"
        )

        return action.invoke()
    }
}
