package com.kaspersky.kaspresso.kaspresso

import androidx.test.espresso.Espresso
import androidx.test.espresso.FailureHandler
import androidx.test.uiautomator.Configurator
import androidx.test.uiautomator.UiDevice
import com.kaspersky.kaspresso.device.Device
import com.kaspersky.kaspresso.device.accessibility.Accessibility
import com.kaspersky.kaspresso.device.activities.Activities
import com.kaspersky.kaspresso.device.apps.Apps
import com.kaspersky.kaspresso.device.exploit.Exploit
import com.kaspersky.kaspresso.device.files.Files
import com.kaspersky.kaspresso.device.keyboard.Keyboard
import com.kaspersky.kaspresso.device.languages.Language
import com.kaspersky.kaspresso.device.location.Location
import com.kaspersky.kaspresso.device.logcat.Logcat
import com.kaspersky.kaspresso.device.logcat.dumper.LogcatDumper
import com.kaspersky.kaspresso.device.logcat.dumper.LogcatDumperImpl
import com.kaspersky.kaspresso.device.network.Network
import com.kaspersky.kaspresso.device.permissions.HackPermissions
import com.kaspersky.kaspresso.device.permissions.Permissions
import com.kaspersky.kaspresso.device.phone.Phone
import com.kaspersky.kaspresso.device.screenshots.Screenshots
import com.kaspersky.kaspresso.device.server.AdbServer
import com.kaspersky.kaspresso.device.video.Videos
import com.kaspersky.kaspresso.device.viewhierarchy.ViewHierarchyDumper
import com.kaspersky.kaspresso.failure.LoggingFailureHandler
import com.kaspersky.kaspresso.files.dirs.DefaultDirsProvider
import com.kaspersky.kaspresso.files.dirs.DirsProvider
import com.kaspersky.kaspresso.files.resources.*
import com.kaspersky.kaspresso.files.resources.impl.*
import com.kaspersky.kaspresso.idlewaiting.KautomatorWaitForIdleSettings
import com.kaspersky.kaspresso.interceptors.behavior.DataBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behavior.ViewBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behavior.WebBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behaviorkautomator.DeviceBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behaviorkautomator.ObjectBehaviorInterceptor
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
import com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.logging.DumpLogcatInterceptor
import com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.logging.LoggingStepWatcherInterceptor
import com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.logging.TestRunLoggerWatcherInterceptor
import com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.screenshot.ScreenshotStepWatcherInterceptor
import com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.screenshot.TestRunnerScreenshotWatcherInterceptor
import com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.video.VideoRecordingInterceptor
import com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.views.DumpViewsInterceptor
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
import com.kaspersky.kaspresso.params.*
import com.kaspersky.kaspresso.testcases.core.testcontext.BaseTestContext
import io.github.kakaocup.kakao.Kakao

/**
 * The storage of all Kaspresso preferences and entities, such as [AdbServer], [Device] and different interceptors.
 */
