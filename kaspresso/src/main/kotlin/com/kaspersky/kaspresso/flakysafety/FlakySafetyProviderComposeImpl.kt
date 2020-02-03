package com.kaspersky.kaspresso.flakysafety

import com.kaspersky.kaspresso.flakysafety.algorithm.FlakySafetyAlgorithm
import com.kaspersky.kaspresso.flakysafety.scalpel.FlakySafeInterceptorScalpel.restoreFlakySafeInterceptorToLibs
import com.kaspersky.kaspresso.flakysafety.scalpel.FlakySafeInterceptorScalpel.scalpFlakySafeInterceptorFromLibs
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.params.FlakySafetyParams

class FlakySafetyProviderComposeImpl(
    private val kaspresso: Kaspresso
) : FlakySafetyProvider {

    private val flakySafetyAlgorithm = FlakySafetyAlgorithm(kaspresso.libLogger)

    override fun <T> flakySafely(action: () -> T): T {
        scalpFlakySafeInterceptorFromLibs(kaspresso)

        val result = flakySafetyAlgorithm.invokeFlakySafely(
            params = getParams(),
            action = action
        )

        restoreFlakySafeInterceptorToLibs(kaspresso)

        return result
    }

    override fun <T> flakySafely(
        timeoutMs: Long?,
        intervalMs: Long?,
        allowedExceptions: Set<Class<out Throwable>>?,
        failureMessage: String?,
        action: () -> T
    ): T {
        scalpFlakySafeInterceptorFromLibs(kaspresso)

        val result = flakySafetyAlgorithm.invokeFlakySafely(
            params = getParams(timeoutMs, intervalMs, allowedExceptions),
            failureMessage = failureMessage,
            action = action
        )

        restoreFlakySafeInterceptorToLibs(kaspresso)

        return result
    }

    private fun getParams(
        timeoutMs: Long? = null,
        intervalMs: Long? = null,
        allowedExceptions: Set<Class<out Throwable>>? = null
    ): FlakySafetyParams {
        val defaultParams = kaspresso.params.flakySafetyParams.merge(
            kaspresso.params.kautomatorFlakySafetyParams
        )
        return FlakySafetyParams.customInstance(
            timeoutMs ?: defaultParams.timeoutMs,
            intervalMs ?: defaultParams.intervalMs,
            allowedExceptions ?: defaultParams.allowedExceptions
        )
    }
}