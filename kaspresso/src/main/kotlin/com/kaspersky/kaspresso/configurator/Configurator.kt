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
import com.agoda.kakao.configurator.KakaoConfigurator

/**
 * A class that keeps all settings.
 *
 * @param attemptsTimeoutMs A timeout for all action attempts in milliseconds.
 * @param attemptsIntervalMs A frequency of action attempts in milliseconds.
 * @param logger Holds an implementation of [UiTestLogger] interface for inner framework usage. Not accessible from outside.
 * @param externalLogger Holds an implementation of [UiTestLogger] interface for external usage.
 * @param apps Holds an implementation of [Apps] interface. If it was not specified in [Configurator.Builder], the default implementation is used.
 * @param activities Holds an implementation of [Activities] interface. If it was not specified in [Configurator.Builder], the default implementation is used.
 * @param files Holds an implementation of [Files] interface. If it was not specified in [Configurator.Builder], the default implementation is used.
 * @param internet Holds an implementation of [Internet] interface. If it was not specified in [Configurator.Builder], the default implementation is used.
 * @param screenshots Holds an implementation of [Screenshots] interface. If it was not specified in [Configurator.Builder], the
 * default implementation is used.
 * @param accessibility Holds an implementation of [Accessibility] interface. If it was not specified in [Configurator.Builder], the
 * default implementation is used.
 * @param permissions Holds an implementation of [Permissions] interface. If it was not specified in [Configurator.Builder], the
 * default implementation is used.
 * @param exploit Holds an implementation of [Exploit] interface. If it was not specified in [Configurator.Builder], the default
 * implementation is used.
 * @param allowedExceptionsForAttempt Exceptions that doesn't stop attempts.
 * @param viewActionInterceptors Interceptors that are called by [com.kaspersky.uitest_framework.proxy.ViewActionProxy] before actually [android.support.test.espresso.ViewAction.perform] call.
 * @param viewAssertionInterceptors Interceptors that are called by [com.kaspersky.uitest_framework.proxy.ViewAssertionProxy] before actually [android.support.test.espresso.ViewAssertion.check] call.
 * @param atomInterceptors Interceptors that are called by [com.kaspersky.uitest_framework.proxy.AtomProxy] before actually [android.support.test.espresso.web.model.Atom.transform] call.
 * @param webAssertionInterceptors  Interceptors that are called by [android.support.test.espresso.web.assertion.WebAssertionProxy] before actually [android.support.test.espresso.web.assertion.WebAssertion.checkResult] call.
 * @param executingInterceptor An interceptor that actually manages the execution of actions or assertions. For example, [FlakySafeExecutingInterceptor] performs multiple attempting to execute an action or assertion.
 * @param stepInterceptors An interceptors set that actually manages the execution of steps [TestContext.step]. Interceptors works using decorator pattern. First interceptor wraps others
 * @param testRunInterceptors An interceptors set that actually manages the execution of test sections [TestInfo]. Interceptors works using decorator pattern. First interceptor wraps others
 */
