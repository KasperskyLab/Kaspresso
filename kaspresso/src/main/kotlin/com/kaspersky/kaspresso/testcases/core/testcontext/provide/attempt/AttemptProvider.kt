package com.kaspersky.kaspresso.testcases.core.testcontext.provide.attempt

import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.ViewInteraction
import com.agoda.kakao.common.actions.BaseActions
import com.agoda.kakao.common.assertions.BaseAssertions
import com.agoda.kakao.intercept.Interceptable
import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.logger.UiTestLogger

interface AttemptProvider {

    fun <T, R> T.attempt(
        timeoutMs: Long = Configurator.attemptsTimeoutMs,
        intervalMs: Long = Configurator.attemptsIntervalMs,
        message: String? = null,
        logger: UiTestLogger = Configurator.logger,
        allowedExceptions: Set<Class<out Throwable>> = Configurator.allowedExceptionsForAttempt,
        action: T.() -> R
    ): R where T : BaseActions, T : BaseAssertions, T : Interceptable<ViewInteraction, ViewAssertion, ViewAction> {

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
}