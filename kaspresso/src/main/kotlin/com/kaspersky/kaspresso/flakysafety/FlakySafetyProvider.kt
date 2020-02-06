package com.kaspersky.kaspresso.flakysafety

/**
 * The interface to provide the flaky safety functionality.
 */
interface FlakySafetyProvider {

    /**
     * Invokes the given [action] flaky safely.
     *
     * @param action the action to invoke.
     *
     * @return the [action] invocation result.
     */
    fun <T> flakySafely(action: () -> T): T

    /**
     * Invokes the given [action] flaky safely.
     *
     * @param timeoutMs the timeout during which attempts will be made.
     * @param intervalMs the interval at which attempts will be made.
     * @param allowedExceptions the set of exceptions that allow to continue an attempt of execution.
     * @param failureMessage the message to log on failure.
     * @param action the action to invoke.
     *
     * @return the [action] invocation result.
     */
    fun <T> flakySafely(
        timeoutMs: Long? = null,
        intervalMs: Long? = null,
        allowedExceptions: Set<Class<out Throwable>>? = null,
        failureMessage: String? = null,
        action: () -> T
    ): T
}