data class Kaspresso(
    internal val libLogger: UiTestLogger,
    internal val testLogger: UiTestLogger,
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

    //Kautomator dependent
    internal val adbServer: AdbServer,
    internal val device: Device,
    internal val objectWatcherInterceptors: List<ObjectWatcherInterceptor>,
    internal val deviceWatcherInterceptors: List<DeviceWatcherInterceptor>,
    internal val objectBehaviorInterceptors: List<ObjectBehaviorInterceptor>,
    internal val deviceBehaviorInterceptors: List<DeviceBehaviorInterceptor>,
) {

    companion object {
        const val DEFAULT_LIB_LOGGER_TAG: String = "KASPRESSO"
        const val DEFAULT_TEST_LOGGER_TAG: String = "KASPRESSO_TEST"
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
            fun default(sharedTest: Boolean = false, customize: Builder.() -> Unit = {}): Builder = advanced(sharedTest, customize)

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
            fun simple(sharedTest: Boolean = false, customize: Builder.() -> Unit = {}): Builder {
                return Builder().apply {

                    kautomatorConfig = KautomatorConfigResolver.build(sharedTest)
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
            fun advanced(sharedTest: Boolean = false, customize: Builder.() -> Unit = {}): Builder {
                return simple(sharedTest, customize).apply {
                    kautomatorConfig = KautomatorConfigResolver.build(sharedTest)
                    stepWatcherInterceptors.add(ScreenshotStepWatcherInterceptor(screenshots))
                    testRunWatcherInterceptors.addAll(
                        listOf(
                            TestRunnerScreenshotWatcherInterceptor(screenshots),
                            VideoRecordingInterceptor(videos),
                            DumpLogcatInterceptor(logcatDumper),
                            DumpViewsInterceptor(viewHierarchyDumper)
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
         * Holds an implementation of [Screenshots] interface. If it was not specified, the default implementation is used.
         */
        lateinit var screenshots: Screenshots

        /**
         * Holds an implementation of [Videos] interface. If it was not specified, the default implementation is used.
         */
        lateinit var videos: Videos

        /**
         * Holds an implementation of [ViewHierarchyDumper] interface. If it was not specified, the default implementation is used.
         */
        lateinit var viewHierarchyDumper: ViewHierarchyDumper

        /**
         * Holds an implementation of [LogcatDumper] interface. If it was not specified, the default implementation is used.
         */
        lateinit var logcatDumper: LogcatDumper

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
         * Holds the [ScreenshotParams] for [com.kaspersky.kaspresso.device.screenshots.screenshotmaker.InternalScreenshotMaker]'s and
         * [com.kaspersky.kaspresso.device.screenshots.screenshotmaker.ExternalScreenshotMaker] usage.
         * If it was not specified, the default implementation is used.
         */
        lateinit var screenshotParams: ScreenshotParams

        /**
         * Holds the [VideoParams] for [com.kaspersky.kaspresso.device.video.recorder.VideoRecorder]'s usage.
         * If it was not specified, the default implementation is used.
         */
        lateinit var videoParams: VideoParams

        /**
         * Holds an implementation of [DirsProvider] interface. If it was not specified, the default implementation is used.
         */
        lateinit var dirsProvider: DirsProvider

        /**
         * Holds an implementation of [ResourceFilesProvider] interface. If it was not specified, the default implementation is used.
         */
        lateinit var resourceFilesProvider: ResourceFilesProvider

        /**
         * Holds an implementation of [ResourceFileNamesProvider] interface. If it was not specified, the default implementation is used.
         */
        lateinit var resourceFileNamesProvider: ResourceFileNamesProvider

        /**
         * Holds an implementation of [ResourcesDirNameProvider] interface. If it was not specified, the default implementation is used.
         */
        lateinit var resourcesDirNameProvider: ResourcesDirNameProvider

        /**
         * Holds an implementation of [ResourcesDirsProvider] interface. If it was not specified, the default implementation is used.
         */
        lateinit var resourcesDirsProvider: ResourcesDirsProvider

        /**
         * Holds an implementation of [ResourcesRootDirsProvider] interface. If it was not specified, the default implementation is used.
         */
        lateinit var resourcesRootDirsProvider: ResourcesRootDirsProvider

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

        /**
         * Sets Kautomator based on sharedTest support (whether tests can ALSO run on the JVM or ONLY on as instrumentation test on devices/emulators)
         * This is necessary since Robolectric is compatible with Espresso but not with UiAutomator
         */
        lateinit var kautomatorConfig: KautomatorConfig

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

        /**
         * Holds an implementation of [UiTestLogger] interface for external developer's usage in tests.
         * If it was not specified, the default implementation with default tag is used.
         */
        lateinit var testLogger: UiTestLogger

        /**
         * UiDevice can only be accessed from Instrumentation tests and never from shared test running with Robolectric
         */
        var uiDevice: UiDevice? = null

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


        @Suppress("detekt.ComplexMethod")
        private fun postInitVariables() {

            if (!::libLogger.isInitialized) libLogger = UiTestLoggerImpl(DEFAULT_LIB_LOGGER_TAG)
            if (!::testLogger.isInitialized) testLogger = UiTestLoggerImpl(DEFAULT_TEST_LOGGER_TAG)

            if (!::flakySafetyParams.isInitialized) flakySafetyParams = FlakySafetyParams.default()
            if (!::continuouslyParams.isInitialized) continuouslyParams = ContinuouslyParams.default()
            if (!::autoScrollParams.isInitialized) autoScrollParams = AutoScrollParams.default()
            if (!::stepParams.isInitialized) stepParams = StepParams()

            // allure
            if (!::dirsProvider.isInitialized) dirsProvider = DefaultDirsProvider()
            if (!::resourcesRootDirsProvider.isInitialized) resourcesRootDirsProvider = DefaultResourcesRootDirsProvider()
            if (!::resourcesDirNameProvider.isInitialized) resourcesDirNameProvider = DefaultResourcesDirNameProvider()
            if (!::resourcesDirsProvider.isInitialized) {
                resourcesDirsProvider = DefaultResourcesDirsProvider(
                    dirsProvider = dirsProvider,
                    resourcesDirNameProvider = resourcesDirNameProvider
                )
            }
            if (!::resourceFileNamesProvider.isInitialized) {
                resourceFileNamesProvider = DefaultResourceFileNamesProvider(addTimestamps = false)
            }
            if (!::resourceFilesProvider.isInitialized) {
                resourceFilesProvider = DefaultResourceFilesProvider(
                    resourcesRootDirsProvider,
                    resourcesDirsProvider,
                    resourceFileNamesProvider
                )
            }

            // kautomator
            if (!::kautomatorWaitForIdleSettings.isInitialized) kautomatorWaitForIdleSettings = KautomatorWaitForIdleSettings.default()
            if (!::adbServer.isInitialized) adbServer = kautomatorConfig.getAdbServer(libLogger)
            if (!::apps.isInitialized) apps = kautomatorConfig.getApps(libLogger, adbServer)
            if (!::activities.isInitialized) activities = kautomatorConfig.getActivities(libLogger)
            if (!::files.isInitialized) files = kautomatorConfig.getFiles(adbServer)
            if (!::network.isInitialized) network = kautomatorConfig.getNetwork(libLogger, adbServer)
            if (!::phone.isInitialized) phone = kautomatorConfig.getPhone(adbServer)
            if (!::location.isInitialized) location = kautomatorConfig.getLocation(adbServer)
            if (!::keyboard.isInitialized) keyboard = kautomatorConfig.getKeyboard(adbServer)
            if (!::accessibility.isInitialized) accessibility = kautomatorConfig.getAccessibility()
            if (!::permissions.isInitialized) permissions = kautomatorConfig.getPermissions(libLogger)
            if (!::hackPermissions.isInitialized) hackPermissions = kautomatorConfig.getHackPermissions(libLogger)
            if (!::exploit.isInitialized) exploit = kautomatorConfig.getExploit(adbServer, activities)
            if (!::language.isInitialized) language = kautomatorConfig.getLanguage(libLogger)
            if (!::logcat.isInitialized) logcat = kautomatorConfig.getLogcat(adbServer)

            if (!::screenshotParams.isInitialized) screenshotParams = ScreenshotParams()
            if (!::videoParams.isInitialized) videoParams = VideoParams()

            if (!::screenshots.isInitialized) {
                screenshots = kautomatorConfig.getScreenshots(resourceFilesProvider, libLogger, activities, screenshotParams)
            }

            if (!::videos.isInitialized) {
                videos = kautomatorConfig.getVideos(resourceFilesProvider, libLogger, videoParams)
            }

            if (!::viewHierarchyDumper.isInitialized) {
                viewHierarchyDumper = kautomatorConfig.getViewHierarchyDumper(libLogger, resourceFilesProvider)
            }

            if (!::logcatDumper.isInitialized) {
                logcatDumper = LogcatDumperImpl(
                    libLogger,
                    resourceFilesProvider,
                    logcat,
                    listOf(DEFAULT_LIB_LOGGER_TAG, DEFAULT_TEST_LOGGER_TAG)
                )
            }
        }

        @Suppress("detekt.ComplexMethod")
        private fun postBaseInitInterceptors() {
            if (!::objectWatcherInterceptors.isInitialized) objectWatcherInterceptors = mutableListOf()
            if (!::deviceWatcherInterceptors.isInitialized) deviceWatcherInterceptors = mutableListOf()
            if (!::objectBehaviorInterceptors.isInitialized) objectBehaviorInterceptors = mutableListOf()
            if (!::deviceBehaviorInterceptors.isInitialized) deviceBehaviorInterceptors = mutableListOf()

            if (!::viewActionWatcherInterceptors.isInitialized) viewActionWatcherInterceptors = mutableListOf()
            if (!::viewAssertionWatcherInterceptors.isInitialized) viewAssertionWatcherInterceptors = mutableListOf()
            if (!::atomWatcherInterceptors.isInitialized) atomWatcherInterceptors = mutableListOf()
            if (!::webAssertionWatcherInterceptors.isInitialized) webAssertionWatcherInterceptors = mutableListOf()
            if (!::viewBehaviorInterceptors.isInitialized) viewBehaviorInterceptors = mutableListOf()
            if (!::dataBehaviorInterceptors.isInitialized) dataBehaviorInterceptors = mutableListOf()
            if (!::webBehaviorInterceptors.isInitialized) webBehaviorInterceptors = mutableListOf()
            if (!::stepWatcherInterceptors.isInitialized) stepWatcherInterceptors = mutableListOf()
            if (!::testRunWatcherInterceptors.isInitialized) testRunWatcherInterceptors = mutableListOf()
        }

        @Suppress("detekt.ComplexMethod")
        private fun postDefaultInitInterceptors() {
            if (!::objectWatcherInterceptors.isInitialized) objectWatcherInterceptors = mutableListOf(
                LoggingObjectWatcherInterceptor(libLogger)
            )

            if (!::deviceWatcherInterceptors.isInitialized) deviceWatcherInterceptors = mutableListOf(
                LoggingDeviceWatcherInterceptor(libLogger)
            )

            if (!::objectBehaviorInterceptors.isInitialized) objectBehaviorInterceptors =
                kautomatorConfig.getObjectBehaviourInterceptors(libLogger, adbServer, autoScrollParams, flakySafetyParams)

            if (!::deviceBehaviorInterceptors.isInitialized) deviceBehaviorInterceptors =
                kautomatorConfig.getDeviceBehaviourInterceptors(libLogger, adbServer, flakySafetyParams)

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

            if (!::viewBehaviorInterceptors.isInitialized) viewBehaviorInterceptors =
                kautomatorConfig.getViewBehaviourInterceptors(libLogger, adbServer, autoScrollParams, flakySafetyParams)

            if (!::dataBehaviorInterceptors.isInitialized) dataBehaviorInterceptors =
                kautomatorConfig.getDataBehaviourInterceptors(libLogger, adbServer, autoScrollParams, flakySafetyParams)

            if (!::webBehaviorInterceptors.isInitialized) webBehaviorInterceptors =
                kautomatorConfig.getWebBehaviourInterceptors(libLogger, adbServer, autoScrollParams, flakySafetyParams)

            if (!::stepWatcherInterceptors.isInitialized) stepWatcherInterceptors = mutableListOf(
                LoggingStepWatcherInterceptor(libLogger)
            )

            if (!::testRunWatcherInterceptors.isInitialized) testRunWatcherInterceptors = mutableListOf(
                TestRunLoggerWatcherInterceptor(libLogger),
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

                params = Params(
                    flakySafetyParams = flakySafetyParams,
                    continuouslyParams = continuouslyParams,
                    autoScrollParams = autoScrollParams,
                    stepParams = stepParams,
                    screenshotParams = screenshotParams,
                    videoParams = videoParams
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

                adbServer = kautomatorConfig.getAdbServer(libLogger),
                device = kautomatorConfig.getDevice(resourceFilesProvider,libLogger, adbServer, activities, screenshotParams),

                objectWatcherInterceptors = emptyList(),
                deviceWatcherInterceptors = emptyList(),

                objectBehaviorInterceptors = emptyList(),
                deviceBehaviorInterceptors = emptyList(),
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
