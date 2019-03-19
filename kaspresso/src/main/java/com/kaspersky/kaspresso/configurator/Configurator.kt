package com.kaspersky.kaspresso.configurator

import android.support.test.espresso.NoMatchingViewException
import android.support.test.espresso.PerformException
import com.agoda.kakao.configurator.KakaoConfigurator
import com.kaspersky.kaspresso.delegates.DataInteractionDelegateImpl
import com.kaspersky.kaspresso.delegates.ViewInteractionDelegateImpl
import com.kaspersky.kaspresso.delegates.WebInteractionDelegateImpl
import com.kaspersky.kaspresso.interceptors.*
import com.kaspersky.kaspresso.interceptors.impl.flakysafety.FlakySafeExecutingInterceptor
import com.kaspersky.kaspresso.interceptors.impl.logging.LoggingFailureInterceptor
import com.kaspersky.kaspresso.interceptors.impl.logging.LoggingViewActionInterceptor
import com.kaspersky.kaspresso.interceptors.impl.logging.LoggingViewAssertionInterceptor
import com.kaspersky.kaspresso.logger.DefaultUiTestLogger
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso.server.DefaultAdbServer
import com.kaspersky.kaspresso.server.ServerInterface

object Configurator {

    internal var logger: UiTestLogger = DefaultUiTestLogger

    internal var attemptsTimeoutMs: Long = 2_000L

    internal var attemptsFrequencyMs: Long = 500L

    internal var allowedExceptionsForAttempt: MutableSet<Class<out Throwable>> = mutableSetOf(
        PerformException::class.java,
        NoMatchingViewException::class.java,
        AssertionError::class.java
    )

    internal var viewActionInterceptors: ArrayList<ViewActionInterceptor> = arrayListOf()

    internal var viewAssertionInterceptors: ArrayList<ViewAssertionInterceptor> = arrayListOf()

    internal var atomInterceptors: ArrayList<AtomInterceptor> = arrayListOf()

    internal var webAssertionInterceptors: ArrayList<WebAssertionInterceptor> = arrayListOf()

    internal var executingInterceptor: ExecutingInterceptor? = null

    internal var failureInterceptor: FailureInterceptor? = null

    internal var serverInterface: ServerInterface = DefaultAdbServer

    class Builder {

        companion object {

            fun default(): Builder {
                return Builder().apply {
                    viewActionInterceptors = arrayListOf(LoggingViewActionInterceptor(logger))
                    viewAssertionInterceptors = arrayListOf(LoggingViewAssertionInterceptor(logger))
                    executingInterceptor = FlakySafeExecutingInterceptor()
                    failureInterceptor = LoggingFailureInterceptor(logger)
                }
            }
        }

        var logger: UiTestLogger = DefaultUiTestLogger

        var attemptsTimeoutMs: Long = 2_000L

        var attemptsFrequencyMs: Long = 500L

        var allowedExceptionsForAttempt: MutableSet<Class<out Throwable>> = mutableSetOf(
            PerformException::class.java,
            NoMatchingViewException::class.java,
            AssertionError::class.java
        )

        var viewActionInterceptors: ArrayList<ViewActionInterceptor> = arrayListOf()

        var viewAssertionInterceptors: ArrayList<ViewAssertionInterceptor> = arrayListOf()

        var atomInterceptors: ArrayList<AtomInterceptor> = arrayListOf()

        var webAssertionInterceptors: ArrayList<WebAssertionInterceptor> = arrayListOf()

        var executingInterceptor: ExecutingInterceptor? = null

        var failureInterceptor: FailureInterceptor? = null

        var serverInterface: ServerInterface = DefaultAdbServer

        @Throws(IllegalArgumentException::class)
        internal fun commit() {
            with(KakaoConfigurator) {
                initViewInteractionDelegateFactory { ViewInteractionDelegateImpl(it) }
                initDataInteractionDelegateFactory { DataInteractionDelegateImpl(it) }
                initWebInteractionDelegateFactory { WebInteractionDelegateImpl(it) }
            }

            Configurator.logger = logger
            Configurator.attemptsTimeoutMs = attemptsTimeoutMs
            Configurator.attemptsFrequencyMs = attemptsFrequencyMs

            Configurator.allowedExceptionsForAttempt = allowedExceptionsForAttempt

            Configurator.viewActionInterceptors = viewActionInterceptors
            Configurator.viewAssertionInterceptors = viewAssertionInterceptors
            Configurator.atomInterceptors = atomInterceptors
            Configurator.webAssertionInterceptors = webAssertionInterceptors

            Configurator.executingInterceptor = executingInterceptor
            Configurator.failureInterceptor = failureInterceptor

            Configurator.serverInterface = serverInterface
        }
    }
}