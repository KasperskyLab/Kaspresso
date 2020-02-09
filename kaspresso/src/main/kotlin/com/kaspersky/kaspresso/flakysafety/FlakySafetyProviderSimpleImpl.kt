package com.kaspersky.kaspresso.flakysafety

import com.kaspersky.kaspresso.flakysafety.algorithm.FlakySafetyAlgorithm
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso.params.FlakySafetyParams

/**
 * The implementation of the [FlakySafetyProvider] interface.
 * By default, this implementation is using to struggle with flaky UI libs inside a View
 */
class FlakySafetyProviderSimpleImpl(
    private val params: FlakySafetyParams,
    logger: UiTestLogger
) : FlakySafetyProvider {

    private val flakySafetyAlgorithm = FlakySafetyAlgorithm(logger)

    /**
     * Invokes the given [action] flaky safely.
     *
     * @param action the action to invoke.
     * @return the [action] invocation result.
     * @throws Throwable if all attempts failed.
     */
    @Throws(Throwable::class)
    override fun <T> flakySafely(action: () -> T): T =
        flakySafetyAlgorithm.invokeFlakySafely(
            params = params,
            action = action
        )

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
        allowedExceptions: Set<Class<out Throwable>>?,
        failureMessage: String?,
        action: () -> T
    ): T = flakySafetyAlgorithm.invokeFlakySafely(
        params = FlakySafetyParams.custom(
            timeoutMs ?: params.timeoutMs,
            intervalMs ?: params.intervalMs,
            allowedExceptions ?: params.allowedExceptions
        ),
        failureMessage = failureMessage,
        action = action
    )
}