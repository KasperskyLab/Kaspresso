package com.kaspersky.kaspresso.flakysafety

/**
 * The interface to provide the flaky safety functionality.
 */
interface ContinuouslyProvider {

    /**
     * Invokes the given [action] during set timeout.
     *
     * It can be helpful for checking of negative scenarios.
     *
     * In opposite to [FlakySafetyProvider.flakySafely] it does not skip last attempt after first success
     * and throws inside exception outside as soon as it was thrown
     *
     * @param action the action to invoke.
     *
     * @return the [action] invocation result.
     */
    fun <T> continuously(action: () -> T): T

    /**
     * Invokes the given [action] during set timeout.
     *
     * It can be helpful for checking of negative scenarios.
     *
     * In opposite to [FlakySafetyProvider.flakySafely] it does not skips last attempt after first success
     * and throws inside exception outside as soon as it was thrown
     *
     * @param timeoutMs the timeout during which attempts will be made.
     * @param intervalMs the interval at which attempts will be made.
     * @param failureMessage the message to log on failure.
     * @param action the action to invoke.
     *
     * @return the [action] invocation result.
     */
    fun <T> continuously(
        timeoutMs: Long? = null,
        intervalMs: Long? = null,
        failureMessage: String? = null,
        action: () -> T
    ): T
}
