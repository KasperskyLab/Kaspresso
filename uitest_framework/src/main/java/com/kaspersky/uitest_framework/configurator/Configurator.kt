package com.kaspersky.uitest_framework.configurator

import android.support.test.espresso.NoMatchingViewException
import android.support.test.espresso.PerformException
import com.agoda.kakao.configurator.KakaoConfigurator
import com.kaspersky.uitest_framework.delegates.DataInteractionDelegateImpl
import com.kaspersky.uitest_framework.delegates.ViewInteractionDelegateImpl
import com.kaspersky.uitest_framework.delegates.WebInteractionDelegateImpl
import com.kaspersky.uitest_framework.interceptors.*
import com.kaspersky.uitest_framework.interceptors.impl.flakysafety.FlakySafeExecutingInterceptor
import com.kaspersky.uitest_framework.interceptors.impl.logging.LoggingFailureInterceptor
import com.kaspersky.uitest_framework.interceptors.impl.logging.LoggingViewActionInterceptor
import com.kaspersky.uitest_framework.interceptors.impl.logging.LoggingViewAssertionInterceptor
import com.kaspersky.uitest_framework.logger.DefaultUiTestLogger
import com.kaspersky.uitest_framework.logger.UiTestLogger
import com.kaspersky.uitest_framework.server.DefaultAdbServer
import com.kaspersky.uitest_framework.server.ServerInterface

/**
 * Object that keeps all settings.
*/
object Configurator {

    /**
     * Just implementation of [UiTestLogger].
     */
    internal var logger: UiTestLogger = DefaultUiTestLogger

    /**
     * The timeout for all action attempts in milliseconds.
     */
    internal var attemptsTimeoutMs: Long = 2_000L

    /**
     * The frequency of action attempts in milliseconds.
     */
    internal var attemptsFrequencyMs: Long = 500L

    /**
     * Exceptions that doesn't stop attempts.
     */
    internal var allowedExceptionsForAttempt: MutableSet<Class<out Throwable>> = mutableSetOf(
        PerformException::class.java,
        NoMatchingViewException::class.java,
        AssertionError::class.java
    )

    /**
     * Interceptors that are called by [com.kaspersky.uitest_framework.proxy.ViewActionProxy] before actually
     * [android.support.test.espresso.ViewAction.perform] call.
     */
    internal var viewActionInterceptors: ArrayList<ViewActionInterceptor> = arrayListOf()

    /**
     * Interceptors that are called by [com.kaspersky.uitest_framework.proxy.ViewAssertionProxy] before actually
     * [android.support.test.espresso.ViewAssertion.check] call.
     */
    internal var viewAssertionInterceptors: ArrayList<ViewAssertionInterceptor> = arrayListOf()

    /**
     * Interceptors that are called by [com.kaspersky.uitest_framework.proxy.AtomProxy] before actually
     * [android.support.test.espresso.web.model.Atom.transform] call.
     */
    internal var atomInterceptors: ArrayList<AtomInterceptor> = arrayListOf()

    /**
     * Interceptors that are called by [android.support.test.espresso.web.assertion.WebAssertionProxy] before actually
     * [android.support.test.espresso.web.assertion.WebAssertion.checkResult] call.
     */
    internal var webAssertionInterceptors: ArrayList<WebAssertionInterceptor> = arrayListOf()

    /**
     * Interceptor that actually manages the execution of actions or assertions. For example,
     * [FlakySafeExecutingInterceptor] performs multiple attempting to execute an action or assertion.
     */
    internal var executingInterceptor: ExecutingInterceptor? = null

    /**
     * Interceptor that is called on failures. It's [FailureInterceptor.interceptAndThrow] method is being provided as a
     * [android.support.test.espresso.FailureHandler].
     */
    internal var failureInterceptor: FailureInterceptor? = null

    internal var serverInterface: ServerInterface = DefaultAdbServer

    /**
     * Class for [Configurator] initialization. The right way to change [Configurator] settings is to use [Builder].
     */
    class Builder {

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

        /**
         * Terminating method to commit built [Configurator] settings. Can be called only inside the framework package.
         * Actually called when the base [com.kaspersky.uitest_framework.testcase.TestCase] class is constructed.
         */
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

        companion object {

            fun default(): Builder = Builder().apply {
                viewActionInterceptors = arrayListOf(LoggingViewActionInterceptor(logger))
                viewAssertionInterceptors = arrayListOf(LoggingViewAssertionInterceptor(logger))
                executingInterceptor = FlakySafeExecutingInterceptor()
                failureInterceptor = LoggingFailureInterceptor(logger)
            }
        }

    }
}
