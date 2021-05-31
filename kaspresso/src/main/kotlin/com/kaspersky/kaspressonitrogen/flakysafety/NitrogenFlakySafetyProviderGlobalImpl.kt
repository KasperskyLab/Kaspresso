package com.kaspersky.kaspressonitrogen.flakysafety

import com.kaspersky.kaspresso.flakysafety.FlakySafetyProvider
import com.kaspersky.kaspresso.flakysafety.algorithm.FlakySafetyAlgorithm
import com.kaspersky.kaspressonitrogen.flakysafety.scalpel.NitrogenFlakySafeInterceptorScalpel
import com.kaspersky.kaspressonitrogen.kaspresso.KaspressoNitrogen
import com.kaspersky.kaspresso.params.FlakySafetyParams

class NitrogenFlakySafetyProviderGlobalImpl(
        val kaspresso: KaspressoNitrogen
) : FlakySafetyProvider {

    private val flakySafetyAlgorithm = FlakySafetyAlgorithm(kaspresso.libLogger)
    private val flakySafeInterceptorScalpel = NitrogenFlakySafeInterceptorScalpel(kaspresso)

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
