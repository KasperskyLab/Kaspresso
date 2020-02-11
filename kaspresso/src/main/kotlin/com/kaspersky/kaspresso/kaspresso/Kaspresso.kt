package com.kaspersky.kaspresso.kaspresso

import android.app.Instrumentation
import androidx.test.espresso.Espresso
import androidx.test.espresso.FailureHandler
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.Configurator
import androidx.test.uiautomator.UiDevice
import com.agoda.kakao.Kakao
import com.kaspersky.components.kautomator.intercept.interaction.UiDeviceInteraction
import com.kaspersky.components.kautomator.intercept.interaction.UiObjectInteraction
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
import com.kaspersky.kaspresso.device.languages.Language
import com.kaspersky.kaspresso.device.languages.LanguageImpl
import com.kaspersky.kaspresso.device.location.Location
import com.kaspersky.kaspresso.device.location.LocationImpl
import com.kaspersky.kaspresso.device.logcat.Logcat
import com.kaspersky.kaspresso.device.logcat.LogcatImpl
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
import com.kaspersky.kaspresso.device.screenshots.screenshotfiles.DefaultScreenshotDirectoryProvider
import com.kaspersky.kaspresso.device.screenshots.screenshotfiles.DefaultScreenshotNameProvider
import com.kaspersky.kaspresso.device.screenshots.screenshotmaker.CombinedScreenshotMaker
import com.kaspersky.kaspresso.device.screenshots.screenshotmaker.ExternalScreenshotMaker
import com.kaspersky.kaspresso.device.screenshots.screenshotmaker.InternalScreenshotMaker
import com.kaspersky.kaspresso.device.server.AdbServer
import com.kaspersky.kaspresso.device.server.AdbServerImpl
import com.kaspersky.kaspresso.failure.LoggingFailureHandler
import com.kaspersky.kaspresso.idlewaiting.KautomatorWaitForIdleSettings
import com.kaspersky.kaspresso.interceptors.behavior.DataBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behavior.ViewBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behavior.WebBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behavior.impl.autoscroll.AutoScrollViewBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behavior.impl.autoscroll.AutoScrollWebBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behavior.impl.flakysafety.FlakySafeDataBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behavior.impl.flakysafety.FlakySafeViewBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behavior.impl.flakysafety.FlakySafeWebBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behavior.impl.systemsafety.SystemDialogSafetyDataBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behavior.impl.systemsafety.SystemDialogSafetyViewBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behavior.impl.systemsafety.SystemDialogSafetyWebBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behaviorkautomator.DeviceBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behaviorkautomator.ObjectBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.autoscroll.AutoScrollObjectBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.flakysafety.FlakySafeDeviceBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.flakysafety.FlakySafeObjectBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.loader.UiObjectLoaderBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.systemsafety.SystemDialogSafetyDeviceBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.systemsafety.SystemDialogSafetyObjectBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.tolibrary.LibraryInterceptorsInjector.injectKaspressoInKakao
import com.kaspersky.kaspresso.interceptors.tolibrary.LibraryInterceptorsInjector.injectKaspressoInKautomator
import com.kaspersky.kaspresso.interceptors.tolibrary.kautomator.KautomatorDeviceInterceptor
import com.kaspersky.kaspresso.interceptors.tolibrary.kautomator.KautomatorObjectInterceptor
import com.kaspersky.kaspresso.interceptors.watcher.kautomator.DeviceWatcherInterceptor
import com.kaspersky.kaspresso.interceptors.watcher.kautomator.ObjectWatcherInterceptor
import com.kaspersky.kaspresso.interceptors.watcher.kautomator.impl.logging.LoggingDeviceWatcherInterceptor
import com.kaspersky.kaspresso.interceptors.watcher.kautomator.impl.logging.LoggingObjectWatcherInterceptor
import com.kaspersky.kaspresso.interceptors.watcher.testcase.StepWatcherInterceptor
import com.kaspersky.kaspresso.interceptors.watcher.testcase.TestRunWatcherInterceptor
import com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.defaults.DefaultTestRunWatcherInterceptor
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
import com.kaspersky.kaspresso.params.ContinuouslyParams
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
    internal val objectWatcherInterceptors: List<ObjectWatcherInterceptor>,
    internal val deviceWatcherInterceptors: List<DeviceWatcherInterceptor>,
    internal val viewBehaviorInterceptors: List<ViewBehaviorInterceptor>,
    internal val dataBehaviorInterceptors: List<DataBehaviorInterceptor>,
    internal val webBehaviorInterceptors: List<WebBehaviorInterceptor>,
    internal val objectBehaviorInterceptors: List<ObjectBehaviorInterceptor>,
    internal val deviceBehaviorInterceptors: List<DeviceBehaviorInterceptor>,
    internal val stepWatcherInterceptors: List<StepWatcherInterceptor>,
    internal val testRunWatcherInterceptors: List<TestRunWatcherInterceptor>
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
             * Default (preconfigured full-featured with screenshot functionality) [Builder] for test environment configuration.
             *
             * If you desire to override some variables in Configurator please use [customize] parameter here.
             *
             * The example is:
             * ```
             * class ComposeTest : TestCase(
             *     kaspressoBuilder = Kaspresso.Builder.default {
             *         adbServer = CustomAdbServerImpl()
             *     }
             * )
             * ```
             * In this case your implementation of ```adbServer``` will be used for initializing of all other variables.
             * So, your implementation has a priority over the default implementation while using [customize] parameter for the overriding.
             *
             * Otherwise, if you don't set custom implementation then default implementation of ```adbServer``` will be explored.
             *
             * Please, be aware, that overridings like:
             * ```
             * class ComposeTest : TestCase(
             *     kaspressoBuilder = Kaspresso.Builder.default().apply {
             *         adbServer = CustomAdbServerImpl()
             *     }
             * )
             * ```
             * don't give you desired effect! Yes, you have an possibility to use your custom ```adbServer```, but
             * in all interceptors and other variables default implementation of ```adbServer``` will be exploring.
             *
             * We strongly recommend to use ```apply``` construction in such cases as an updating of a list of interceptors or
             * to change the order of interceptors in the list (for example, it are variables like
             * ```viewActionWatcherInterceptors```, ```viewAssertionWatcherInterceptors```, etc).
             *
             *
             * @return the new instance of [Builder].
             */
            @Deprecated("Use `advanced()` builder.", ReplaceWith("Kaspresso.Builder.advanced(customize)"))
            fun default(customize: Builder.() -> Unit = {}): Builder = advanced(customize)

            /**
             * Simple (preconfigured with logging and flaky-safe features) [Builder] for test environment configuration.
             *
             * If you desire to override some variables in Configurator please use [customize] parameter here.
             *
             * The example is:
             * ```
             * class ComposeTest : TestCase(
             *     kaspressoBuilder = Kaspresso.Builder.simple {
             *         adbServer = CustomAdbServerImpl()
             *     }
             * )
             * ```
             * In this case your implementation of ```adbServer``` will be used for initializing of all other variables.
             * So, your implementation has a priority over the default implementation while using [customize] parameter for the overriding.
             *
             * Otherwise, if you don't set custom implementation then default implementation of ```adbServer``` will be explored.
             *
             * Please, be aware, that overridings like:
             * ```
             * class ComposeTest : TestCase(
             *     kaspressoBuilder = Kaspresso.Builder.simple().apply {
             *         adbServer = CustomAdbServerImpl()
             *     }
             * )
             * ```
             * don't give you desired effect! Yes, you have an possibility to use your custom ```adbServer```, but
             * in all interceptors and other variables default implementation of ```adbServer``` will be exploring.
             *
             * We strongly recommend to use ```apply``` construction in such cases as an updating of a list of interceptors or
             * to change the order of interceptors in the list (for example, it are variables like
             * ```viewActionWatcherInterceptors```, ```viewAssertionWatcherInterceptors```, etc).
             *
             * @return the new instance of [Builder].
             */
            fun simple(customize: Builder.() -> Unit = {}): Builder {
                return Builder().apply {

                    customize.invoke(this)
                    postInitVariables()
                    postDefaultInitInterceptors()

                    failureHandler = LoggingFailureHandler(libLogger)
                }
            }

            /**
             * Advanced (full-featured with screenshot functionality) [Builder] for test environment configuration.
             *
             * If you desire to override some variables in Configurator please use [customize] parameter here.
             *
             * The example is:
             * ```
             * class ComposeTest : TestCase(
             *     kaspressoBuilder = Kaspresso.Builder.advanced {
             *         adbServer = CustomAdbServerImpl()
             *     }
             * )
             * ```
             * In this case your implementation of ```adbServer``` will be used for initializing of all other variables.
             * So, your implementation has a priority over the default implementation while using [customize] parameter for the overriding.
             *
             * Otherwise, if you don't set custom implementation then default implementation of ```adbServer``` will be explored.
             *
             * Please, be aware, that overridings like:
             * ```
             * class ComposeTest : TestCase(
             *     kaspressoBuilder = Kaspresso.Builder.advanced().apply {
             *         adbServer = CustomAdbServerImpl()
             *     }
             * )
             * ```
             * don't give you desired effect! Yes, you have an possibility to use your custom ```adbServer```, but
             * in all interceptors and other variables default implementation of ```adbServer``` will be exploring.
             *
             * We strongly recommend to use ```apply``` construction in such cases as an updating of a list of interceptors or
             * to change the order of interceptors in the list (for example, it are variables like
             * ```viewActionWatcherInterceptors```, ```viewAssertionWatcherInterceptors```, etc).
             *
             * @return the new instance of [Builder].
             */
            fun advanced(customize: Builder.() -> Unit = {}): Builder {
                return simple(customize).apply {
                    stepWatcherInterceptors.add(ScreenshotStepWatcherInterceptor(screenshots))
                    testRunWatcherInterceptors.add(
                        TestRunnerScreenshotWatcherInterceptor(
                            screenshots
                        )
                    )
                }
            }
        }

        /**
         * Holds an implementation of [KautomatorWaitForIdleSettings] for external developer's usage in tests.
         * If it was not specified, the default implementation is used.
         */
        lateinit var kautomatorWaitForIdleSettings: KautomatorWaitForIdleSettings

        /**
         * Holds an implementation of [UiTestLogger] interface for inner Kaspresso usage.
         * If it was not specified, the default implementation with default tag is used.
         */
        lateinit var libLogger: UiTestLogger

        /**
         * Holds an implementation of [UiTestLogger] interface for external developer's usage in tests.
         * If it was not specified, the default implementation with default tag is used.
         */
        lateinit var testLogger: UiTestLogger

        private val instrumentation: Instrumentation = InstrumentationRegistry.getInstrumentation()
        private val uiDevice = UiDevice.getInstance(instrumentation)
        private val configurator = Configurator.getInstance()

        /**
         * Holds an implementation of [AdbServer] interface. If it was not specified, the default implementation is used.
         */
        lateinit var adbServer: AdbServer

        /**
         * Holds an implementation of [Apps] interface. If it was not specified, the default implementation is used.
         */
        lateinit var apps: Apps

        /**
         * Holds an implementation of [Activities] interface. If it was not specified, the default implementation is used.
         */
        lateinit var activities: Activities

        /**
         * Holds an implementation of [Files] interface. If it was not specified, the default implementation is used.
         */
        lateinit var files: Files

        /**
         * Holds an implementation of [Network] interface. If it was not specified, the default implementation is used.
         */
        lateinit var network: Network

        /**
         * Holds an implementation of [Phone] interface. If it was not specified, the default implementation is used.
         */
        lateinit var phone: Phone

        /**
         * Holds an implementation of [Location] interface. If it was not specified, the default implementation is used.
         */
        lateinit var location: Location

        /**
         * Holds an implementation of [Keyboard] interface. If it was not specified, the default implementation is used.
         */
        lateinit var keyboard: Keyboard

        /**
         * Holds an implementation of [Screenshots] interface. If it was not specified, the default implementation is used.
         */
        lateinit var screenshots: Screenshots

        /**
         * Holds an implementation of [Accessibility] interface. If it was not specified, the default implementation is used.
         */
        lateinit var accessibility: Accessibility

        /**
         * Holds an implementation of [Permissions] interface. If it was not specified, the default implementation is used.
         */
        lateinit var permissions: Permissions

        /**
         * Holds an implementation of [HackPermissions] interface. If it was not specified, the default implementation is used.
         */
        lateinit var hackPermissions: HackPermissions

        /**
         * Holds an implementation of [Exploit] interface. If it was not specified, the default implementation is used.
         */
        lateinit var exploit: Exploit

        /**
         * Holds an implementation of [Language] interface. If it was not specified, the default implementation is used.
         */
        lateinit var language: Language

        /**
         * Holds an implementation of [Logcat] interface. If it was not specified, the default implementation is used.
         */
        lateinit var logcat: Logcat

        /**
         * Holds the [FlakySafetyParams] for [com.kaspersky.kaspresso.flakysafety.FlakySafetyProvider]'s usage.
         * If it was not specified, the default implementation is used.
         */
        lateinit var flakySafetyParams: FlakySafetyParams

        /**
         * Holds the [ContinuouslyParams] for [com.kaspersky.kaspresso.flakysafety.ContinuouslyProvider]'s usage.
         * If it was not specified, the default implementation is used.
         */
        lateinit var continuouslyParams: ContinuouslyParams

        /**
         * Holds the [AutoScrollParams] for [com.kaspersky.kaspresso.autoscroll.AutoScrollProvider]'s usage.
         * If it was not specified, the default implementation is used.
         */
        lateinit var autoScrollParams: AutoScrollParams

        /**
         * Holds the [StepParams] for [com.kaspersky.kaspresso.testcases.core.step.StepsManager]'s usage.
         * If it was not specified, the default implementation is used.
         */
        lateinit var stepParams: StepParams

        /**
         * Holds the list of [ViewActionWatcherInterceptor]s.
         * If it was not specified, Kaspresso will use no [ViewActionWatcherInterceptor]s.
         * These interceptors are called by [com.kaspersky.kaspresso.proxy.ViewActionProxy]
         * before actual [androidx.test.espresso.ViewAction.perform] call.
         */
        lateinit var viewActionWatcherInterceptors: MutableList<ViewActionWatcherInterceptor>

        /**
         * Holds the list of [ViewAssertionWatcherInterceptor]s.
         * If it was not specified, Kaspresso will use no [ViewAssertionWatcherInterceptor]s.
         * These interceptors are called by [com.kaspersky.kaspresso.proxy.ViewAssertionProxy]
         * before actual [androidx.test.espresso.ViewAssertion.check] call.
         */
        lateinit var viewAssertionWatcherInterceptors: MutableList<ViewAssertionWatcherInterceptor>

        /**
         * Holds the list of [AtomWatcherInterceptor]s.
         * If it was not specified, Kaspresso will use no [AtomWatcherInterceptor]s.
         * These interceptors are called by [com.kaspersky.kaspresso.proxy.AtomProxy]
         * before [androidx.test.espresso.web.model.Atom] is actually called.
         */
        lateinit var atomWatcherInterceptors: MutableList<AtomWatcherInterceptor>

        /**
         * Holds the list of [WebAssertionWatcherInterceptor]s.
         * If it was not specified, Kaspresso will use no [WebAssertionWatcherInterceptor]s.
         * These interceptors are called by [androidx.test.espresso.web.assertion.WebAssertionProxy]
         * before actual [androidx.test.espresso.web.assertion.WebAssertion.checkResult] call.
         */
        lateinit var webAssertionWatcherInterceptors: MutableList<WebAssertionWatcherInterceptor>

        /**
         * Holds the list of [ObjectWatcherInterceptor]s.
         * If it was not specified, Kaspresso will use no [ObjectWatcherInterceptor]s.
         * These interceptors are called by [KautomatorObjectInterceptor]
         * before actual [UiObjectInteraction.check] and [UiObjectInteraction.perform] call.
         */
        lateinit var objectWatcherInterceptors: MutableList<ObjectWatcherInterceptor>

        /**
         * Holds the list of [DeviceWatcherInterceptor]s.
         * If it was not specified, Kaspresso will use no [DeviceWatcherInterceptor]s.
         * These interceptors are called by [KautomatorDeviceInterceptor]
         * before actual [UiDeviceInteraction.check] and [UiDeviceInteraction.perform] call.
         */
        lateinit var deviceWatcherInterceptors: MutableList<DeviceWatcherInterceptor>

        /**
         * Holds the list of [ViewBehaviorInterceptor]s.
         * If it was not specified, Kaspresso will use no [ViewBehaviorInterceptor]s.
         * These interceptors are called by [com.kaspersky.kaspresso.interceptors.tolibrary.impl.KakaoViewInterceptor]
         * before actual [androidx.test.espresso.ViewInteraction.perform] and
         * [androidx.test.espresso.ViewInteraction.check] calls.
         * Note that the order of [ViewBehaviorInterceptor]s in this list is significant: the first item wil be
         * at the lowest level of intercepting chain, and the last item will be at the highest level.
         * For example: the first item actually wraps the [androidx.test.espresso.ViewInteraction.perform] call,
         * the second item wraps the first item, and so on.
         */
        lateinit var viewBehaviorInterceptors: MutableList<ViewBehaviorInterceptor>

        /**
         * Holds the list of [DataBehaviorInterceptor]s.
         * If it was not specified, Kaspresso will use no [DataBehaviorInterceptor]s.
         * These interceptors are called by [com.kaspersky.kaspresso.interceptors.tolibrary.impl.KakaoDataInterceptor]
         * before actual [androidx.test.espresso.DataInteraction.check] call.
         * Note that the order of [DataBehaviorInterceptor]s in this list is significant: the first item wil be
         * at the lowest level of intercepting chain, and the last item will be at the highest level.
         * For example: the first item actually wraps the [androidx.test.espresso.DataInteraction.check] call,
         * the second item wraps the first item, and so on.
         */
        lateinit var dataBehaviorInterceptors: MutableList<DataBehaviorInterceptor>

        /**
         * Holds the list of [WebBehaviorInterceptor]s.
         * If it was not specified, Kaspresso will use no [WebBehaviorInterceptor]s.
         * These interceptors are called by [com.kaspersky.kaspresso.interceptors.tolibrary.impl.KakaoWebInterceptor]
         * before actual [androidx.test.espresso.web.sugar.Web.WebInteraction.perform] and
         * [androidx.test.espresso.web.sugar.Web.WebInteraction.check] calls.
         * Note that the order of [WebBehaviorInterceptor]s in this list is significant: the first item wil be
         * at the lowest level of intercepting chain, and the last item will be at the highest level.
         * For example: the first item actually wraps the [androidx.test.espresso.web.sugar.Web.WebInteraction.perform]
         * call, the second item wraps the first item, and so on.
         */
        lateinit var webBehaviorInterceptors: MutableList<WebBehaviorInterceptor>

        /**
         * Holds the list of [ObjectBehaviorInterceptor]s.
         * If it was not specified, Kaspresso will use no [ObjectBehaviorInterceptor]s.
         * These interceptors are called by [KautomatorDeviceInterceptor]
         * before actual [UiDeviceInteraction.check] and [UiDeviceInteraction.perform] call.
         * Note that the order of [ObjectBehaviorInterceptor]s in this list is significant: the first item wil be
         * at the lowest level of intercepting chain, and the last item will be at the highest level.
         * For example: the first item actually wraps the [UiDeviceInteraction.check] or [UiDeviceInteraction.perform]
         * call, the second item wraps the first item, and so on.
         */
        lateinit var objectBehaviorInterceptors: MutableList<ObjectBehaviorInterceptor>

        /**
         * Holds the list of [DeviceBehaviorInterceptor]s.
         * If it was not specified, Kaspresso will use no [DeviceBehaviorInterceptor]s.
         * These interceptors are called by [KautomatorObjectInterceptor]
         * before actual [UiObjectInteraction.check] and [UiObjectInteraction.perform] call.
         * Note that the order of [DeviceBehaviorInterceptor]s in this list is significant: the first item wil be
         * at the lowest level of intercepting chain, and the last item will be at the highest level.
         * For example: the first item actually wraps the [UiObjectInteraction.check] or [UiObjectInteraction.perform]
         * call, the second item wraps the first item, and so on.
         */
        lateinit var deviceBehaviorInterceptors: MutableList<DeviceBehaviorInterceptor>

        /**
         * Holds the list of [StepWatcherInterceptor]s.
         * If it was not specified, Kaspresso will use no [StepWatcherInterceptor]s.
         * These interceptors are called by [com.kaspersky.kaspresso.testcases.core.testcontext.TestContext] in "step"
         * method on both "step started", "step finished with success", "step finished with failure" and
         * "step finally finished" events.
         */
        lateinit var stepWatcherInterceptors: MutableList<StepWatcherInterceptor>

        /**
         * Holds the list of [TestRunWatcherInterceptor]s.
         * If it was not specified, Kaspresso will use no [TestRunWatcherInterceptor]s.
         * These interceptors are called by [com.kaspersky.kaspresso.testcases.core.TestRunner] in "run"
         * method on "test started", on all [com.kaspersky.kaspresso.testcases.core.sections]' "section started",
         * "section finished with success" and "section finished with failure", and "test finished" events.
         */
        lateinit var testRunWatcherInterceptors: MutableList<TestRunWatcherInterceptor>

        /**
         * Holds the implementation of the [androidx.test.espresso.FailureHandler] interface, that is called on every
         * failure.
         */
        var failureHandler: FailureHandler? = null

        private val defaultsTestRunWatcherInterceptor = DefaultTestRunWatcherInterceptor()

        /**
         * Set the action which will be executed before the test.
         * The action has access to BaseTestContext.
         * If you set @param override in false then the final beforeAction will be
         *     beforeAction of the parent TestCase plus current @param action.
         *     Otherwise final beforeAction will be only @param action.
         */
        fun beforeEachTest(override: Boolean = false, action: BaseTestContext.() -> Unit) {
            defaultsTestRunWatcherInterceptor.beforeEachTest(override, action)
        }

        /**
         * Set the action which will be executed after the test.
         * The action has access to BaseTestContext.
         * If you set @param override in false then the final beforeAction will be
         *     beforeAction of the parent TestCase plus current @param action.
         *     Otherwise final beforeAction will be only @param action.
         */
        fun afterEachTest(override: Boolean = false, action: BaseTestContext.() -> Unit) {
            defaultsTestRunWatcherInterceptor.afterEachTest(override, action)
        }

        @Suppress("detekt.ComplexMethod")
        private fun postInitVariables() {
            if (!::kautomatorWaitForIdleSettings.isInitialized) kautomatorWaitForIdleSettings = KautomatorWaitForIdleSettings.default()

            if (!::libLogger.isInitialized) libLogger = UiTestLoggerImpl(DEFAULT_LIB_LOGGER_TAG)
            if (!::testLogger.isInitialized) testLogger = UiTestLoggerImpl(DEFAULT_TEST_LOGGER_TAG)

            if (!::adbServer.isInitialized) adbServer = AdbServerImpl(libLogger)
            if (!::apps.isInitialized) apps = AppsImpl(libLogger, instrumentation.context, uiDevice, adbServer)
            if (!::activities.isInitialized) activities = ActivitiesImpl(libLogger)
            if (!::files.isInitialized) files = FilesImpl(adbServer)
            if (!::network.isInitialized) network = NetworkImpl(instrumentation.targetContext, adbServer)
            if (!::phone.isInitialized) phone = PhoneImpl(adbServer)
            if (!::location.isInitialized) location = LocationImpl(adbServer)
            if (!::keyboard.isInitialized) keyboard = KeyboardImpl(adbServer)
            if (!::screenshots.isInitialized) {
                screenshots = ScreenshotsImpl(
                    libLogger,
                    CombinedScreenshotMaker(InternalScreenshotMaker(activities), ExternalScreenshotMaker()),
                    DefaultScreenshotDirectoryProvider(groupByRunNumbers = true),
                    DefaultScreenshotNameProvider(addTimestamps = false)
                )
            }
            if (!::accessibility.isInitialized) accessibility = AccessibilityImpl()
            if (!::permissions.isInitialized) permissions = PermissionsImpl(libLogger, uiDevice)
            if (!::hackPermissions.isInitialized) hackPermissions = HackPermissionsImpl(instrumentation.uiAutomation, libLogger)
            if (!::exploit.isInitialized) exploit = ExploitImpl(activities, uiDevice, adbServer)
            if (!::language.isInitialized) language = LanguageImpl(libLogger, instrumentation.targetContext)
            if (!::logcat.isInitialized) logcat = LogcatImpl()

            if (!::flakySafetyParams.isInitialized) flakySafetyParams = FlakySafetyParams.default()
            if (!::continuouslyParams.isInitialized) continuouslyParams = ContinuouslyParams.default()
            if (!::autoScrollParams.isInitialized) autoScrollParams = AutoScrollParams.default()
            if (!::stepParams.isInitialized) stepParams = StepParams()
        }

        @Suppress("detekt.ComplexMethod")
        private fun postBaseInitInterceptors() {
            if (!::viewActionWatcherInterceptors.isInitialized) viewActionWatcherInterceptors = mutableListOf()
            if (!::viewAssertionWatcherInterceptors.isInitialized) viewAssertionWatcherInterceptors = mutableListOf()
            if (!::atomWatcherInterceptors.isInitialized) atomWatcherInterceptors = mutableListOf()
            if (!::webAssertionWatcherInterceptors.isInitialized) webAssertionWatcherInterceptors = mutableListOf()
            if (!::objectWatcherInterceptors.isInitialized) objectWatcherInterceptors = mutableListOf()
            if (!::deviceWatcherInterceptors.isInitialized) deviceWatcherInterceptors = mutableListOf()
            if (!::viewBehaviorInterceptors.isInitialized) viewBehaviorInterceptors = mutableListOf()
            if (!::dataBehaviorInterceptors.isInitialized) dataBehaviorInterceptors = mutableListOf()
            if (!::webBehaviorInterceptors.isInitialized) webBehaviorInterceptors = mutableListOf()
            if (!::objectBehaviorInterceptors.isInitialized) objectBehaviorInterceptors = mutableListOf()
            if (!::deviceBehaviorInterceptors.isInitialized) deviceBehaviorInterceptors = mutableListOf()
            if (!::stepWatcherInterceptors.isInitialized) stepWatcherInterceptors = mutableListOf()
            if (!::testRunWatcherInterceptors.isInitialized) testRunWatcherInterceptors = mutableListOf()
        }

        @Suppress("detekt.ComplexMethod")
        private fun postDefaultInitInterceptors() {
            if (!::viewActionWatcherInterceptors.isInitialized) viewActionWatcherInterceptors = mutableListOf(
                LoggingViewActionWatcherInterceptor(libLogger)
            )

            if (!::viewAssertionWatcherInterceptors.isInitialized) viewAssertionWatcherInterceptors = mutableListOf(
                LoggingViewAssertionWatcherInterceptor(libLogger)
            )

            if (!::atomWatcherInterceptors.isInitialized) atomWatcherInterceptors = mutableListOf(
                LoggingAtomWatcherInterceptor(libLogger)
            )

            if (!::webAssertionWatcherInterceptors.isInitialized) webAssertionWatcherInterceptors = mutableListOf(
                LoggingWebAssertionWatcherInterceptor(libLogger)
            )

            if (!::objectWatcherInterceptors.isInitialized) objectWatcherInterceptors = mutableListOf(
                LoggingObjectWatcherInterceptor(libLogger)
            )

            if (!::deviceWatcherInterceptors.isInitialized) deviceWatcherInterceptors = mutableListOf(
                LoggingDeviceWatcherInterceptor(libLogger)
            )

            if (!::viewBehaviorInterceptors.isInitialized) viewBehaviorInterceptors = mutableListOf(
                AutoScrollViewBehaviorInterceptor(autoScrollParams, libLogger),
                SystemDialogSafetyViewBehaviorInterceptor(libLogger, uiDevice, adbServer),
                FlakySafeViewBehaviorInterceptor(flakySafetyParams, libLogger)
            )

            if (!::dataBehaviorInterceptors.isInitialized) dataBehaviorInterceptors = mutableListOf(
                SystemDialogSafetyDataBehaviorInterceptor(libLogger, uiDevice, adbServer),
                FlakySafeDataBehaviorInterceptor(flakySafetyParams, libLogger)
            )

            if (!::webBehaviorInterceptors.isInitialized) webBehaviorInterceptors = mutableListOf(
                AutoScrollWebBehaviorInterceptor(autoScrollParams, libLogger),
                SystemDialogSafetyWebBehaviorInterceptor(libLogger, uiDevice, adbServer),
                FlakySafeWebBehaviorInterceptor(flakySafetyParams, libLogger)
            )

            if (!::objectBehaviorInterceptors.isInitialized) objectBehaviorInterceptors = mutableListOf(
                AutoScrollObjectBehaviorInterceptor(libLogger, autoScrollParams),
                UiObjectLoaderBehaviorInterceptor(libLogger),
                SystemDialogSafetyObjectBehaviorInterceptor(libLogger, uiDevice, adbServer),
                FlakySafeObjectBehaviorInterceptor(flakySafetyParams, libLogger)
            )

            if (!::deviceBehaviorInterceptors.isInitialized) deviceBehaviorInterceptors = mutableListOf(
                SystemDialogSafetyDeviceBehaviorInterceptor(libLogger, uiDevice, adbServer),
                FlakySafeDeviceBehaviorInterceptor(flakySafetyParams, libLogger)
            )

            if (!::stepWatcherInterceptors.isInitialized) stepWatcherInterceptors = mutableListOf(
                LoggingStepWatcherInterceptor(libLogger)
            )

            if (!::testRunWatcherInterceptors.isInitialized) testRunWatcherInterceptors = mutableListOf(
                TestRunLoggerWatcherInterceptor(libLogger),
                BuildStepReportWatcherInterceptor(AllureReportWriter(libLogger)),
                defaultsTestRunWatcherInterceptor
            )
        }

        /**
         * Terminating method to build [Kaspresso] instance. Can be called only inside the framework package.
         * Actually called when the base [com.kaspersky.kaspresso.testcases.api.testcase.BaseTestCase] class is
         * instantiated.
         *
         * @return build [Kaspresso] instance.
         */
        internal fun build(): Kaspresso {
            postInitVariables()
            postBaseInitInterceptors()

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
                    exploit = exploit,
                    language = language,
                    logcat = logcat
                ),

                params = Params(
                    flakySafetyParams = flakySafetyParams,
                    continuouslyParams = continuouslyParams,
                    autoScrollParams = autoScrollParams,
                    stepParams = stepParams
                ),

                viewActionWatcherInterceptors = viewActionWatcherInterceptors,
                viewAssertionWatcherInterceptors = viewAssertionWatcherInterceptors,
                atomWatcherInterceptors = atomWatcherInterceptors,
                webAssertionWatcherInterceptors = webAssertionWatcherInterceptors,

                objectWatcherInterceptors = objectWatcherInterceptors,
                deviceWatcherInterceptors = deviceWatcherInterceptors,

                viewBehaviorInterceptors = viewBehaviorInterceptors,
                dataBehaviorInterceptors = dataBehaviorInterceptors,
                webBehaviorInterceptors = webBehaviorInterceptors,

                objectBehaviorInterceptors = objectBehaviorInterceptors,
                deviceBehaviorInterceptors = deviceBehaviorInterceptors,

                stepWatcherInterceptors = stepWatcherInterceptors,
                testRunWatcherInterceptors = testRunWatcherInterceptors
            )

            configurator.waitForIdleTimeout = kautomatorWaitForIdleSettings.waitForIdleTimeout
            configurator.waitForSelectorTimeout = kautomatorWaitForIdleSettings.waitForSelectorTimeout

            injectKaspressoInKakao(
                kaspresso.viewBehaviorInterceptors,
                kaspresso.dataBehaviorInterceptors,
                kaspresso.webBehaviorInterceptors,
                kaspresso.viewActionWatcherInterceptors,
                kaspresso.viewAssertionWatcherInterceptors,
                kaspresso.atomWatcherInterceptors,
                kaspresso.webAssertionWatcherInterceptors
            )

            injectKaspressoInKautomator(
                kaspresso.objectBehaviorInterceptors,
                kaspresso.deviceBehaviorInterceptors,
                kaspresso.objectWatcherInterceptors,
                kaspresso.deviceWatcherInterceptors
            )

            failureHandler?.let { Espresso.setFailureHandler(it) }

            return kaspresso
        }
    }

    internal fun reset(): Unit = Kakao.reset()
}