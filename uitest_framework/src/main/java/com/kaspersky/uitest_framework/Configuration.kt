package com.kaspersky.uitest_framework

import android.support.test.espresso.NoMatchingViewException
import android.support.test.espresso.PerformException
import com.kaspersky.uitest_framework.interceptors.flakysafety.FlakySafeExecutingInterceptor
import com.kaspersky.uitest_framework.interceptors.logging.LoggingViewActionInterceptor
import com.kaspersky.uitest_framework.interceptors.logging.LoggingViewAssertionInterceptor
import com.kaspersky.uitest_framework.kakao.interceptors.InterceptorsHolder
import com.kaspersky.uitest_framework.logger.DefaultUiTestLogger
import com.kaspersky.uitest_framework.logger.UiTestLogger

/**
 * Created by egor.kurnikov on 01.03.2019
 */

object Configuration {

    val logger: UiTestLogger = DefaultUiTestLogger

    const val attemptsTimeoutMs: Long = 2_000L

    const val attemptsFrequencyMs: Long = 500L

    val allowedExceptionsForAttempt: Set<Class<out Throwable>> = setOf(
            PerformException::class.java,
            NoMatchingViewException::class.java,
            AssertionError::class.java,
            PerformException::class.java
    )

    init {
        InterceptorsHolder.viewActionInterceptors += LoggingViewActionInterceptor(logger)
        InterceptorsHolder.viewAssertionInterceptors += LoggingViewAssertionInterceptor(logger)
        InterceptorsHolder.executingInterceptor = FlakySafeExecutingInterceptor()
    }
}