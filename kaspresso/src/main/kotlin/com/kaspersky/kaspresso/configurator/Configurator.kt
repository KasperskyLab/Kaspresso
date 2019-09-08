package com.kaspersky.kaspresso.configurator

import android.app.Instrumentation
import androidx.test.espresso.Espresso
import androidx.test.espresso.FailureHandler
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import com.agoda.kakao.Kakao
import com.kaspersky.kaspresso.autoscroll.AutoScrollParams
import com.kaspersky.kaspresso.device.Device
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
import com.kaspersky.kaspresso.device.keyboard.Keyboard
import com.kaspersky.kaspresso.device.keyboard.KeyboardImpl
import com.kaspersky.kaspresso.device.location.Location
import com.kaspersky.kaspresso.device.location.LocationImpl
import com.kaspersky.kaspresso.device.network.Network
import com.kaspersky.kaspresso.device.network.NetworkImpl
import com.kaspersky.kaspresso.device.permissions.Permissions
import com.kaspersky.kaspresso.device.permissions.PermissionsImpl
import com.kaspersky.kaspresso.device.phone.Phone
import com.kaspersky.kaspresso.device.phone.PhoneImpl
import com.kaspersky.kaspresso.device.screenshots.Screenshots
import com.kaspersky.kaspresso.device.screenshots.ScreenshotsImpl
import com.kaspersky.kaspresso.device.server.AdbServer
import com.kaspersky.kaspresso.device.server.AdbServerImpl
import com.kaspersky.kaspresso.failure.LoggingFailureHandler
import com.kaspersky.kaspresso.flakysafety.FlakySafetyParams
import com.kaspersky.kaspresso.interceptors.behavior.DataBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behavior.ViewBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behavior.WebBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behavior.impl.autoscroll.AutoScrollViewBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behavior.impl.autoscroll.AutoScrollWebBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behavior.impl.failure.FailureLoggingDataBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behavior.impl.failure.FailureLoggingViewBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behavior.impl.failure.FailureLoggingWebBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behavior.impl.flakysafety.FlakySafeDataBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behavior.impl.flakysafety.FlakySafeViewBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behavior.impl.flakysafety.FlakySafeWebBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behavior.impl.systemsafety.SystemDialogSafetyDataBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behavior.impl.systemsafety.SystemDialogSafetyViewBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behavior.impl.systemsafety.SystemDialogSafetyWebBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.tokakao.impl.KakaoDataInterceptor
import com.kaspersky.kaspresso.interceptors.tokakao.impl.KakaoViewInterceptor
import com.kaspersky.kaspresso.interceptors.tokakao.impl.KakaoWebInterceptor
import com.kaspersky.kaspresso.interceptors.watcher.testcase.StepWatcherInterceptor
import com.kaspersky.kaspresso.interceptors.watcher.testcase.TestRunWatcherInterceptor
import com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.logging.LoggingStepWatcherInterceptor
import com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.logging.TestRunLoggerWatcherInterceptor
import com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.report.BuildStepReportWatcherInterceptor
import com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.screenshot.ScreenshotStepWatcherInterceptor
import com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.screenshot.TestRunnerScreenshotWatcherInterceptor
import com.kaspersky.kaspresso.interceptors.watcher.view.AtomWatcherInterceptor
import com.kaspersky.kaspresso.interceptors.watcher.view.ViewActionWatcherInterceptor
import com.kaspersky.kaspresso.interceptors.watcher.view.ViewAssertionWatcherInterceptor
import com.kaspersky.kaspresso.interceptors.watcher.view.WebAssertionWatcherInterceptor
import com.kaspersky.kaspresso.interceptors.watcher.view.impl.logging.LoggingAtomWatcherInterceptor
import com.kaspersky.kaspresso.interceptors.watcher.view.impl.logging.LoggingViewActionWatcherInterceptor
import com.kaspersky.kaspresso.interceptors.watcher.view.impl.logging.LoggingViewAssertionWatcherInterceptor
import com.kaspersky.kaspresso.interceptors.watcher.view.impl.logging.LoggingWebAssertionWatcherInterceptor
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso.logger.UiTestLoggerImpl
import com.kaspersky.kaspresso.report.impl.AllureReportWriter

/**
 * A class that keeps all settings.
 *
 * @param apps Holds an implementation of [Apps] interface. If it was not specified in [Configurator.Builder], the
 * default implementation is used.
 * @param activities Holds an implementation of [Activities] interface. If it was not specified in [Configurator.Builder],
 * the default implementation is used.
 * @param files Holds an implementation of [Files] interface. If it was not specified in [Configurator.Builder],
 * the default implementation is used.
 * @param internet Holds an implementation of [Network] interface. If it was not specified in [Configurator.Builder],
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
 * @param testLogger Holds an implementation of [UiTestLogger] interface for external usage.
 */
