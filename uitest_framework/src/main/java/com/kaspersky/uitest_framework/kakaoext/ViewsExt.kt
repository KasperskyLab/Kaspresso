package com.kaspersky.uitest_framework.kakaoext

import com.kaspersky.uitest_framework.configurator.Configurator
import com.agoda.kakao.common.views.KBaseView
import java.util.concurrent.TimeUnit

/**
 * Provides the [com.kaspersky.uitest_framework.attempting.attempt] method as an extension of [KBaseView].
 */
fun <T : KBaseView<Any>> T.attempt(
    timeoutMs: Long = Configurator.attemptsTimeoutMs,
    attemptsFrequencyMs: Long = Configurator.attemptsFrequencyMs,
    allowedExceptions: Set<Class<out Throwable>> = Configurator.allowedExceptionsForAttempt,
    action: T.() -> Unit
) {
    com.kaspersky.uitest_framework.attempting.attempt(
        timeoutMs,
        attemptsFrequencyMs,
        allowedExceptions
    ) {
        action.invoke(this)
    }
}

/**
 * Provides the simplified [com.kaspersky.uitest_framework.attempting.attempt] method as an extension of [KBaseView].
 */
fun <T : KBaseView<Any>> T.attempt(
    timeoutSec: Long,
    action: T.() -> Unit
) {
    com.kaspersky.uitest_framework.attempting.attempt(
        TimeUnit.SECONDS.toMillis(timeoutSec)
    ) {
        action.invoke(this)
    }
}