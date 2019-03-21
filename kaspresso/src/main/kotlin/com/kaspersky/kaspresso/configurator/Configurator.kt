package com.kaspersky.kaspresso.configurator

import android.support.test.espresso.NoMatchingViewException
import android.support.test.espresso.PerformException
import com.kaspersky.kaspresso.delegates.DataInteractionDelegateImpl
import com.kaspersky.kaspresso.delegates.ViewInteractionDelegateImpl
import com.kaspersky.kaspresso.delegates.WebInteractionDelegateImpl
import com.kaspersky.kaspresso.device.activities.ActivitiesManager
import com.kaspersky.kaspresso.device.activities.ActivitiesManagerImpl
import com.kaspersky.kaspresso.device.files.FilesManager
import com.kaspersky.kaspresso.device.files.FilesManagerImpl
import com.kaspersky.kaspresso.device.internet.InternetManager
import com.kaspersky.kaspresso.device.internet.InternetManagerImpl
import com.kaspersky.kaspresso.device.apps.AppsManager
import com.kaspersky.kaspresso.device.apps.AppsManagerImpl
import com.kaspersky.kaspresso.device.screenshots.ScreenshotsManager
import com.kaspersky.kaspresso.device.screenshots.ScreenshotsManagerImpl
import com.kaspersky.kaspresso.interceptors.*
import com.kaspersky.kaspresso.interceptors.impl.flakysafety.FlakySafeExecutingInterceptor
import com.kaspersky.kaspresso.interceptors.impl.logging.LoggingFailureInterceptor
import com.kaspersky.kaspresso.interceptors.impl.logging.LoggingViewActionInterceptor
import com.kaspersky.kaspresso.interceptors.impl.logging.LoggingViewAssertionInterceptor
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso.device.server.AdbServerImpl
import com.kaspersky.kaspresso.device.server.AdbServer
import com.kaspersky.kaspresso.logger.UiTestLoggerImpl
import com.kaspersky.klkakao.configurator.KakaoConfigurator

/**
 * An object that keeps all settings.
 */
object Configurator {

    private const val DEFAULT_ATTEMPTS_TIMEOUT_MS: Long = 2_000L
    private const val DEFAULT_ATTEMPTS_FREQUENCY_MS: Long = 500L

    private const val DEFAULT_INNER_LOGGER_TAG: String = "UI_TESTING"
    private const val DEFAULT_OUTER_LOGGER_TAG: String = "UI_TESTING_SPECIAL"

    /**
     * A timeout for all action attempts in milliseconds.
     */
    internal var attemptsTimeoutMs: Long = DEFAULT_ATTEMPTS_TIMEOUT_MS

    /**
     * A frequency of action attempts in milliseconds.
     */
    internal var attemptsFrequencyMs: Long = DEFAULT_ATTEMPTS_FREQUENCY_MS

    /**
     * An implementation of [UiTestLogger] for inner framework usage. Not accessible from outside.
     */
    internal var logger: UiTestLogger = UiTestLoggerImpl(DEFAULT_INNER_LOGGER_TAG)

    /**
     * An implementation of [UiTestLogger] for external usage.
     */
    internal var externalLogger: UiTestLogger = UiTestLoggerImpl(DEFAULT_OUTER_LOGGER_TAG)

    /**
     * An interface of applications manager.
     */
    internal var appsManager: AppsManager = AppsManagerImpl

    /**
     * An interface of activities manager.
     */
    internal var activitiesManager: ActivitiesManager = ActivitiesManagerImpl

    /**
     * An interface of files manager.
     */
    internal var filesManager: FilesManager = FilesManagerImpl

    /**
     * An interface of internet manager.
     */
    internal var internetManager: InternetManager = InternetManagerImpl

    /**
     * An interface of screenshots manager.
     */
    internal var screenshotsManager: ScreenshotsManager = ScreenshotsManagerImpl

    /**
     * An interface of adb server.
     */
    internal var adbServer: AdbServer = AdbServerImpl

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
     * An interceptor that actually manages the execution of actions or assertions. For example,
     * [FlakySafeExecutingInterceptor] performs multiple attempting to execute an action or assertion.
     */
    internal var executingInterceptor: ExecutingInterceptor? = null

    /**
     * An interceptor that is called on failures. It's [FailureInterceptor.interceptAndThrow] method is being provided
     * as a [android.support.test.espresso.FailureHandler].
     */
    internal var failureInterceptor: FailureInterceptor? = null

    /**
     * A class for [Configurator] initialization. The right way to change [Configurator] settings is to use [Builder].
     */
    class Builder {

        companion object {

            /**
             * Puts the default settings pack to [Builder].
             *
             * @return an existing instance of [Builder].
             */
            fun default(): Builder {
                return Builder().apply {
                    viewActionInterceptors = arrayListOf(LoggingViewActionInterceptor(logger))
                    viewAssertionInterceptors = arrayListOf(LoggingViewAssertionInterceptor(logger))
                    executingInterceptor = FlakySafeExecutingInterceptor()
                    failureInterceptor = LoggingFailureInterceptor(logger)
                }
            }
        }

        var attemptsTimeoutMs: Long = DEFAULT_ATTEMPTS_TIMEOUT_MS
        var attemptsFrequencyMs: Long = DEFAULT_ATTEMPTS_FREQUENCY_MS

        var logger: UiTestLogger = UiTestLoggerImpl(DEFAULT_INNER_LOGGER_TAG)
        var externalLogger: UiTestLogger = UiTestLoggerImpl(DEFAULT_OUTER_LOGGER_TAG)

        var appsManager: AppsManager = AppsManagerImpl
        var activitiesManager: ActivitiesManager = ActivitiesManagerImpl
        var filesManager: FilesManager = FilesManagerImpl
        var internetManager: InternetManager = InternetManagerImpl
        var screenshotsManager: ScreenshotsManager = ScreenshotsManagerImpl

        var adbServer: AdbServer = AdbServerImpl

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

        /**
         * Terminating method to commit built [Configurator] settings. Can be called only inside the framework
         * package. Actually called when the base [com.kaspersky.uitest_framework.testcase.TestCase] class is
         * constructed.
         */
        @Throws(IllegalArgumentException::class)
        internal fun commit() {
            with(KakaoConfigurator) {
                initViewInteractionDelegateFactory { ViewInteractionDelegateImpl(it) }
                initDataInteractionDelegateFactory { DataInteractionDelegateImpl(it) }
                initWebInteractionDelegateFactory { WebInteractionDelegateImpl(it) }
            }

            Configurator.attemptsTimeoutMs = attemptsTimeoutMs
            Configurator.attemptsFrequencyMs = attemptsFrequencyMs

            Configurator.logger = logger
            Configurator.externalLogger = externalLogger

            Configurator.appsManager = appsManager
            Configurator.activitiesManager = activitiesManager
            Configurator.filesManager = filesManager
            Configurator.internetManager = internetManager
            Configurator.screenshotsManager = screenshotsManager

            Configurator.adbServer = adbServer

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