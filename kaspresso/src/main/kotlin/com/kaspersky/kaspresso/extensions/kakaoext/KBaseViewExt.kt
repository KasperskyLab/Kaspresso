package com.kaspersky.kaspresso.extensions.kakaoext

import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.klkakao.common.views.KBaseView
import java.util.concurrent.TimeUnit

/**
 * Provides an [com.kaspersky.kaspresso.flakysafety.attempt] method as an extension of [KBaseView].
 */
fun <T : KBaseView<Any>, R> T.attempt(
    timeoutMs: Long = Configurator.attemptsTimeoutMs,
    intervalMs: Long = Configurator.attemptsIntervalMs,
    logger: UiTestLogger = Configurator.logger,
    allowedExceptions: Set<Class<out Throwable>> = Configurator.allowedExceptionsForAttempt,
    action: T.() -> R
): R {
    return com.kaspersky.kaspresso.flakysafety.attempt(
        timeoutMs = timeoutMs,
        intervalMs = intervalMs,
        logger = logger,
        allowedExceptions = allowedExceptions
    ) {
        action.invoke(this)
    }
}

/**
 * Provides a simplified [com.kaspersky.kaspresso.flakysafety.attempt] method as an extension of [KBaseView].
 */
fun <T : KBaseView<Any>, R> T.attempt(
    timeoutSec: Long,
    action: T.() -> R
): R {
    return com.kaspersky.kaspresso.flakysafety.attempt(
        timeoutMs = TimeUnit.SECONDS.toMillis(timeoutSec)
    ) {
        action.invoke(this)
    }
}

/**
 * Provides a [com.kaspersky.kaspresso.flakysafety.wait] method as an extension of [KBaseView].
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

/**
 * Provides a simplified [com.kaspersky.kaspresso.flakysafety.wait] method as an extension of [KBaseView].
 */
fun <T : KBaseView<Any>, R> T.wait(
    timeoutSec: Long,
    action: T.() -> R
): R {
    return com.kaspersky.kaspresso.flakysafety.wait(
        timeoutMs = TimeUnit.SECONDS.toMillis(timeoutSec)
    ) {
        action.invoke(this)
    }
}