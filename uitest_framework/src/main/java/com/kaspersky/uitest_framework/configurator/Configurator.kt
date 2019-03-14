package com.kaspersky.uitest_framework.configurator

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
import com.agoda.kakao.configurator.KakaoConfigurator
import com.kaspersky.uitest_framework.logger.DefaultUiTestLogger
import com.kaspersky.uitest_framework.logger.UiTestLogger
import java.lang.IllegalArgumentException

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

    class Builder {

        private var logger: UiTestLogger = DefaultUiTestLogger

        private var attemptsTimeoutMs: Long = 2_000L

        private var attemptsFrequencyMs: Long = 500L

        private var allowedExceptionsForAttempt: MutableSet<Class<out Throwable>> = mutableSetOf(
                PerformException::class.java,
                NoMatchingViewException::class.java,
                AssertionError::class.java
        )

        private var viewActionInterceptors: ArrayList<ViewActionInterceptor> = arrayListOf()

        private var viewAssertionInterceptors: ArrayList<ViewAssertionInterceptor> = arrayListOf()

        private var atomInterceptors: ArrayList<AtomInterceptor> = arrayListOf()

        private var webAssertionInterceptors: ArrayList<WebAssertionInterceptor> = arrayListOf()

        private var executingInterceptor: ExecutingInterceptor? = null

        private var failureInterceptor: FailureInterceptor? = null

        fun setLogger(logger: UiTestLogger): Builder {
            this.logger = logger
            return this
        }

        fun setAttemptsTimeout(timeoutMs: Long): Builder {
            attemptsTimeoutMs = timeoutMs
            return this
        }

        fun setAttemptsFrequency(frequencyMs: Long): Builder {
            attemptsFrequencyMs = frequencyMs
            return this
        }

        fun addAllowedExceptionForAttempt(
                exception: Class<out Throwable>
        ): Builder {
            allowedExceptionsForAttempt.add(exception)
            return this
        }

        fun addViewActionInterceptor(
                viewActionInterceptor: ViewActionInterceptor
        ): Builder {
            viewActionInterceptors.add(viewActionInterceptor)
            return this
        }

        fun addViewAssertionInterceptor(
                viewAssertionInterceptor: ViewAssertionInterceptor
        ): Builder {
            viewAssertionInterceptors.add(viewAssertionInterceptor)
            return this
        }

        fun addAtomInterceptor(
                atomInterceptor: AtomInterceptor
        ): Builder {
            atomInterceptors.add(atomInterceptor)
            return this
        }

        fun addWebAssertionInterceptor(
                webAssertionInterceptor: WebAssertionInterceptor
        ): Builder {
            webAssertionInterceptors.add(webAssertionInterceptor)
            return this
        }

        fun setExecutingInterceptor(
                executingInterceptor: ExecutingInterceptor
        ): Builder {
            this.executingInterceptor = executingInterceptor
            return this
        }

        fun setFailureInterceptor(
                failureInterceptor: FailureInterceptor
        ): Builder {
            this.failureInterceptor = failureInterceptor
            return this
        }

        fun default(): Builder {

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

            return this
        }

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
        }
    }
}