package com.kaspersky.uitest_framework

import android.support.test.espresso.NoMatchingViewException
import android.support.test.espresso.PerformException
import com.kaspersky.uitest_framework.delegates.DataInteractionDelegateImpl
import com.kaspersky.uitest_framework.delegates.ViewInteractionDelegateImpl
import com.kaspersky.uitest_framework.delegates.WebInteractionDelegateImpl
import com.kaspersky.uitest_framework.interceptors.InterceptorsHolder
import com.kaspersky.uitest_framework.interceptors.flakysafety.FlakySafeExecutingInterceptor
import com.kaspersky.uitest_framework.interceptors.logging.LoggingFailureInterceptor
import com.kaspersky.uitest_framework.interceptors.logging.LoggingViewActionInterceptor
import com.kaspersky.uitest_framework.interceptors.logging.LoggingViewAssertionInterceptor
import com.kaspersky.uitest_framework.kakao.configuration.Configuration
import com.kaspersky.uitest_framework.logger.DefaultUiTestLogger
import com.kaspersky.uitest_framework.logger.UiTestLogger

object Configuration {

    val logger: UiTestLogger = DefaultUiTestLogger

    const val attemptsTimeoutMs: Long = 2_000L

    const val attemptsFrequencyMs: Long = 500L

    val allowedExceptionsForAttempt: Set<Class<out Throwable>> = setOf(
            PerformException::class.java,
            NoMatchingViewException::class.java,
            AssertionError::class.java
    )

    init {
        Configuration.viewInteractionDelegateFactory = { ViewInteractionDelegateImpl(it) }
        Configuration.dataInteractionDelegateFactory = { DataInteractionDelegateImpl(it) }
        Configuration.webInteractionDelegateFactory = { WebInteractionDelegateImpl(it) }

        InterceptorsHolder.viewActionInterceptors += LoggingViewActionInterceptor(logger)
        InterceptorsHolder.viewAssertionInterceptors += LoggingViewAssertionInterceptor(logger)
        InterceptorsHolder.executingInterceptor = FlakySafeExecutingInterceptor()
        InterceptorsHolder.failureInterceptor = LoggingFailureInterceptor(logger)
    }
}