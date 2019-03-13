package com.kaspersky.uitest_framework.configuration

import android.support.test.espresso.NoMatchingViewException
import android.support.test.espresso.PerformException
import com.kaspersky.uitest_framework.delegates.DataInteractionDelegateImpl
import com.kaspersky.uitest_framework.delegates.ViewInteractionDelegateImpl
import com.kaspersky.uitest_framework.delegates.WebInteractionDelegateImpl
import com.kaspersky.uitest_framework.interceptors.*
import com.kaspersky.uitest_framework.interceptors.impl.flakysafety.FlakySafeExecutingInterceptor
import com.kaspersky.uitest_framework.interceptors.impl.logging.LoggingFailureInterceptor
import com.kaspersky.uitest_framework.interceptors.impl.logging.LoggingViewActionInterceptor
import com.kaspersky.uitest_framework.interceptors.impl.logging.LoggingViewAssertionInterceptor
import com.agoda.kakao.configuration.Configurator
import com.kaspersky.uitest_framework.logger.DefaultUiTestLogger
import com.kaspersky.uitest_framework.logger.UiTestLogger

object InterceptorConfigurator {

    internal var logger: UiTestLogger = DefaultUiTestLogger
        private set

    internal var attemptsTimeoutMs: Long = 2_000L
        private set

    internal var attemptsFrequencyMs: Long = 500L
        private set

    internal var allowedExceptionsForAttempt: MutableSet<Class<out Throwable>> = mutableSetOf(
            PerformException::class.java,
            NoMatchingViewException::class.java,
            AssertionError::class.java
    )
        private set

    internal var viewActionInterceptors: ArrayList<ViewActionInterceptor> = arrayListOf()
        private set

    internal var viewAssertionInterceptors: ArrayList<ViewAssertionInterceptor> = arrayListOf()
        private set

    internal var atomInterceptors: ArrayList<AtomInterceptor> = arrayListOf()
        private set

    internal var webAssertionInterceptors: ArrayList<WebAssertionInterceptor> = arrayListOf()
        private set

    internal var executingInterceptor: ExecutingInterceptor? = null
        private set

    internal var failureInterceptor: FailureInterceptor? = null
        private set

    fun setupConfigs(settings: Settings) {
        settings.commit()
    }

    class Settings {

        var logger: UiTestLogger = InterceptorConfigurator.logger

        var attemptsTimeoutMs: Long = InterceptorConfigurator.attemptsTimeoutMs

        var attemptsFrequencyMs: Long = InterceptorConfigurator.attemptsFrequencyMs

        var allowedExceptionsForAttempt: MutableSet<Class<out Throwable>> = InterceptorConfigurator.allowedExceptionsForAttempt

        var viewActionInterceptors: ArrayList<ViewActionInterceptor> = InterceptorConfigurator.viewActionInterceptors

        var viewAssertionInterceptors: ArrayList<ViewAssertionInterceptor> = InterceptorConfigurator.viewAssertionInterceptors

        var atomInterceptors: ArrayList<AtomInterceptor> = InterceptorConfigurator.atomInterceptors

        var webAssertionInterceptors: ArrayList<WebAssertionInterceptor> = InterceptorConfigurator.webAssertionInterceptors

        var executingInterceptor: ExecutingInterceptor? = InterceptorConfigurator.executingInterceptor

        var failureInterceptor: FailureInterceptor? = InterceptorConfigurator.failureInterceptor

        fun setupFromCurrent(block: (Settings.() -> Unit)? = null): Settings {
            block?.let { it() }
            return this
        }

        fun setupFromDefault(block: (Settings.() -> Unit)? = null): Settings {
            logger = DefaultUiTestLogger
            attemptsTimeoutMs = 2_000L
            attemptsFrequencyMs = 500L
            allowedExceptionsForAttempt = mutableSetOf(
                PerformException::class.java,
                NoMatchingViewException::class.java,
                AssertionError::class.java
            )
            viewActionInterceptors = arrayListOf(LoggingViewActionInterceptor(logger))
            viewAssertionInterceptors = arrayListOf(LoggingViewAssertionInterceptor(logger))
            atomInterceptors = arrayListOf()
            webAssertionInterceptors = arrayListOf()
            executingInterceptor = FlakySafeExecutingInterceptor()
            failureInterceptor = LoggingFailureInterceptor(logger)

            block?.let { it() }

            return this
        }

        fun setupFromZero(block: (Settings.() -> Unit)? = null): Settings {
            logger = DefaultUiTestLogger
            attemptsTimeoutMs = 2_000L
            attemptsFrequencyMs = 500L
            allowedExceptionsForAttempt = mutableSetOf(
                PerformException::class.java,
                NoMatchingViewException::class.java,
                AssertionError::class.java
            )
            viewActionInterceptors = arrayListOf()
            viewAssertionInterceptors = arrayListOf()
            atomInterceptors = arrayListOf()
            webAssertionInterceptors = arrayListOf()
            executingInterceptor = null
            failureInterceptor = null

            block?.let { it() }

            return this
        }

        internal fun commit() {
            Configurator.setupConfiguration(
                viewInteractionDelegateFactory = { ViewInteractionDelegateImpl(it) },
                dataInteractionDelegateFactory = { DataInteractionDelegateImpl(it) },
                webInteractionDelegateFactory = { WebInteractionDelegateImpl(it) }
            )

            InterceptorConfigurator.logger = logger
            InterceptorConfigurator.attemptsTimeoutMs = attemptsTimeoutMs
            InterceptorConfigurator.attemptsTimeoutMs = attemptsFrequencyMs
            InterceptorConfigurator.viewActionInterceptors = viewActionInterceptors
            InterceptorConfigurator.viewAssertionInterceptors = viewAssertionInterceptors
            InterceptorConfigurator.atomInterceptors = atomInterceptors
            InterceptorConfigurator.webAssertionInterceptors = webAssertionInterceptors
            InterceptorConfigurator.executingInterceptor = executingInterceptor
            InterceptorConfigurator.failureInterceptor = failureInterceptor
        }

    }
}