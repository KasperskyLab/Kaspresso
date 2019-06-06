package com.kaspersky.kaspresso.configurator

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso
import android.support.test.espresso.NoMatchingViewException
import android.support.test.espresso.PerformException
import android.support.test.uiautomator.UiDevice
import com.kaspersky.kaspresso.delegates.DataInteractionDelegateKaspressoImpl
import com.kaspersky.kaspresso.delegates.ViewInteractionDelegateKaspressoImpl
import com.kaspersky.kaspresso.delegates.WebInteractionDelegateKaspressoImpl
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
import com.kaspersky.kaspresso.interceptors.impl.logging.*
import com.kaspersky.kaspresso.interceptors.impl.report.BuildStepReportInterceptor
import com.kaspersky.kaspresso.interceptors.impl.screenshot.ScreenshotStepInterceptor
import com.kaspersky.kaspresso.interceptors.impl.screenshot.TestRunnerScreenshotInterceptor
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso.logger.UiTestLoggerImpl
import com.kaspersky.kaspresso.report.impl.AllureReportWriter
import com.kaspersky.kaspresso.testcases.core.TestContext
import com.kaspersky.klkakao.configurator.KakaoConfigurator

/**
 * An object that keeps all settings.
 */
class Configurator {

    companion object {
        internal const val DEFAULT_ATTEMPTS_TIMEOUT_MS: Long = 2_000L
        internal const val DEFAULT_ATTEMPTS_INTERVAL_MS: Long = 500L

        internal const val DEFAULT_INNER_LOGGER_TAG: String = "KASPRESSO"
        internal const val DEFAULT_OUTER_LOGGER_TAG: String = "KASPRESSO_SPECIAL"
    }

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
    internal lateinit var logger: UiTestLogger

    /**
     * Holds an implementation of [UiTestLogger] interface for external usage.
     */
    internal lateinit var externalLogger: UiTestLogger

    /**
     * Holds an implementation of [Apps] interface. If it was not specified in [Configurator.Builder], the default
     * implementation is used.
     */
    internal lateinit var apps: Apps

    /**
     * Holds an implementation of [Activities] interface. If it was not specified in [Configurator.Builder], the default
     * implementation is used.
     */
    internal lateinit var activities: Activities

    /**
     * Holds an implementation of [Files] interface. If it was not specified in [Configurator.Builder], the default
     * implementation is used.
     */
    internal lateinit var files: Files

    /**
     * Holds an implementation of [Internet] interface. If it was not specified in [Configurator.Builder], the default
     * implementation is used.
     */
    internal lateinit var internet: Internet

    /**
     * Holds an implementation of [Screenshots] interface. If it was not specified in [Configurator.Builder], the
     * default implementation is used.
     */
    internal lateinit var screenshots: Screenshots

    /**
     * Holds an implementation of [Accessibility] interface. If it was not specified in [Configurator.Builder], the
     * default implementation is used.
     */
    internal lateinit var accessibility: Accessibility

    /**
     * Holds an implementation of [Permissions] interface. If it was not specified in [Configurator.Builder], the
     * default implementation is used.
     */
    internal lateinit var permissions: Permissions

    /**
     * Holds an implementation of [Exploit] interface. If it was not specified in [Configurator.Builder], the default
     * implementation is used.
     */
    internal lateinit var exploit: Exploit

    /**
     * Exceptions that doesn't stop attempts.
     */
    internal lateinit var allowedExceptionsForAttempt: Set<Class<out Throwable>>

    /**
     * Interceptors that are called by [com.kaspersky.uitest_framework.proxy.ViewActionProxy] before actually
     * [android.support.test.espresso.ViewAction.perform] call.
     */
    internal lateinit var viewActionInterceptors: List<ViewActionInterceptor>

    /**
     * Interceptors that are called by [com.kaspersky.uitest_framework.proxy.ViewAssertionProxy] before actually
     * [android.support.test.espresso.ViewAssertion.check] call.
     */
    internal lateinit var viewAssertionInterceptors: List<ViewAssertionInterceptor>

    /**
     * Interceptors that are called by [com.kaspersky.uitest_framework.proxy.AtomProxy] before actually
     * [android.support.test.espresso.web.model.Atom.transform] call.
     */
    internal lateinit var atomInterceptors: List<AtomInterceptor>

    /**
     * Interceptors that are called by [android.support.test.espresso.web.assertion.WebAssertionProxy] before actually
     * [android.support.test.espresso.web.assertion.WebAssertion.checkResult] call.
     */
    internal lateinit var webAssertionInterceptors: List<WebAssertionInterceptor>

