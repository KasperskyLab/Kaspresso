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
     * @param allowedExceptions the set of exceptions, if caught, attempt will continue.
     * @param failureMessage the message to log on failure.
     * @param action the action to invoke.
     *
     * @return the [action] invocation result.
     */
    fun <T> flakySafely(
        timeoutMs: Long? = null,
        intervalMs: Long? = null,
        allowedExceptions: MutableSet<Class<out Throwable>>? = null,
        failureMessage: String? = null,
        action: () -> T
    ): T
}

/**
 * The function to call [FlakySafetyProvider.flakySafely] with null-safety.
 *
 * @param action the action to invoke.
 *
 * @return the result of the [action] invocation.
 */
internal fun <T> FlakySafetyProvider?.flakySafelyIfNotNull(action: () -> T): T =
    if (this != null) flakySafely(action) else action.invoke()