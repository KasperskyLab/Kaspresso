package com.kaspersky.uitest_framework.kakaoext

import com.kaspersky.uitest_framework.configuration.InterceptorConfigurator
import com.agoda.kakao.common.views.KBaseView
import java.util.concurrent.TimeUnit

fun <T : KBaseView<Any>> T.attempt(
    timeoutMs: Long = InterceptorConfigurator.attemptsTimeoutMs,
    attemptsFrequencyMs: Long = InterceptorConfigurator.attemptsFrequencyMs,
    allowedExceptions: Set<Class<out Throwable>> = InterceptorConfigurator.allowedExceptionsForAttempt,
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