    /**
     * An interceptor that actually manages the execution of actions or assertions. For example,
     * [FlakySafeExecutingInterceptor] performs multiple attempting to execute an action or assertion.
     */
    internal var executingInterceptor: ExecutingInterceptor? = null

    /**
     * An interceptors set that actually manages the execution of steps [TestContext.step]. Interceptors works using
     * decorator pattern. First interceptor wraps others
     */
    internal lateinit var stepInterceptors: List<StepInterceptor>

    /**
     * An interceptors set that actually manages the execution of test sections [TestInfo]. Interceptors works using
     * decorator pattern. First interceptor wraps others
     */
    internal lateinit var testRunInterceptors: List<TestRunInterceptor>

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
                    testRunInterceptors = listOf(
                        TestRunLoggerInterceptor(logger),
                        TestRunnerScreenshotInterceptor(screenshots),
                        BuildStepReportInterceptor(AllureReportWriter(logger))
                    )
                }
            }
        }

        var attemptsTimeoutMs: Long = DEFAULT_ATTEMPTS_TIMEOUT_MS
        var attemptsIntervalMs: Long = DEFAULT_ATTEMPTS_INTERVAL_MS

        var logger: UiTestLogger = UiTestLoggerImpl(DEFAULT_INNER_LOGGER_TAG)
        var externalLogger: UiTestLogger = UiTestLoggerImpl(DEFAULT_OUTER_LOGGER_TAG)

        var apps: Apps = AppsImpl(logger)
        var activities: Activities = ActivitiesImpl(logger)
        var files: Files = FilesImpl()
        var internet: Internet = InternetImpl()
        var screenshots: Screenshots = ScreenshotsImpl(logger, activities)
        var accessibility: Accessibility = AccessibilityImpl()
        var permissions: Permissions = PermissionsImpl(logger, UiDevice.getInstance(InstrumentationRegistry.getInstrumentation()))
        var exploit: Exploit = ExploitImpl(activities, UiDevice.getInstance(InstrumentationRegistry.getInstrumentation()))

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

        var testRunInterceptors: List<TestRunInterceptor> = emptyList()
        /**
         * Terminating method to commit built [Configurator] settings. Can be called only inside the framework
         * package. Actually called when the base [com.kaspersky.uitest_framework.testcase.TestCase] class is
         * constructed.
         */
        @Throws(IllegalArgumentException::class)
        internal fun commit(): Configurator {
            val configurator = Configurator()

            configurator.attemptsTimeoutMs = attemptsTimeoutMs
            configurator.attemptsIntervalMs = attemptsIntervalMs

            configurator.logger = logger
            configurator.externalLogger = externalLogger

            configurator.apps = apps
            configurator.activities = activities
            configurator.files = files
            configurator.internet = internet
            configurator.screenshots = screenshots
            configurator.accessibility = accessibility
            configurator.permissions = permissions
            configurator.exploit = exploit

            configurator.allowedExceptionsForAttempt = allowedExceptionsForAttempt

            configurator.viewActionInterceptors = viewActionInterceptors
            configurator.viewAssertionInterceptors = viewAssertionInterceptors
            configurator.atomInterceptors = atomInterceptors
            configurator.webAssertionInterceptors = webAssertionInterceptors

            configurator.executingInterceptor = executingInterceptor

            failureInterceptor?.let { Espresso.setFailureHandler(it::interceptAndThrow) }

            configurator.stepInterceptors = stepInterceptors
            configurator.testRunInterceptors = testRunInterceptors

            with(KakaoConfigurator) {
                initViewInteractionDelegateFactory { ViewInteractionDelegateKaspressoImpl(it, configurator) }
                initDataInteractionDelegateFactory {
                        viewInteraction, dataInteraction ->
                    DataInteractionDelegateKaspressoImpl(viewInteraction, dataInteraction, configurator)
                }
                initWebInteractionDelegateFactory { WebInteractionDelegateKaspressoImpl(it, configurator) }
            }

            ConfiguratorExt.allowedExceptionsForAttempt = allowedExceptionsForAttempt
            ConfiguratorExt.attemptsIntervalMs = attemptsIntervalMs
            ConfiguratorExt.attemptsTimeoutMs = attemptsTimeoutMs
            ConfiguratorExt.logger = logger

            return configurator
        }
    }

}