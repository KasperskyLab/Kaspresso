package com.kaspersky.uitest_framework.kakaoext

import com.kaspersky.uitest_framework.Configuration
import com.kaspersky.uitest_framework.kakao.common.views.KBaseView
import java.util.concurrent.TimeUnit

fun <T : KBaseView<Any>> T.attempt(
        timeoutMs: Long = Configuration.attemptsTimeoutMs,
        attemptsFrequencyMs: Long = Configuration.attemptsFrequencyMs,
        allowedExceptions: Set<Class<out Throwable>> = Configuration.allowedExceptionsForAttempt,
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