package com.kaspersky.kaspresso.configurator

import android.support.test.espresso.Espresso
import android.support.test.espresso.NoMatchingViewException
import android.support.test.espresso.PerformException
import com.kaspersky.kaspresso.delegates.DataInteractionDelegateImpl
import com.kaspersky.kaspresso.delegates.ViewInteractionDelegateImpl
import com.kaspersky.kaspresso.delegates.WebInteractionDelegateImpl
import com.kaspersky.kaspresso.device.accessibility.Accessibility
import com.kaspersky.kaspresso.device.accessibility.AccessibilityImpl
import com.kaspersky.kaspresso.device.activities.Activities
import com.kaspersky.kaspresso.device.activities.ActivitiesImpl
import com.kaspersky.kaspresso.device.apps.Apps
import com.kaspersky.kaspresso.device.apps.AppsImpl
import com.kaspersky.kaspresso.device.exploit.Exploit
import com.kaspersky.kaspresso.device.exploit.ExploitImpl
import com.kaspersky.kaspresso.device.files.Files
import com.kaspersky.kaspresso.device.files.FilesImpl
import com.kaspersky.kaspresso.device.internet.Internet
import com.kaspersky.kaspresso.device.internet.InternetImpl
import com.kaspersky.kaspresso.device.permissions.Permissions
import com.kaspersky.kaspresso.device.permissions.PermissionsImpl
import com.kaspersky.kaspresso.device.screenshots.Screenshots
import com.kaspersky.kaspresso.device.screenshots.ScreenshotsImpl
import com.kaspersky.kaspresso.interceptors.*
import com.kaspersky.kaspresso.interceptors.impl.flakysafety.FlakySafeExecutingInterceptor
import com.kaspersky.kaspresso.interceptors.impl.logging.LoggingFailureInterceptor
import com.kaspersky.kaspresso.interceptors.impl.logging.LoggingViewActionInterceptor
import com.kaspersky.kaspresso.interceptors.impl.logging.LoggingViewAssertionInterceptor
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso.logger.UiTestLoggerImpl
import com.kaspersky.kaspresso.testcases.Scenario
import com.kaspersky.kaspresso.interceptors.impl.logging.LoggingStepInterceptor
import com.kaspersky.kaspresso.interceptors.impl.screenshot.ScreenshotStepInterceptor
import com.kaspersky.kaspresso.interceptors.StepInterceptor
import com.kaspersky.klkakao.configurator.KakaoConfigurator

/**
 * An object that keeps all settings.
 */
object Configurator {

    private const val DEFAULT_ATTEMPTS_TIMEOUT_MS: Long = 2_000L
    private const val DEFAULT_ATTEMPTS_INTERVAL_MS: Long = 500L

    private const val DEFAULT_INNER_LOGGER_TAG: String = "KASPRESSO"
    private const val DEFAULT_OUTER_LOGGER_TAG: String = "KASPRESSO_SPECIAL"

    /**
     * A timeout for all action attempts in milliseconds.
     */
    internal var attemptsTimeoutMs: Long = DEFAULT_ATTEMPTS_TIMEOUT_MS

    /**
     * A frequency of action attempts in milliseconds.
     */
    internal var attemptsIntervalMs: Long = DEFAULT_ATTEMPTS_INTERVAL_MS

    /**
     * Holds an implementation of [UiTestLogger] interface for inner framework usage. Not accessible from outside.
     */
    internal var logger: UiTestLogger = UiTestLoggerImpl(DEFAULT_INNER_LOGGER_TAG)

    /**
     * Holds an implementation of [UiTestLogger] interface for external usage.
     */
    internal var externalLogger: UiTestLogger = UiTestLoggerImpl(DEFAULT_OUTER_LOGGER_TAG)

    /**
     * Holds an implementation of [Apps] interface. If it was not specified in [Configurator.Builder], the default
     * implementation is used.
     */
    internal var apps: Apps = AppsImpl()

    /**
     * Holds an implementation of [Activities] interface. If it was not specified in [Configurator.Builder], the default
     * implementation is used.
     */
    internal var activities: Activities = ActivitiesImpl()

    /**
     * Holds an implementation of [Files] interface. If it was not specified in [Configurator.Builder], the default
     * implementation is used.
     */
    internal var files: Files = FilesImpl()

    /**
     * Holds an implementation of [Internet] interface. If it was not specified in [Configurator.Builder], the default
     * implementation is used.
     */
    internal var internet: Internet = InternetImpl()

    /**
     * Holds an implementation of [Screenshots] interface. If it was not specified in [Configurator.Builder], the
     * default implementation is used.
     */
    internal var screenshots: Screenshots = ScreenshotsImpl()

    /**
     * Holds an implementation of [Accessibility] interface. If it was not specified in [Configurator.Builder], the
     * default implementation is used.
     */
    internal var accessibility: Accessibility = AccessibilityImpl()

    /**
     * Holds an implementation of [Permissions] interface. If it was not specified in [Configurator.Builder], the
     * default implementation is used.
     */
    internal var permissions: Permissions = PermissionsImpl()

    /**
     * Holds an implementation of [Exploit] interface. If it was not specified in [Configurator.Builder], the default
     * implementation is used.
     */
    internal var exploit: Exploit = ExploitImpl()

