package com.kaspersky.kaspresso.configurator

import androidx.test.InstrumentationRegistry
import androidx.test.espresso.Espresso
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.PerformException
import androidx.test.uiautomator.UiDevice
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
import com.kaspersky.kaspresso.interceptors.behavior.DataBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behavior.ViewBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behavior.WebBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behavior.impl.autoscroll.AutoscrollViewBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behavior.impl.autoscroll.AutoscrollWebBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behavior.impl.flakysafety.FlakySafeDataBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behavior.impl.flakysafety.FlakySafeViewBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behavior.impl.flakysafety.FlakySafeWebBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.tokakao.impl.DataKakaoInteractionInterceptor
import com.kaspersky.kaspresso.interceptors.tokakao.impl.ViewKakaoInteractionInterceptor
import com.kaspersky.kaspresso.interceptors.tokakao.impl.WebKakaoInteractionInterceptor
import com.kaspersky.kaspresso.interceptors.watcher.testcase.StepWatcherInterceptor
import com.kaspersky.kaspresso.interceptors.watcher.testcase.TestRunWatcherInterceptor
import com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.logging.LoggingStepWatcherInterceptor
import com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.logging.TestRunLoggerWatcherInterceptor
import com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.report.BuildStepReportWatcherInterceptor
import com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.screenshot.ScreenshotStepWatcherInterceptor
import com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.screenshot.TestRunnerScreenshotWatcherInterceptor
import com.kaspersky.kaspresso.interceptors.watcher.view.AtomWatcherInterceptor
import com.kaspersky.kaspresso.interceptors.watcher.view.FailureInterceptor
import com.kaspersky.kaspresso.interceptors.watcher.view.ViewActionWatcherInterceptor
import com.kaspersky.kaspresso.interceptors.watcher.view.ViewAssertionWatcherInterceptor
import com.kaspersky.kaspresso.interceptors.watcher.view.WebAssertionWatcherInterceptor
import com.kaspersky.kaspresso.interceptors.watcher.view.impl.logging.LoggingAtomWatcherInterceptor
import com.kaspersky.kaspresso.interceptors.watcher.view.impl.logging.LoggingFailureInterceptor
import com.kaspersky.kaspresso.interceptors.watcher.view.impl.logging.LoggingViewActionWatcherInterceptor
import com.kaspersky.kaspresso.interceptors.watcher.view.impl.logging.LoggingViewAssertionWatcherInterceptor
import com.kaspersky.kaspresso.interceptors.watcher.view.impl.logging.LoggingWebAssertionWatcherInterceptor
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso.logger.UiTestLoggerImpl
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
 * @param viewActionWatcherInterceptors Interceptors that are called by [com.kaspersky.kaspresso.proxy.ViewActionProxy]
 * before actually [androidx.test.espresso.ViewAction.perform] call.
 * @param viewAssertionWatcherInterceptors Interceptors that are called by [com.kaspersky.kaspresso.proxy.ViewAssertionProxy]
 * before actually [androidx.test.espresso.ViewAssertion.check] call.
 * @param atomWatcherInterceptors Interceptors that are called by [com.kaspersky.kaspresso.proxy.AtomProxy]
 * before actually [androidx.test.espresso.web.model.Atom.transform] call.
 * @param webAssertionWatcherInterceptors Interceptors that are called by [androidx.test.espresso.web.assertion.WebAssertionProxy]
 * before actually [androidx.test.espresso.web.assertion.WebAssertion.checkResult] call.
 * @param executingInterceptor An interceptor that actually manages the execution of actions or assertions. For example,
 * [FlakySafeExecutionInterceptor] performs multiple attempting to interact an action or assertion.
 * @param stepWatcherInterceptors An interceptors set that actually manages the execution of steps [TestContext.step].
 * Interceptors work using decorator pattern. First interceptor wraps others.
 * @param testRunWatcherInterceptors An interceptors set that actually manages the execution of test sections
 * [com.kaspersky.kaspresso.testcases.models.TestInfo]. Interceptor works using decorator pattern. First interceptor wraps others.
 * @param externalLogger Holds an implementation of [UiTestLogger] interface for external usage.
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
    internal val viewActionWatcherInterceptors: List<ViewActionWatcherInterceptor>,
    internal val viewAssertionWatcherInterceptors: List<ViewAssertionWatcherInterceptor>,
    internal val atomWatcherInterceptors: List<AtomWatcherInterceptor>,
    internal val webAssertionWatcherInterceptors: List<WebAssertionWatcherInterceptor>,
    internal val viewBehaviorInterceptors: List<ViewBehaviorInterceptor>,
    internal val dataBehaviorInterceptors: List<DataBehaviorInterceptor>,
    internal val webBehaviorInterceptors: List<WebBehaviorInterceptor>,
    internal val stepWatcherInterceptors: List<StepWatcherInterceptor>,
    internal val testRunWatcherInterceptors: List<TestRunWatcherInterceptor>,
    internal val externalLogger: UiTestLogger
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

                    viewActionWatcherInterceptors = mutableListOf(LoggingViewActionWatcherInterceptor(logger))
                    viewAssertionWatcherInterceptors = mutableListOf(LoggingViewAssertionWatcherInterceptor(logger))

                    atomWatcherInterceptors = mutableListOf(LoggingAtomWatcherInterceptor(logger))
                    webAssertionWatcherInterceptors = mutableListOf(LoggingWebAssertionWatcherInterceptor(logger))

                    viewBehaviorInterceptors = mutableListOf(AutoscrollViewBehaviorInterceptor(), FlakySafeViewBehaviorInterceptor())
                    dataBehaviorInterceptors = mutableListOf(FlakySafeDataBehaviorInterceptor())
                    webBehaviorInterceptors = mutableListOf(AutoscrollWebBehaviorInterceptor(), FlakySafeWebBehaviorInterceptor())

                    failureInterceptor = LoggingFailureInterceptor(logger)

                    stepWatcherInterceptors = mutableListOf(
                        LoggingStepWatcherInterceptor(
                            logger
                        ),
                        ScreenshotStepWatcherInterceptor(
                            screenshots
                        )
                    )

                    testRunWatcherInterceptors = mutableListOf(
                        TestRunLoggerWatcherInterceptor(
                            logger
                        ),
                        TestRunnerScreenshotWatcherInterceptor(
                            screenshots
                        ),
                        BuildStepReportWatcherInterceptor(
                            AllureReportWriter(logger)
                        )
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

        var viewActionWatcherInterceptors: MutableList<ViewActionWatcherInterceptor> = mutableListOf()
        var viewAssertionWatcherInterceptors: MutableList<ViewAssertionWatcherInterceptor> = mutableListOf()
        var atomWatcherInterceptors: MutableList<AtomWatcherInterceptor> = mutableListOf()
        var webAssertionWatcherInterceptors: MutableList<WebAssertionWatcherInterceptor> = mutableListOf()

        var viewBehaviorInterceptors: MutableList<ViewBehaviorInterceptor> = mutableListOf()
        var dataBehaviorInterceptors: MutableList<DataBehaviorInterceptor> = mutableListOf()
        var webBehaviorInterceptors: MutableList<WebBehaviorInterceptor> = mutableListOf()

        /**
         * An interceptor that is called on failures. It's [FailureInterceptor.intercept] method is being
         * provide as the default [androidx.test.espresso.FailureHandler].
         */
        var failureInterceptor: FailureInterceptor? = null

        var stepWatcherInterceptors: MutableList<StepWatcherInterceptor> = mutableListOf()

        var testRunWatcherInterceptors: MutableList<TestRunWatcherInterceptor> = mutableListOf()

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

                viewActionWatcherInterceptors = viewActionWatcherInterceptors,
                viewAssertionWatcherInterceptors = viewAssertionWatcherInterceptors,
                atomWatcherInterceptors = atomWatcherInterceptors,
                webAssertionWatcherInterceptors = webAssertionWatcherInterceptors,

                viewBehaviorInterceptors = viewBehaviorInterceptors,
                dataBehaviorInterceptors = dataBehaviorInterceptors,
                webBehaviorInterceptors = webBehaviorInterceptors,

                stepWatcherInterceptors = stepWatcherInterceptors,
                testRunWatcherInterceptors = testRunWatcherInterceptors,

                externalLogger = externalLogger
            )

            failureInterceptor?.let { Espresso.setFailureHandler(it::interceptAndThrow) }

            val viewInteractionInterceptor = ViewKakaoInteractionInterceptor(configurator)
            val dataInteractionInterceptor = DataKakaoInteractionInterceptor(configurator)
            val webInteractionInterceptor = WebKakaoInteractionInterceptor(configurator)

            Kakao.intercept {
                onViewInteraction {
                    onCheck(
                        isOverride = true,
                        interceptor = viewInteractionInterceptor::interceptCheck
                    )
                    onPerform(
                        isOverride = true,
                        interceptor = viewInteractionInterceptor::interceptPerform
                    )
                }
                onDataInteraction {
                    onCheck(
                        isOverride = true,
                        interceptor = dataInteractionInterceptor::interceptCheck
                    )
                }
                onWebInteraction {
                    onCheck(
                        isOverride = true,
                        interceptor = webInteractionInterceptor::interceptCheck
                    )
                    onPerform(
                        isOverride = true,
                        interceptor = webInteractionInterceptor::interceptPerform
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

    internal fun reset(): Unit = Kakao.reset()
}