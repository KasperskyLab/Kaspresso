package com.kaspersky.kaspresso.flakysafety

import com.kaspersky.kaspresso.flakysafety.algorithm.FlakySafetyAlgorithm
import com.kaspersky.kaspresso.flakysafety.scalpel.FlakySafeInterceptorScalpel
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.params.FlakySafetyParams

/**
 * The implementation of the [FlakySafetyProvider] interface.
 *
 * By default, this implementation is using in tests (not inside a View).
 *
 * Why? Have a glance at the example:
 * ```
 * someView {
 *     flakySafety(timeout = 20.sec) {
 *         isVisible()
 *     }
 * }
 * ```
 * In this case, Kaspresso tries to execute someView.isVisible() for 10 seconds (default timeout for FlakySafety interceptors).
 * After 10 seconds someView.isVisible() fails, throws an exception and writes info in logs.
 * This exception is catching by extra ```flakySafety``` that tries to repeat.
 *
 * But such log messages make logs dirty and confuse a developer or tester.
 *
 * Besides, extra ```flakySafety``` can contain more actions,
 * that's why there is probability of not having time to execute entire block.
 * Honestly, we don't recommend to put into flakySafety block more than one concrete action.
 *
 * All of this pushed us to write special implementation of [FlakySafetyProvider].
 *
 * The algorithm:
 *
 * 1. [FlakySafetyProviderGlobalImpl] removes all interceptors implementing FlakySafety behavior from Kakao/Kautomator before the action.
 * 2. [FlakySafetyProviderGlobalImpl] executes the entire! action inside own FlakySafety.
 * 3. [FlakySafetyProviderGlobalImpl] restores all interceptors implementing FlakySafety behavior from Kakao/Kautomator after the action.
 *
 * Such behavior allows us to observe more predictable log information (a developer will see only one and correct error message in described above example)
 * and avoids potentially inconsistent execution.
 *
 */
class FlakySafetyProviderGlobalImpl(
    private val kaspresso: Kaspresso
) : FlakySafetyProvider {

    private val flakySafetyAlgorithm = FlakySafetyAlgorithm(kaspresso.libLogger)
    private val flakySafeInterceptorScalpel = FlakySafeInterceptorScalpel(kaspresso)

    /**
     * Invokes the given [action] flaky safely.
     *
     * @param action the action to invoke.
     * @return the [action] invocation result.
     * @throws Throwable if all attempts failed.
     */
    @Throws(Throwable::class)
    override fun <T> flakySafely(action: () -> T): T {
        flakySafeInterceptorScalpel.scalpFromLibs()

        val result = flakySafetyAlgorithm.invokeFlakySafely(
            params = getParams(),
            action = action
        )

        flakySafeInterceptorScalpel.restoreScalpToLibs()

        return result
    }

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
    ): T {
        flakySafeInterceptorScalpel.scalpFromLibs()

        val result = flakySafetyAlgorithm.invokeFlakySafely(
            params = getParams(timeoutMs, intervalMs, allowedExceptions),
            failureMessage = failureMessage,
            action = action
        )

        flakySafeInterceptorScalpel.restoreScalpToLibs()

        return result
    }

    private fun getParams(
        timeoutMs: Long? = null,
        intervalMs: Long? = null,
        allowedExceptions: Set<Class<out Throwable>>? = null
    ): FlakySafetyParams {
        val defaultParams = kaspresso.params.flakySafetyParams
        return FlakySafetyParams.custom(
            timeoutMs ?: defaultParams.timeoutMs,
            intervalMs ?: defaultParams.intervalMs,
            allowedExceptions ?: defaultParams.allowedExceptions
        )
    }
}