package com.kaspersky.kaspresso.kaspresso

import android.app.Instrumentation
import androidx.test.espresso.Espresso
import androidx.test.espresso.FailureHandler
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import com.agoda.kakao.Kakao
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
import com.kaspersky.kaspresso.device.permissions.HackPermissions
import com.kaspersky.kaspresso.device.permissions.HackPermissionsImpl
import com.kaspersky.kaspresso.device.permissions.Permissions
import com.kaspersky.kaspresso.device.permissions.PermissionsImpl
import com.kaspersky.kaspresso.device.phone.Phone
import com.kaspersky.kaspresso.device.phone.PhoneImpl
import com.kaspersky.kaspresso.device.screenshots.Screenshots
import com.kaspersky.kaspresso.device.screenshots.ScreenshotsImpl
import com.kaspersky.kaspresso.device.server.AdbServer
import com.kaspersky.kaspresso.device.server.AdbServerImpl
import com.kaspersky.kaspresso.failure.LoggingFailureHandler
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
import com.kaspersky.kaspresso.params.AutoScrollParams
import com.kaspersky.kaspresso.params.FlakySafetyParams
import com.kaspersky.kaspresso.params.Params
import com.kaspersky.kaspresso.params.StepParams
import com.kaspersky.kaspresso.report.impl.AllureReportWriter
import com.kaspersky.kaspresso.testcases.core.testcontext.BaseTestContext

/**
 * The storage of all Kaspresso preferences and entities, such as [AdbServer], [Device] and different interceptors.
 */
