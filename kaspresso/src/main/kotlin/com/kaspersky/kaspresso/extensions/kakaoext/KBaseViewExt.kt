package com.kaspersky.kaspresso.extensions.kakaoext

import com.agoda.kakao.common.views.KBaseView
import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.logger.UiTestLogger

/**
 * Provides [attempt] method as an extension of [KBaseView].
 */
fun <T : KBaseView<Any>, R> T.attempt(
    timeoutMs: Long = Configurator.attemptsTimeoutMs,
    intervalMs: Long = Configurator.attemptsIntervalMs,
    message: String? = null,
    logger: UiTestLogger = Configurator.logger,
    allowedExceptions: Set<Class<out Throwable>> = Configurator.allowedExceptionsForAttempt,
    action: T.() -> R
): R {
    return com.kaspersky.kaspresso.flakysafety.attempt(
        timeoutMs = timeoutMs,
        intervalMs = intervalMs,
        message = message,
        logger = logger,
        allowedExceptions = allowedExceptions
    ) {
        action.invoke(this)
    }
}

/**
 * Provides [wait] method as an extension of [KBaseView].
 */
fun <T : KBaseView<Any>, R> T.wait(
    timeoutMs: Long = Configurator.attemptsTimeoutMs,
    logger: UiTestLogger = Configurator.logger,
    action: T.() -> R
): R {
    return com.kaspersky.kaspresso.flakysafety.wait(
        timeoutMs = timeoutMs,
        logger = logger
    ) {
        action.invoke(this)
    }
}
