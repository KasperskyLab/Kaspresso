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

object Configurator {

    private const val DEFAULT_ATTEMPTS_TIMEOUT_MS: Long = 2_000L
    private const val DEFAULT_ATTEMPTS_FREQUENCY_MS: Long = 500L

    private const val DEFAULT_INNER_LOGGER_TAG: String = "UI_TESTING"
    private const val DEFAULT_OUTER_LOGGER_TAG: String = "UI_TESTING_SPECIAL"

    internal var attemptsTimeoutMs: Long = DEFAULT_ATTEMPTS_TIMEOUT_MS
    internal var attemptsFrequencyMs: Long = DEFAULT_ATTEMPTS_FREQUENCY_MS

    internal var logger: UiTestLogger = UiTestLoggerImpl(DEFAULT_INNER_LOGGER_TAG)
    internal var outerLogger: UiTestLogger = UiTestLoggerImpl(DEFAULT_OUTER_LOGGER_TAG)

    internal var appsManager: AppsManager = AppsManagerImpl
    internal var activitiesManager: ActivitiesManager = ActivitiesManagerImpl
    internal var filesManager: FilesManager = FilesManagerImpl
    internal var internetManager: InternetManager = InternetManagerImpl
    internal var screenshotsManager: ScreenshotsManager = ScreenshotsManagerImpl

    internal var adbServer: AdbServer = AdbServerImpl

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

        var attemptsTimeoutMs: Long = DEFAULT_ATTEMPTS_TIMEOUT_MS
        var attemptsFrequencyMs: Long = DEFAULT_ATTEMPTS_FREQUENCY_MS

        var logger: UiTestLogger = UiTestLoggerImpl(DEFAULT_INNER_LOGGER_TAG)
        var outerLogger: UiTestLogger = UiTestLoggerImpl(DEFAULT_OUTER_LOGGER_TAG)

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
            Configurator.outerLogger = outerLogger

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