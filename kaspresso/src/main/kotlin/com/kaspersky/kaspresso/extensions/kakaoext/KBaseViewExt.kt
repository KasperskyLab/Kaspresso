package com.kaspersky.kaspresso.extensions.kakaoext

import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.klkakao.common.views.KBaseView
import java.util.concurrent.TimeUnit

/**
 * Provides an [com.kaspersky.kaspresso.attempting.attempt] method as an extension of [KBaseView].
 */
fun <T : KBaseView<Any>> T.attempt(
    timeoutMs: Long = Configurator.attemptsTimeoutMs,
    attemptsFrequencyMs: Long = Configurator.attemptsFrequencyMs,
    logger: UiTestLogger = Configurator.logger,
    allowedExceptions: Set<Class<out Throwable>> = Configurator.allowedExceptionsForAttempt,
    action: T.() -> Unit
) {
    com.kaspersky.kaspresso.attempting.attempt(
        timeoutMs = timeoutMs,
        attemptsFrequencyMs = attemptsFrequencyMs,
        logger = logger,
        allowedExceptions = allowedExceptions
    ) {
        action.invoke(this)
    }
}

/**
 * Provides a simplified [com.kaspersky.kaspresso.attempting.attempt] method as an extension of [KBaseView].
 */
fun <T : KBaseView<Any>> T.attempt(
    timeoutSec: Long,
    action: T.() -> Unit
) {
    com.kaspersky.kaspresso.attempting.attempt(
        timeoutMs = TimeUnit.SECONDS.toMillis(timeoutSec)
    ) {
        action.invoke(this)
    }
}