data class Configurator(
    internal val libLogger: UiTestLogger,
    internal val testLogger: UiTestLogger,
    internal val adbServer: AdbServer,
    internal val device: Device,
    internal val flakySafetyParams: FlakySafetyParams,
    internal val autoScrollParams: AutoScrollParams,
    internal val viewActionWatcherInterceptors: List<ViewActionWatcherInterceptor>,
    internal val viewAssertionWatcherInterceptors: List<ViewAssertionWatcherInterceptor>,
    internal val atomWatcherInterceptors: List<AtomWatcherInterceptor>,
    internal val webAssertionWatcherInterceptors: List<WebAssertionWatcherInterceptor>,
    internal val viewBehaviorInterceptors: List<ViewBehaviorInterceptor>,
    internal val dataBehaviorInterceptors: List<DataBehaviorInterceptor>,
    internal val webBehaviorInterceptors: List<WebBehaviorInterceptor>,
    internal val stepWatcherInterceptors: List<StepWatcherInterceptor>,
    internal val testRunWatcherInterceptors: List<TestRunWatcherInterceptor>
) {
    private companion object {
        private const val DEFAULT_LIB_LOGGER_TAG: String = "KASPRESSO"
        private const val DEFAULT_TEST_LOGGER_TAG: String = "KASPRESSO_TEST"
    }

    /**
     * A class for [Configurator] initialization. The right way to change [Configurator] settings is to use [Builder].
     */
    class Builder {

        companion object {

            /**
             * Puts the default settings pack to [Builder].
             * Please be aware if you add some settings after [default] method. You can catch inconsistent state of the
             * [Builder]. For example if you change [libLogger] after [default] method than all interceptors will work with
             * old [libLogger].
             *
             * @return an existing instance of [Builder].
             */
            fun default(): Builder {
                return Builder().apply {

                    viewActionWatcherInterceptors = mutableListOf(
                        LoggingViewActionWatcherInterceptor(libLogger)
                    )

                    viewAssertionWatcherInterceptors = mutableListOf(
                        LoggingViewAssertionWatcherInterceptor(libLogger)
                    )

                    atomWatcherInterceptors = mutableListOf(
                        LoggingAtomWatcherInterceptor(libLogger)
                    )

                    webAssertionWatcherInterceptors = mutableListOf(
                        LoggingWebAssertionWatcherInterceptor(libLogger)
                    )

                    viewBehaviorInterceptors = mutableListOf(
                        AutoScrollViewBehaviorInterceptor(autoScrollParams, libLogger),
                        SystemDialogSafetyViewBehaviorInterceptor(libLogger, uiDevice),
                        FlakySafeViewBehaviorInterceptor(flakySafetyParams, libLogger),
                        FailureLoggingViewBehaviorInterceptor(libLogger)
                    )

                    dataBehaviorInterceptors = mutableListOf(
                        SystemDialogSafetyDataBehaviorInterceptor(libLogger, uiDevice),
                        FlakySafeDataBehaviorInterceptor(flakySafetyParams, libLogger),
                        FailureLoggingDataBehaviorInterceptor(libLogger)
                    )

                    webBehaviorInterceptors = mutableListOf(
                        AutoScrollWebBehaviorInterceptor(autoScrollParams, libLogger),
                        SystemDialogSafetyWebBehaviorInterceptor(libLogger, uiDevice),
                        FlakySafeWebBehaviorInterceptor(flakySafetyParams, libLogger),
                        FailureLoggingWebBehaviorInterceptor(libLogger)
                    )

                    stepWatcherInterceptors = mutableListOf(
                        LoggingStepWatcherInterceptor(libLogger),
                        ScreenshotStepWatcherInterceptor(screenshots)
                    )

                    testRunWatcherInterceptors = mutableListOf(
                        TestRunLoggerWatcherInterceptor(libLogger),
                        TestRunnerScreenshotWatcherInterceptor(screenshots),
                        BuildStepReportWatcherInterceptor(AllureReportWriter(libLogger))
                    )

                    failureHandler = LoggingFailureHandler(libLogger)
                }
            }
        }

        var libLogger: UiTestLogger = UiTestLoggerImpl(DEFAULT_LIB_LOGGER_TAG)
        var testLogger: UiTestLogger = UiTestLoggerImpl(DEFAULT_TEST_LOGGER_TAG)

        private val instrumentation: Instrumentation = InstrumentationRegistry.getInstrumentation()
        private val uiDevice = UiDevice.getInstance(instrumentation)

        var adbServer: AdbServer = AdbServerImpl(libLogger)

        var apps: Apps = AppsImpl(libLogger, instrumentation.context, UiDevice.getInstance(instrumentation), adbServer)
        var activities: Activities = ActivitiesImpl(libLogger)
        var files: Files = FilesImpl(adbServer)
        var network: Network = NetworkImpl(instrumentation.targetContext, adbServer)
        var phone: Phone = PhoneImpl(adbServer)
        var location: Location = LocationImpl(adbServer)
        var keyboard: Keyboard = KeyboardImpl(adbServer)
        var screenshots: Screenshots = ScreenshotsImpl(libLogger, activities)
        var accessibility: Accessibility = AccessibilityImpl()
        var permissions: Permissions = PermissionsImpl(libLogger, UiDevice.getInstance(instrumentation))
        var exploit: Exploit = ExploitImpl(activities, UiDevice.getInstance(instrumentation), adbServer)

        var flakySafetyParams: FlakySafetyParams = FlakySafetyParams()
        var autoScrollParams: AutoScrollParams = AutoScrollParams()

        var viewActionWatcherInterceptors: MutableList<ViewActionWatcherInterceptor> = mutableListOf()
        var viewAssertionWatcherInterceptors: MutableList<ViewAssertionWatcherInterceptor> = mutableListOf()
        var atomWatcherInterceptors: MutableList<AtomWatcherInterceptor> = mutableListOf()
        var webAssertionWatcherInterceptors: MutableList<WebAssertionWatcherInterceptor> = mutableListOf()

        var viewBehaviorInterceptors: MutableList<ViewBehaviorInterceptor> = mutableListOf()
        var dataBehaviorInterceptors: MutableList<DataBehaviorInterceptor> = mutableListOf()
        var webBehaviorInterceptors: MutableList<WebBehaviorInterceptor> = mutableListOf()

        var stepWatcherInterceptors: MutableList<StepWatcherInterceptor> = mutableListOf()
        var testRunWatcherInterceptors: MutableList<TestRunWatcherInterceptor> = mutableListOf()

        /**
         * An interceptor that is called on failures. It's [FailureHandler.intercept] method is being
         * provide as the default [androidx.test.espresso.FailureHandler].
         */
        var failureHandler: FailureHandler? = null

        private fun initInterception(configurator: Configurator) {
            val viewInterceptor = KakaoViewInterceptor(configurator)
            val dataInterceptor = KakaoDataInterceptor(configurator)
            val webInterceptor = KakaoWebInterceptor(configurator)

            Kakao.intercept {
                onViewInteraction {
                    onCheck(isOverride = true, interceptor = viewInterceptor::interceptCheck)
                    onPerform(isOverride = true, interceptor = viewInterceptor::interceptPerform)
                }
                onDataInteraction {
                    onCheck(isOverride = true, interceptor = dataInterceptor::interceptCheck)
                }
                onWebInteraction {
                    onCheck(isOverride = true, interceptor = webInterceptor::interceptCheck)
                    onPerform(isOverride = true, interceptor = webInterceptor::interceptPerform)
                }
            }
        }

        /**
         * Terminating method to build built [Configurator] settings. Can be called only inside the framework
         * package. Actually called when the base [com.kaspersky.kaspresso.testcases.api.BaseTestCase] class is
         * constructed.
         */
        internal fun build(): Configurator {

            val configurator = Configurator(
                libLogger = libLogger,
                testLogger = testLogger,

                adbServer = adbServer,
                device = Device(
                    apps = apps,
                    activities = activities,
                    files = files,
                    network = network,
                    phone = phone,
                    location = location,
                    keyboard = keyboard,
                    screenshots = screenshots,
                    accessibility = accessibility,
                    permissions = permissions,
                    exploit = exploit
                ),

                flakySafetyParams = flakySafetyParams,
                autoScrollParams = autoScrollParams,

                viewActionWatcherInterceptors = viewActionWatcherInterceptors,
                viewAssertionWatcherInterceptors = viewAssertionWatcherInterceptors,
                atomWatcherInterceptors = atomWatcherInterceptors,
                webAssertionWatcherInterceptors = webAssertionWatcherInterceptors,

                viewBehaviorInterceptors = viewBehaviorInterceptors,
                dataBehaviorInterceptors = dataBehaviorInterceptors,
                webBehaviorInterceptors = webBehaviorInterceptors,

                stepWatcherInterceptors = stepWatcherInterceptors,
                testRunWatcherInterceptors = testRunWatcherInterceptors
            )

            initInterception(configurator)

            failureHandler?.let { Espresso.setFailureHandler(it) }

            return configurator
        }
    }

    internal fun reset(): Unit = Kakao.reset()
}