class Configurator(
    internal val attemptsTimeoutMs: Long = DEFAULT_ATTEMPTS_TIMEOUT_MS,
    internal val attemptsIntervalMs: Long = DEFAULT_ATTEMPTS_INTERVAL_MS,
    internal val logger: UiTestLogger,
    internal val externalLogger: UiTestLogger,
    internal val apps: Apps,
    internal val activities: Activities,
    internal val files: Files,
    internal val internet: Internet,
    internal val screenshots: Screenshots,
    internal val accessibility: Accessibility,
    internal val permissions: Permissions,
    internal val exploit: Exploit,
    internal val allowedExceptionsForAttempt: Set<Class<out Throwable>>,
    internal val viewActionInterceptors: List<ViewActionInterceptor>,
    internal val viewAssertionInterceptors: List<ViewAssertionInterceptor>,
    internal val atomInterceptors: List<AtomInterceptor>,
    internal val webAssertionInterceptors: List<WebAssertionInterceptor>,
    internal val executingInterceptor: ExecutingInterceptor? = null,
    internal val stepInterceptors: List<StepInterceptor>,
    internal val testRunInterceptors: List<TestRunInterceptor>
) {

    companion object {
        internal const val DEFAULT_ATTEMPTS_TIMEOUT_MS: Long = 2_000L
        internal const val DEFAULT_ATTEMPTS_INTERVAL_MS: Long = 500L

        internal const val DEFAULT_INNER_LOGGER_TAG: String = "KASPRESSO"
        internal const val DEFAULT_OUTER_LOGGER_TAG: String = "KASPRESSO_SPECIAL"
    }


    /**
     * A class for [Configurator] initialization. The right way to change [Configurator] settings is to use [Builder].
     */
    class Builder {

        companion object {

            /**
             * Puts the default settings pack to [Builder].
             * Please be aware if you add some settings after [default] method. You can catch inconsistent state of the [Builder].
             * For example if you change [logger] after [default] method than all interceptors will work with old [logger]
             *
             * @return an existing instance of [Builder].
             */
            fun default(): Builder {
                return Builder().apply {
                    viewActionInterceptors = listOf(LoggingViewActionInterceptor(logger))
                    viewAssertionInterceptors = listOf(LoggingViewAssertionInterceptor(logger))

                    atomInterceptors = listOf(LoggingAtomInterceptor(logger))
                    webAssertionInterceptors = listOf(LoggingWebAssertionInterceptor(logger))

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

        var apps: Apps = AppsImpl(
            logger,
            InstrumentationRegistry.getInstrumentation().context,
            UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        )
        var activities: Activities = ActivitiesImpl(logger)
        var files: Files = FilesImpl()
        var internet: Internet = InternetImpl(InstrumentationRegistry.getTargetContext())
        var screenshots: Screenshots = ScreenshotsImpl(logger, activities)
        var accessibility: Accessibility = AccessibilityImpl()
        var permissions: Permissions =
            PermissionsImpl(logger, UiDevice.getInstance(InstrumentationRegistry.getInstrumentation()))
        var exploit: Exploit =
            ExploitImpl(activities, UiDevice.getInstance(InstrumentationRegistry.getInstrumentation()))

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
         * Terminating method to build built [Configurator] settings. Can be called only inside the framework
         * package. Actually called when the base [com.kaspersky.uitest_framework.testcase.TestCase] class is
         * constructed.
         */
        internal fun build(): Configurator {

            val configurator = Configurator(

                attemptsTimeoutMs = attemptsTimeoutMs,
                attemptsIntervalMs = attemptsIntervalMs,

                logger = logger,
                externalLogger = externalLogger,

                apps = apps,
                activities = activities,
                files = files,
                internet = internet,
                screenshots = screenshots,
                accessibility = accessibility,
                permissions = permissions,
                exploit = exploit,

                allowedExceptionsForAttempt = allowedExceptionsForAttempt,

                viewActionInterceptors = viewActionInterceptors,
                viewAssertionInterceptors = viewAssertionInterceptors,
                atomInterceptors = atomInterceptors,
                webAssertionInterceptors = webAssertionInterceptors,

                executingInterceptor = executingInterceptor,

                stepInterceptors = stepInterceptors,
                testRunInterceptors = testRunInterceptors
            )

            failureInterceptor?.let { Espresso.setFailureHandler(it::interceptAndThrow) }

            with(KakaoConfigurator) {
                initViewInteractionDelegateFactory { ViewInteractionDelegateKaspressoImpl(it, configurator) }
                initDataInteractionDelegateFactory { viewInteraction, dataInteraction ->
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

    internal fun reset() {
        with(KakaoConfigurator) {
            initViewInteractionDelegateFactory(null)
            initDataInteractionDelegateFactory(null)
            initWebInteractionDelegateFactory(null)
        }
    }

}