package com.kaspersky.kaspresso.kakaoext

import com.kaspersky.kaspresso.configurator.Configurator
import com.agoda.kakao.common.views.KBaseView
import java.util.concurrent.TimeUnit

fun <T : KBaseView<Any>> T.attempt(
    timeoutMs: Long = Configurator.attemptsTimeoutMs,
    attemptsFrequencyMs: Long = Configurator.attemptsFrequencyMs,
    allowedExceptions: Set<Class<out Throwable>> = Configurator.allowedExceptionsForAttempt,
    action: T.() -> Unit
) {
    com.kaspersky.kaspresso.attempting.attempt(
        timeoutMs,
        attemptsFrequencyMs,
        allowedExceptions
    ) {
        action.invoke(this)
    }
}

fun <T : KBaseView<Any>> T.attempt(
    timeoutSec: Long,
    action: T.() -> Unit
) {
    com.kaspersky.kaspresso.attempting.attempt(
        TimeUnit.SECONDS.toMillis(timeoutSec)
    ) {
        action.invoke(this)
    }
}