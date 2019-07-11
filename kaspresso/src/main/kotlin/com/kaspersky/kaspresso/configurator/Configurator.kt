package com.kaspersky.kaspresso.configurator

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso
import android.support.test.espresso.NoMatchingViewException
import android.support.test.espresso.PerformException
import android.support.test.uiautomator.UiDevice
import com.agoda.kakao.Kakao
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
import com.kaspersky.kaspresso.device.keyboard.Keyboard
import com.kaspersky.kaspresso.device.keyboard.KeyboardImpl
import com.kaspersky.kaspresso.device.location.Location
import com.kaspersky.kaspresso.device.location.LocationImpl
import com.kaspersky.kaspresso.device.permissions.Permissions
import com.kaspersky.kaspresso.device.permissions.PermissionsImpl
import com.kaspersky.kaspresso.device.phone.Phone
import com.kaspersky.kaspresso.device.phone.PhoneImpl
import com.kaspersky.kaspresso.device.screenshots.Screenshots
import com.kaspersky.kaspresso.device.screenshots.ScreenshotsImpl
import com.kaspersky.kaspresso.interceptors.AtomInterceptor
import com.kaspersky.kaspresso.interceptors.ExecutingInterceptor
import com.kaspersky.kaspresso.interceptors.FailureInterceptor
import com.kaspersky.kaspresso.interceptors.StepInterceptor
import com.kaspersky.kaspresso.interceptors.TestRunInterceptor
import com.kaspersky.kaspresso.interceptors.ViewActionInterceptor
import com.kaspersky.kaspresso.interceptors.ViewAssertionInterceptor
import com.kaspersky.kaspresso.interceptors.WebAssertionInterceptor
import com.kaspersky.kaspresso.interceptors.impl.flakysafety.FlakySafeExecutingInterceptor
import com.kaspersky.kaspresso.interceptors.impl.logging.LoggingAtomInterceptor
import com.kaspersky.kaspresso.interceptors.impl.logging.LoggingFailureInterceptor
import com.kaspersky.kaspresso.interceptors.impl.logging.LoggingStepInterceptor
import com.kaspersky.kaspresso.interceptors.impl.logging.LoggingViewActionInterceptor
import com.kaspersky.kaspresso.interceptors.impl.logging.LoggingViewAssertionInterceptor
import com.kaspersky.kaspresso.interceptors.impl.logging.LoggingWebAssertionInterceptor
import com.kaspersky.kaspresso.interceptors.impl.logging.TestRunLoggerInterceptor
import com.kaspersky.kaspresso.interceptors.impl.report.BuildStepReportInterceptor
import com.kaspersky.kaspresso.interceptors.impl.screenshot.ScreenshotStepInterceptor
import com.kaspersky.kaspresso.interceptors.impl.screenshot.TestRunnerScreenshotInterceptor
import com.kaspersky.kaspresso.kakao_interceptors.DataInteractionKakaoInterceptor
import com.kaspersky.kaspresso.kakao_interceptors.ViewInteractionKakaoInterceptor
import com.kaspersky.kaspresso.kakao_interceptors.WebInteractionKakaoInterceptor
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso.logger.UiTestLoggerImpl
import com.kaspersky.kaspresso.logger.composite.CompositeLogger
import com.kaspersky.kaspresso.report.impl.AllureReportWriter
import com.kaspersky.kaspresso.testcases.core.testcontext.TestContext

/**
 * A class that keeps all settings.
 *
 * @param apps Holds an implementation of [Apps] interface. If it was not specified in [Configurator.Builder], the
 * default implementation is used.
 * @param activities Holds an implementation of [Activities] interface. If it was not specified in [Configurator.Builder],
 * the default implementation is used.
 * @param files Holds an implementation of [Files] interface. If it was not specified in [Configurator.Builder],
 * the default implementation is used.
 * @param internet Holds an implementation of [Internet] interface. If it was not specified in [Configurator.Builder],
 * the default implementation is used.
 * @param phone Holds an implementation of [Phone] interface. If it was not specified in [Configurator.Builder],
 * the default implementation is used.
 * @param location Holds an implementation of [Location] interface. If it was not specified in [Configurator.Builder],
 * the default implementation is used.
 * @param keyboard Holds an implementation of [Keyboard] interface. If it was not specified in [Configurator.Builder],
 * the default implementation is used.
 * @param screenshots Holds an implementation of [Screenshots] interface. If it was not specified in [Configurator.Builder],
 * the default implementation is used.
 * @param accessibility Holds an implementation of [Accessibility] interface. If it was not specified in [Configurator.Builder],
 * the default implementation is used.
 * @param permissions Holds an implementation of [Permissions] interface. If it was not specified in [Configurator.Builder],
 * the default implementation is used.
 * @param exploit Holds an implementation of [Exploit] interface. If it was not specified in [Configurator.Builder],
 * the default implementation is used.
 * @param viewActionInterceptors Interceptors that are called by [com.kaspersky.kaspresso.proxy.ViewActionProxy]
 * before actually [android.support.test.espresso.ViewAction.perform] call.
 * @param viewAssertionInterceptors Interceptors that are called by [com.kaspersky.kaspresso.proxy.ViewAssertionProxy]
 * before actually [android.support.test.espresso.ViewAssertion.check] call.
 * @param atomInterceptors Interceptors that are called by [com.kaspersky.kaspresso.proxy.AtomProxy]
 * before actually [android.support.test.espresso.web.model.Atom.transform] call.
 * @param webAssertionInterceptors Interceptors that are called by [android.support.test.espresso.web.assertion.WebAssertionProxy]
 * before actually [android.support.test.espresso.web.assertion.WebAssertion.checkResult] call.
 * @param executingInterceptor An interceptor that actually manages the execution of actions or assertions. For example,
 * [FlakySafeExecutingInterceptor] performs multiple attempting to execute an action or assertion.
 * @param stepInterceptors An interceptors set that actually manages the execution of steps [TestContext.step].
 * Interceptors work using decorator pattern. First interceptor wraps others.
 * @param testRunInterceptors An interceptors set that actually manages the execution of test sections
 * [com.kaspersky.kaspresso.testcases.models.TestInfo]. Interceptor works using decorator pattern. First interceptor wraps others.
 * @param externalLogger Holds an implementation of [UiTestLogger] interface for external usage.
 * @param compositeLogger Holds an instance of [CompositeLogger] for inner framework usage. Not accessible from outside.
 */