    /**
     * Exceptions that doesn't stop attempts.
     */
    internal var allowedExceptionsForAttempt: Set<Class<out Throwable>> = setOf(
        PerformException::class.java,
        NoMatchingViewException::class.java,
        AssertionError::class.java
    )

    /**
     * Interceptors that are called by [com.kaspersky.uitest_framework.proxy.ViewActionProxy] before actually
     * [android.support.test.espresso.ViewAction.perform] call.
     */
    internal var viewActionInterceptors: List<ViewActionInterceptor> = emptyList()

    /**
     * Interceptors that are called by [com.kaspersky.uitest_framework.proxy.ViewAssertionProxy] before actually
     * [android.support.test.espresso.ViewAssertion.check] call.
     */
    internal var viewAssertionInterceptors: List<ViewAssertionInterceptor> = emptyList()

    /**
     * Interceptors that are called by [com.kaspersky.uitest_framework.proxy.AtomProxy] before actually
     * [android.support.test.espresso.web.model.Atom.transform] call.
     */
    internal var atomInterceptors: List<AtomInterceptor> = emptyList()

    /**
     * Interceptors that are called by [android.support.test.espresso.web.assertion.WebAssertionProxy] before actually
     * [android.support.test.espresso.web.assertion.WebAssertion.checkResult] call.
     */
    internal var webAssertionInterceptors: List<WebAssertionInterceptor> = emptyList()

    /**
     * An interceptor that actually manages the execution of actions or assertions. For example,
     * [FlakySafeExecutingInterceptor] performs multiple attempting to execute an action or assertion.
     */
    internal var executingInterceptor: ExecutingInterceptor? = null

    /**
     * An interceptors set that actually manages the execution of steps [Scenario.step]. Interceptors works using
     * decorator pattern. First interceptor wraps others
     */
    internal var stepInterceptors: List<StepInterceptor> = emptyList()

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
                    viewActionInterceptors = listOf(LoggingViewActionInterceptor(logger))
                    viewAssertionInterceptors = listOf(LoggingViewAssertionInterceptor(logger))
                    executingInterceptor = FlakySafeExecutingInterceptor()
                    failureInterceptor = LoggingFailureInterceptor(logger)
                    stepInterceptors = listOf(
                        LoggingStepInterceptor(logger),
                        ScreenshotStepInterceptor(screenshots)
                    )
                }
            }
        }

        var attemptsTimeoutMs: Long = DEFAULT_ATTEMPTS_TIMEOUT_MS
        var attemptsIntervalMs: Long = DEFAULT_ATTEMPTS_INTERVAL_MS

        var logger: UiTestLogger = UiTestLoggerImpl(DEFAULT_INNER_LOGGER_TAG)
        var externalLogger: UiTestLogger = UiTestLoggerImpl(DEFAULT_OUTER_LOGGER_TAG)

        var apps: Apps = AppsImpl()
        var activities: Activities = ActivitiesImpl()
        var files: Files = FilesImpl()
        var internet: Internet = InternetImpl()
        var screenshots: Screenshots = ScreenshotsImpl()
        var accessibility: Accessibility = AccessibilityImpl()
        var permissions: Permissions = PermissionsImpl()
        var exploit: Exploit = ExploitImpl()

        var allowedExceptionsForAttempt: Set<Class<out Throwable>> = setOf(
            PerformException::class.java,
            NoMatchingViewException::class.java,
            AssertionError::class.java
        )

        var viewActionInterceptors: List<ViewActionInterceptor> = emptyList()
        var viewAssertionInterceptors: List<ViewAssertionInterceptor> = emptyList()
        var atomInterceptors: List<AtomInterceptor> = emptyList()
        var webAssertionInterceptors: List<WebAssertionInterceptor> = emptyList()

        var executingInterceptor: ExecutingInterceptor? = null

        /**
         * An interceptor that is called on failures. It's [FailureInterceptor.interceptAndThrow] method is being
         * provide as the default [android.support.test.espresso.FailureHandler].
         */
        var failureInterceptor: FailureInterceptor? = null

        var stepInterceptors: List<StepInterceptor> = emptyList()
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
            Configurator.attemptsIntervalMs = attemptsIntervalMs

            Configurator.logger = logger
            Configurator.externalLogger = externalLogger

            Configurator.apps = apps
            Configurator.activities = activities
            Configurator.files = files
            Configurator.internet = internet
            Configurator.screenshots = screenshots
            Configurator.accessibility = accessibility
            Configurator.permissions = permissions
            Configurator.exploit = exploit

            Configurator.allowedExceptionsForAttempt = allowedExceptionsForAttempt

            Configurator.viewActionInterceptors = viewActionInterceptors
            Configurator.viewAssertionInterceptors = viewAssertionInterceptors
            Configurator.atomInterceptors = atomInterceptors
            Configurator.webAssertionInterceptors = webAssertionInterceptors

            Configurator.executingInterceptor = executingInterceptor

            failureInterceptor?.let { Espresso.setFailureHandler(it::interceptAndThrow) }

            Configurator.stepInterceptors = stepInterceptors
        }
    }
}