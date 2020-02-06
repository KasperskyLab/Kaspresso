package com.kaspersky.kaspresso.flakysafety

import com.kaspersky.kaspresso.flakysafety.algorithm.FlakySafetyAlgorithm
import com.kaspersky.kaspresso.flakysafety.scalpel.FlakySafeInterceptorScalpel
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.params.FlakySafetyParams

class FlakySafetyProviderGlobalImpl(
    private val kaspresso: Kaspresso
) : FlakySafetyProvider {

    private val flakySafetyAlgorithm = FlakySafetyAlgorithm(kaspresso.libLogger)
    private val flakySafeInterceptorScalpel = FlakySafeInterceptorScalpel(kaspresso)

    override fun <T> flakySafely(action: () -> T): T {
        flakySafeInterceptorScalpel.scalpFromLibs()

        val result = flakySafetyAlgorithm.invokeFlakySafely(
            params = getParams(),
            action = action
        )

        flakySafeInterceptorScalpel.restoreScalpToLibs()

        return result
    }

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