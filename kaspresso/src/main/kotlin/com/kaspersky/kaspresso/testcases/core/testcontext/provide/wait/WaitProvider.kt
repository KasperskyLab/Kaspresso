package com.kaspersky.kaspresso.testcases.core.testcontext.provide.wait

import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.ViewInteraction
import com.agoda.kakao.common.actions.BaseActions
import com.agoda.kakao.common.assertions.BaseAssertions
import com.agoda.kakao.intercept.Interceptable
import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.logger.UiTestLogger

interface WaitProvider {

    fun <T, R> T.wait(
        timeoutMs: Long = Configurator.attemptsTimeoutMs,
        logger: UiTestLogger = Configurator.logger,
        action: T.() -> R
    ): R where T : BaseActions, T : BaseAssertions, T : Interceptable<ViewInteraction, ViewAssertion, ViewAction> {

        return com.kaspersky.kaspresso.flakysafety.wait(
            timeoutMs = timeoutMs,
            logger = logger
        ) {
            action.invoke(this)
        }
    }
}