class Configurator(
    internal val apps: Apps,
    internal val activities: Activities,
    internal val files: Files,
    internal val internet: Internet,
    internal val phone: Phone,
    internal val location: Location,
    internal val keyboard: Keyboard,
    internal val screenshots: Screenshots,
    internal val accessibility: Accessibility,
    internal val permissions: Permissions,
    internal val exploit: Exploit,
    internal val viewActionInterceptors: List<ViewActionInterceptor>,
    internal val viewAssertionInterceptors: List<ViewAssertionInterceptor>,
    internal val atomInterceptors: List<AtomInterceptor>,
    internal val webAssertionInterceptors: List<WebAssertionInterceptor>,
    internal val executingInterceptor: ExecutingInterceptor? = null,
    internal val stepInterceptors: List<StepInterceptor>,
    internal val testRunInterceptors: List<TestRunInterceptor>,
    internal val externalLogger: UiTestLogger,
    internal val compositeLogger: CompositeLogger
) {
    companion object {

        internal const val DEFAULT_ATTEMPTS_TIMEOUT_MS: Long = 2_000L
        internal const val DEFAULT_ATTEMPTS_INTERVAL_MS: Long = 500L

        internal const val DEFAULT_INNER_LOGGER_TAG: String = "KASPRESSO"
        internal const val DEFAULT_EXTERNAL_LOGGER_TAG: String = "KASPRESSO_SPECIAL"

        /**
         * A timeout for all action attempts in milliseconds.
         */
        internal var attemptsTimeoutMs: Long = DEFAULT_ATTEMPTS_TIMEOUT_MS

        /**
         * A frequency of action attempts in milliseconds.
         */
        internal var attemptsIntervalMs: Long = DEFAULT_ATTEMPTS_INTERVAL_MS

        /**
         * Exceptions that doesn't stop attempts.
         */
        internal var allowedExceptionsForAttempt: Set<Class<out Throwable>> =
            setOf(
                PerformException::class.java,
                NoMatchingViewException::class.java,
                AssertionError::class.java
            )

        /**
         * Holds an implementation of [UiTestLogger] interface for inner framework usage.
         */
        internal var logger: UiTestLogger = UiTestLoggerImpl(DEFAULT_INNER_LOGGER_TAG)
    }

    /**
     * A class for [Configurator] initialization. The right way to change [Configurator] settings is to use [Builder].
     */
    class Builder {

        companion object {

            /**
             * Puts the default settings pack to [Builder].
             * Please be aware if you add some settings after [default] method. You can catch inconsistent state of the
             * [Builder]. For example if you change [logger] after [default] method than all interceptors will work with
             * old [logger].
             *
             * @return an existing instance of [Builder].
             */
            fun default(): Builder {
                return Builder().apply {

                    viewActionInterceptors = mutableListOf(LoggingViewActionInterceptor(logger))
                    viewAssertionInterceptors = mutableListOf(LoggingViewAssertionInterceptor(logger))

                    atomInterceptors = mutableListOf(LoggingAtomInterceptor(compositeLogger))
                    webAssertionInterceptors = mutableListOf(LoggingWebAssertionInterceptor(compositeLogger))

                    executingInterceptor = FlakySafeExecutingInterceptor()
                    failureInterceptor = LoggingFailureInterceptor(logger)

                    stepInterceptors = mutableListOf(
                        LoggingStepInterceptor(logger),
                        ScreenshotStepInterceptor(screenshots)
                    )

                    testRunInterceptors = mutableListOf(
                        TestRunLoggerInterceptor(logger),
                        TestRunnerScreenshotInterceptor(screenshots),
                        BuildStepReportInterceptor(AllureReportWriter(logger))
                    )
                }
            }
        }

        var attemptsTimeoutMs: Long = DEFAULT_ATTEMPTS_TIMEOUT_MS
        var attemptsIntervalMs: Long = DEFAULT_ATTEMPTS_INTERVAL_MS
        var allowedExceptionsForAttempt: Set<Class<out Throwable>> = setOf(
            PerformException::class.java,
            NoMatchingViewException::class.java,
            AssertionError::class.java
        )

        var logger: UiTestLogger = UiTestLoggerImpl(DEFAULT_INNER_LOGGER_TAG)
        var externalLogger: UiTestLogger = UiTestLoggerImpl(DEFAULT_EXTERNAL_LOGGER_TAG)
        var compositeLogger: CompositeLogger = CompositeLogger(logger)

        var apps: Apps = AppsImpl(
            logger,
            InstrumentationRegistry.getInstrumentation().context,
            UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        )
        var activities: Activities = ActivitiesImpl(logger)
        var files: Files = FilesImpl()
        var internet: Internet = InternetImpl(InstrumentationRegistry.getTargetContext())
        var phone: Phone = PhoneImpl()
        var location: Location = LocationImpl()
        var keyboard: Keyboard = KeyboardImpl()
        var screenshots: Screenshots = ScreenshotsImpl(logger, activities)
        var accessibility: Accessibility = AccessibilityImpl()
        var permissions: Permissions =
            PermissionsImpl(logger, UiDevice.getInstance(InstrumentationRegistry.getInstrumentation()))
        var exploit: Exploit =
            ExploitImpl(activities, UiDevice.getInstance(InstrumentationRegistry.getInstrumentation()))

        var viewActionInterceptors: MutableList<ViewActionInterceptor> = mutableListOf()
        var viewAssertionInterceptors: MutableList<ViewAssertionInterceptor> = mutableListOf()
        var atomInterceptors: MutableList<AtomInterceptor> = mutableListOf()
        var webAssertionInterceptors: MutableList<WebAssertionInterceptor> = mutableListOf()

        var executingInterceptor: ExecutingInterceptor? = null

        /**
         * An interceptor that is called on failures. It's [FailureInterceptor.interceptAndThrow] method is being
         * provide as the default [android.support.test.espresso.FailureHandler].
         */
        var failureInterceptor: FailureInterceptor? = null

        var stepInterceptors: MutableList<StepInterceptor> = mutableListOf()

        var testRunInterceptors: MutableList<TestRunInterceptor> = mutableListOf()

        /**
         * Terminating method to build built [Configurator] settings. Can be called only inside the framework
         * package. Actually called when the base [com.kaspersky.kaspresso.testcases.api.BaseTestCase] class is
         * constructed.
         */
        internal fun build(): Configurator {

            val configurator = Configurator(
                apps = apps,
                activities = activities,
                files = files,
                internet = internet,
                phone = phone,
                location = location,
                keyboard = keyboard,
                screenshots = screenshots,
                accessibility = accessibility,
                permissions = permissions,
                exploit = exploit,

                viewActionInterceptors = viewActionInterceptors,
                viewAssertionInterceptors = viewAssertionInterceptors,
                atomInterceptors = atomInterceptors,
                webAssertionInterceptors = webAssertionInterceptors,

                executingInterceptor = executingInterceptor,

                stepInterceptors = stepInterceptors,
                testRunInterceptors = testRunInterceptors,

                externalLogger = externalLogger,
                compositeLogger = compositeLogger
            )

            failureInterceptor?.let { Espresso.setFailureHandler(it::interceptAndThrow) }

            val viewInteractionKakaoInterceptor = ViewInteractionKakaoInterceptor(configurator)
            val dataInteractionKakaoInterceptor = DataInteractionKakaoInterceptor(configurator)
            val webInteractionKakaoInterceptor = WebInteractionKakaoInterceptor(configurator)

            Kakao.intercept {
                onViewInteraction {
                    onCheck(
                        isOverride = true,
                        interceptor = viewInteractionKakaoInterceptor.captureCheck()
                    )
                    onPerform(
                        isOverride = true,
                        interceptor = viewInteractionKakaoInterceptor.capturePerform()
                    )
                }
                onDataInteraction {
                    onCheck(
                        isOverride = true,
                        interceptor = dataInteractionKakaoInterceptor.captureCheck()
                    )
                }
                onWebInteraction {
                    onCheck(
                        isOverride = true,
                        interceptor = webInteractionKakaoInterceptor.captureCheck()
                    )
                    onPerform(
                        isOverride = true,
                        interceptor = webInteractionKakaoInterceptor.capturePerform()
                    )
                }
            }

            Configurator.attemptsTimeoutMs = attemptsTimeoutMs
            Configurator.attemptsIntervalMs = attemptsIntervalMs
            Configurator.allowedExceptionsForAttempt = allowedExceptionsForAttempt

            Configurator.logger = logger

            return configurator
        }
    }

    internal fun reset() {
        Kakao.reset()
    }
}