data class Kaspresso(
    internal val libLogger: UiTestLogger,
    internal val testLogger: UiTestLogger,
    internal val adbServer: AdbServer,
    internal val device: Device,
    internal val params: Params,
    internal val viewActionWatcherInterceptors: List<ViewActionWatcherInterceptor>,
    internal val viewAssertionWatcherInterceptors: List<ViewAssertionWatcherInterceptor>,
    internal val atomWatcherInterceptors: List<AtomWatcherInterceptor>,
    internal val webAssertionWatcherInterceptors: List<WebAssertionWatcherInterceptor>,
    internal val viewBehaviorInterceptors: List<ViewBehaviorInterceptor>,
    internal val dataBehaviorInterceptors: List<DataBehaviorInterceptor>,
    internal val webBehaviorInterceptors: List<WebBehaviorInterceptor>,
    internal val stepWatcherInterceptors: List<StepWatcherInterceptor>,
    internal val testRunWatcherInterceptors: List<TestRunWatcherInterceptor>,
    internal val beforeEachTestAction: (BaseTestContext.() -> Unit)?,
    internal val afterEachTestAction: (BaseTestContext.() -> Unit)?
) {
    private companion object {
        private const val DEFAULT_LIB_LOGGER_TAG: String = "KASPRESSO"
        private const val DEFAULT_TEST_LOGGER_TAG: String = "KASPRESSO_TEST"
    }

    /**
     * The class for [Kaspresso] configuration and initialization. This is the way to set Kaspresso preferences.
     */
    class Builder {

        companion object {
            /**
             * Puts the default preferences and entities pack to [Builder].
             * Please be aware if you add some settings after [default] method. You can catch inconsistent state of the
             * [Builder]. For example if you change [libLogger] after [default] method than all interceptors will work
             * with old [libLogger].
             *
             * @return the new instance of [Builder].
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

        /**
         * Holds an implementation of [UiTestLogger] interface for inner Kaspresso usage.
         * If it was not specified, the default implementation with default tag is used.
         */
        var libLogger: UiTestLogger = UiTestLoggerImpl(DEFAULT_LIB_LOGGER_TAG)

        /**
         * Holds an implementation of [UiTestLogger] interface for external developer's usage in tests.
         * If it was not specified, the default implementation with default tag is used.
         */
        var testLogger: UiTestLogger = UiTestLoggerImpl(DEFAULT_TEST_LOGGER_TAG)

        private val instrumentation: Instrumentation = InstrumentationRegistry.getInstrumentation()
        private val uiDevice = UiDevice.getInstance(instrumentation)

        /**
         * Holds an implementation of [AdbServer] interface. If it was not specified, the default implementation is used.
         */
        var adbServer: AdbServer = AdbServerImpl(libLogger)

        /**
         * Holds an implementation of [Apps] interface. If it was not specified, the default implementation is used.
         */
        var apps: Apps = AppsImpl(libLogger, instrumentation.context, uiDevice, adbServer)

        /**
         * Holds an implementation of [Activities] interface. If it was not specified, the default implementation is used.
         */
        var activities: Activities = ActivitiesImpl(libLogger)

        /**
         * Holds an implementation of [Files] interface. If it was not specified, the default implementation is used.
         */
        var files: Files = FilesImpl(adbServer)

        /**
         * Holds an implementation of [Network] interface. If it was not specified, the default implementation is used.
         */
        var network: Network = NetworkImpl(instrumentation.targetContext, adbServer)

        /**
         * Holds an implementation of [Phone] interface. If it was not specified, the default implementation is used.
         */
        var phone: Phone = PhoneImpl(adbServer)

        /**
         * Holds an implementation of [Location] interface. If it was not specified, the default implementation is used.
         */
        var location: Location = LocationImpl(adbServer)

        /**
         * Holds an implementation of [Keyboard] interface. If it was not specified, the default implementation is used.
         */
        var keyboard: Keyboard = KeyboardImpl(adbServer)

        /**
         * Holds an implementation of [Screenshots] interface. If it was not specified, the default implementation is used.
         */
        var screenshots: Screenshots = ScreenshotsImpl(libLogger, activities)

        /**
         * Holds an implementation of [Accessibility] interface. If it was not specified, the default implementation is used.
         */
        var accessibility: Accessibility = AccessibilityImpl()

        /**
         * Holds an implementation of [Permissions] interface. If it was not specified, the default implementation is used.
         */
        var permissions: Permissions = PermissionsImpl(libLogger, uiDevice)

        /**
         * Holds an implementation of [HackPermissions] interface. If it was not specified, the default implementation is used.
         */
        var hackPermissions: HackPermissions = HackPermissionsImpl(instrumentation.uiAutomation, libLogger)

        /**
         * Holds an implementation of [Exploit] interface. If it was not specified, the default implementation is used.
         */
        var exploit: Exploit = ExploitImpl(activities, uiDevice, adbServer)

        /**
         * Holds the [FlakySafetyParams] for [com.kaspersky.kaspresso.flakysafety.FlakySafetyProvider]'s usage.
         * If it was not specified, the default implementation is used.
         */
        val flakySafetyParams: FlakySafetyParams = FlakySafetyParams()

        /**
         * Holds the [AutoScrollParams] for [com.kaspersky.kaspresso.autoscroll.AutoScrollProvider]'s usage.
         * If it was not specified, the default implementation is used.
         */
        val autoScrollParams: AutoScrollParams = AutoScrollParams()

        /**
         * Holds the [StepParams] for [com.kaspersky.kaspresso.testcases.core.step.StepsManager]'s usage.
         * If it was not specified, the default implementation is used.
         */
        val stepParams: StepParams = StepParams()

        /**
         * Holds the list of [ViewActionWatcherInterceptor]s.
         * If it was not specified, Kaspresso will use no [ViewActionWatcherInterceptor]s.
         * These interceptors are called by [com.kaspersky.kaspresso.proxy.ViewActionProxy]
         * before actual [androidx.test.espresso.ViewAction.perform] call.
         */
        var viewActionWatcherInterceptors: MutableList<ViewActionWatcherInterceptor> = mutableListOf()

        /**
         * Holds the list of [ViewAssertionWatcherInterceptor]s.
         * If it was not specified, Kaspresso will use no [ViewAssertionWatcherInterceptor]s.
         * These interceptors are called by [com.kaspersky.kaspresso.proxy.ViewAssertionProxy]
         * before actual [androidx.test.espresso.ViewAssertion.check] call.
         */
        var viewAssertionWatcherInterceptors: MutableList<ViewAssertionWatcherInterceptor> = mutableListOf()

        /**
         * Holds the list of [AtomWatcherInterceptor]s.
         * If it was not specified, Kaspresso will use no [AtomWatcherInterceptor]s.
         * These interceptors are called by [com.kaspersky.kaspresso.proxy.AtomProxy]
         * before [androidx.test.espresso.web.model.Atom] is actually called.
         */
        var atomWatcherInterceptors: MutableList<AtomWatcherInterceptor> = mutableListOf()

        /**
         * Holds the list of [WebAssertionWatcherInterceptor]s.
         * If it was not specified, Kaspresso will use no [WebAssertionWatcherInterceptor]s.
         * These interceptors are called by [androidx.test.espresso.web.assertion.WebAssertionProxy]
         * before actual [androidx.test.espresso.web.assertion.WebAssertion.checkResult] call.
         */
        var webAssertionWatcherInterceptors: MutableList<WebAssertionWatcherInterceptor> = mutableListOf()

        /**
         * Holds the list of [ViewBehaviorInterceptor]s.
         * If it was not specified, Kaspresso will use no [ViewBehaviorInterceptor]s.
         * These interceptors are called by [com.kaspersky.kaspresso.interceptors.tokakao.impl.KakaoViewInterceptor]
         * before actual [androidx.test.espresso.ViewInteraction.perform] and
         * [androidx.test.espresso.ViewInteraction.check] calls.
         * Note that the order of [ViewBehaviorInterceptor]s in this list is significant: the first item wil be
         * at the lowest level of intercepting chain, and the last item will be at the highest level.
         * For example: the first item actually wraps the [androidx.test.espresso.ViewInteraction.perform] call,
         * the second item wraps the first item, and so on.
         */
        var viewBehaviorInterceptors: MutableList<ViewBehaviorInterceptor> = mutableListOf()

        /**
         * Holds the list of [DataBehaviorInterceptor]s.
         * If it was not specified, Kaspresso will use no [DataBehaviorInterceptor]s.
         * These interceptors are called by [com.kaspersky.kaspresso.interceptors.tokakao.impl.KakaoDataInterceptor]
         * before actual [androidx.test.espresso.DataInteraction.check] call.
         * Note that the order of [DataBehaviorInterceptor]s in this list is significant: the first item wil be
         * at the lowest level of intercepting chain, and the last item will be at the highest level.
         * For example: the first item actually wraps the [androidx.test.espresso.DataInteraction.check] call,
         * the second item wraps the first item, and so on.
         */
        var dataBehaviorInterceptors: MutableList<DataBehaviorInterceptor> = mutableListOf()

        /**
         * Holds the list of [WebBehaviorInterceptor]s.
         * If it was not specified, Kaspresso will use no [WebBehaviorInterceptor]s.
         * These interceptors are called by [com.kaspersky.kaspresso.interceptors.tokakao.impl.KakaoWebInterceptor]
         * before actual [androidx.test.espresso.web.sugar.Web.WebInteraction.perform] and
         * [androidx.test.espresso.web.sugar.Web.WebInteraction.check] calls.
         * Note that the order of [WebBehaviorInterceptor]s in this list is significant: the first item wil be
         * at the lowest level of intercepting chain, and the last item will be at the highest level.
         * For example: the first item actually wraps the [androidx.test.espresso.web.sugar.Web.WebInteraction.perform]
         * call, the second item wraps the first item, and so on.
         */
        var webBehaviorInterceptors: MutableList<WebBehaviorInterceptor> = mutableListOf()

        /**
         * Holds the list of [StepWatcherInterceptor]s.
         * If it was not specified, Kaspresso will use no [StepWatcherInterceptor]s.
         * These interceptors are called by [com.kaspersky.kaspresso.testcases.core.testcontext.TestContext] in "step"
         * method on both "step started", "step finished with success", "step finished with failure" and
         * "step finally finished" events.
         */
        var stepWatcherInterceptors: MutableList<StepWatcherInterceptor> = mutableListOf()

        /**
         * Holds the list of [TestRunWatcherInterceptor]s.
         * If it was not specified, Kaspresso will use no [TestRunWatcherInterceptor]s.
         * These interceptors are called by [com.kaspersky.kaspresso.testcases.core.TestRunner] in "run"
         * method on "test started", on all [com.kaspersky.kaspresso.testcases.core.sections]' "section started",
         * "section finished with success" and "section finished with failure", and "test finished" events.
         */
        var testRunWatcherInterceptors: MutableList<TestRunWatcherInterceptor> = mutableListOf()

        /**
         * Holds the implementation of the [androidx.test.espresso.FailureHandler] interface, that is called on every
         * failure.
         */
        var failureHandler: FailureHandler? = null

        /**
         * Holds the action which will be executed before the test.
         * The action has access to BaseTestContext.
         */
        private var beforeEachTestAction: (BaseTestContext.() -> Unit)? = null

        /**
         * Set the action which will be executed before the test.
         * The action has access to BaseTestContext.
         * If you set @param override in false then the final beforeAction will be
         *     beforeAction of the parent TestCase plus current @param action.
         *     Otherwise final beforeAction will be only @param action.
         */
        fun beforeEachTest(override: Boolean = false, action: BaseTestContext.() -> Unit) {
            if (override) {
                beforeEachTestAction = action
            } else {
                val oldBeforeEachTestAction = beforeEachTestAction
                beforeEachTestAction = {
                    oldBeforeEachTestAction?.invoke(this)
                    action.invoke(this)
                }
            }
        }

        /**
         * Holds the action which will be executed after the test.
         * The action has access to BaseTestContext.
         */
        private var afterEachTestAction: (BaseTestContext.() -> Unit)? = null

        /**
         * Set the action which will be executed after the test.
         * The action has access to BaseTestContext.
         * If you set @param override in false then the final beforeAction will be
         *     beforeAction of the parent TestCase plus current @param action.
         *     Otherwise final beforeAction will be only @param action.
         */
        fun afterEachTest(override: Boolean = false, action: BaseTestContext.() -> Unit) {
            if (override) {
                afterEachTestAction = action
            } else {
                val oldAfterEachTestAction = afterEachTestAction
                afterEachTestAction = {
                    oldAfterEachTestAction?.invoke(this)
                    action.invoke(this)
                }
            }
        }

        /**
         * Sets the Kaspressos's implementations of Kakao's [androidx.test.espresso.ViewInteraction] interceptor,
         * [androidx.test.espresso.DataInteraction] interceptor and [androidx.test.espresso.WebInteraction] interceptor.
         */
        private fun initInterception(kaspresso: Kaspresso) {
            val viewInterceptor = KakaoViewInterceptor(kaspresso)
            val dataInterceptor = KakaoDataInterceptor(kaspresso)
            val webInterceptor = KakaoWebInterceptor(kaspresso)

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
         * Terminating method to build [Kaspresso] instance. Can be called only inside the framework package.
         * Actually called when the base [com.kaspersky.kaspresso.testcases.api.testcase.BaseTestCase] class is
         * instantiated.
         *
         * @return build [Kaspresso] instance.
         */
        internal fun build(): Kaspresso {

            val kaspresso = Kaspresso(
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
                    hackPermissions = hackPermissions,
                    exploit = exploit
                ),

                params = Params(
                    flakySafetyParams = flakySafetyParams,
                    autoScrollParams = autoScrollParams,
                    stepParams = stepParams
                ),

                viewActionWatcherInterceptors = viewActionWatcherInterceptors,
                viewAssertionWatcherInterceptors = viewAssertionWatcherInterceptors,
                atomWatcherInterceptors = atomWatcherInterceptors,
                webAssertionWatcherInterceptors = webAssertionWatcherInterceptors,

                viewBehaviorInterceptors = viewBehaviorInterceptors,
                dataBehaviorInterceptors = dataBehaviorInterceptors,
                webBehaviorInterceptors = webBehaviorInterceptors,

                stepWatcherInterceptors = stepWatcherInterceptors,
                testRunWatcherInterceptors = testRunWatcherInterceptors,

                beforeEachTestAction = beforeEachTestAction,
                afterEachTestAction = afterEachTestAction
            )

            initInterception(kaspresso)

            failureHandler?.let { Espresso.setFailureHandler(it) }

            return kaspresso
        }
    }

    internal fun reset(): Unit = Kakao.reset()
}