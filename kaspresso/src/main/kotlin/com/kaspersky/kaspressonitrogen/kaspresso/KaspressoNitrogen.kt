package com.kaspersky.kaspressonitrogen.kaspresso

import androidx.test.espresso.FailureHandler
import com.agoda.kakao.Kakao


import com.kaspersky.kaspresso.failure.LoggingFailureHandler
import com.kaspersky.kaspresso.interceptors.behavior.DataBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behavior.ViewBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behavior.WebBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behavior.impl.autoscroll.AutoScrollViewBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behavior.impl.autoscroll.AutoScrollWebBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behavior.impl.flakysafety.FlakySafeDataBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behavior.impl.flakysafety.FlakySafeViewBehaviorInterceptor
import com.kaspersky.kaspresso.interceptors.behavior.impl.flakysafety.FlakySafeWebBehaviorInterceptor

import com.kaspersky.kaspresso.interceptors.tolibrary.LibraryInterceptorsInjector
import com.kaspersky.kaspresso.interceptors.watcher.testcase.NitrogenTestRunWatcherInterceptor

import com.kaspersky.kaspresso.interceptors.watcher.testcase.StepWatcherInterceptor
import com.kaspersky.kaspressonitrogen.interceptors.watcher.testcase.impl.defaults.NitrogenDefaultTestRunWatcherInterceptor
import com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.logging.LoggingStepWatcherInterceptor
import com.kaspersky.kaspressonitrogen.interceptors.watcher.testcase.impl.logging.NitrogenTestRunLoggerWatcherInterceptor
import com.kaspersky.kaspressonitrogen.interceptors.watcher.testcase.impl.report.NitrogenBuildStepReportWatcherInterceptor
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
import com.kaspersky.kaspresso.report.impl.AllureReportWriter
import com.kaspersky.kaspressonitrogen.testcases.core.testcontext.NitrogenBaseTestContext

class KaspressoNitrogen(
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
    internal val testRunWatcherInterceptors: List<NitrogenTestRunWatcherInterceptor>
) {

    companion object {
        const val DEFAULT_LIB_LOGGER_TAG: String = "KASPRESSO"
        const val DEFAULT_TEST_LOGGER_TAG: String = "KASPRESSO_TEST"
    }

    /**
     * The class for [KaspressoNitrogen] configuration and initialization. This is the way to set Kaspresso preferences.
     */
    class Builder {

        companion object {

            /**
             * Simple (preconfigured with logging and flaky-safe features) [Builder] for test environment configuration.
             *
             * If you desire to override some variables in Configurator please use [customize] parameter here.
             *
             * The example is:
             * ```
             * class ComposeTest : TestCase(
             *     kaspressoBuilder = KaspressoNitrogen.Builder.simple()
             * )
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
        }

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
         * Holds the list of [StepWatcherInterceptor]s.
         * If it was not specified, Kaspresso will use no [StepWatcherInterceptor]s.
         * These interceptors are called by [com.kaspersky.nitrogenkaspresso.testcases.core.testcontext.NitrogenTestContext] in "step"
         * method on both "step started", "step finished with success", "step finished with failure" and
         * "step finally finished" events.
         */
        lateinit var stepWatcherInterceptors: MutableList<StepWatcherInterceptor>

        /**
         * Holds the list of [NitrogenTestRunWatcherInterceptor]s.
         * If it was not specified, Kaspresso will use no [NitrogenTestRunWatcherInterceptor]s.
         * These interceptors are called by [com.kaspersky.kaspressonitrogen.testcases.core.NitrogenTestRunner] in "run"
         * method on "test started", on all [com.kaspersky.kaspressonitrogen.testcases.core.sections]' "section started",
         * "section finished with success" and "section finished with failure", and "test finished" events.
         */
        lateinit var testRunWatcherInterceptors: MutableList<NitrogenTestRunWatcherInterceptor>

        /**
         * Holds the implementation of the [androidx.test.espresso.FailureHandler] interface, that is called on every
         * failure.
         */
        var failureHandler: FailureHandler? = null

        private val defaultsTestRunWatcherInterceptor = NitrogenDefaultTestRunWatcherInterceptor()

        /**
         * Set the action which will be executed before the test.
         * The action has access to BaseTestContext.
         * If you set @param override in false then the final beforeAction will be
         *     beforeAction of the parent TestCase plus current @param action.
         *     Otherwise final beforeAction will be only @param action.
         */
        fun beforeEachTest(override: Boolean = false, action: NitrogenBaseTestContext.() -> Unit) {
            defaultsTestRunWatcherInterceptor.beforeEachTest(override, action)
        }

        /**
         * Set the action which will be executed after the test.
         * The action has access to BaseTestContext.
         * If you set @param override in false then the final beforeAction will be
         *     beforeAction of the parent TestCase plus current @param action.
         *     Otherwise final beforeAction will be only @param action.
         */
        fun afterEachTest(override: Boolean = false, action: NitrogenBaseTestContext.() -> Unit) {
            defaultsTestRunWatcherInterceptor.afterEachTest(override, action)
        }


        @Suppress("detekt.ComplexMethod")
        private fun postInitVariables() {

            if (!::libLogger.isInitialized) libLogger = UiTestLoggerImpl(DEFAULT_LIB_LOGGER_TAG)
            if (!::testLogger.isInitialized) testLogger = UiTestLoggerImpl(DEFAULT_TEST_LOGGER_TAG)

            if (!::flakySafetyParams.isInitialized) flakySafetyParams = FlakySafetyParams.default()
            if (!::continuouslyParams.isInitialized) continuouslyParams = ContinuouslyParams.default()
            if (!::autoScrollParams.isInitialized) autoScrollParams = AutoScrollParams.nitrogen()
            if (!::stepParams.isInitialized) stepParams = StepParams()
        }

        @Suppress("detekt.ComplexMethod")
        private fun postBaseInitInterceptors() {
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

            if (!::viewBehaviorInterceptors.isInitialized) viewBehaviorInterceptors = mutableListOf(
                AutoScrollViewBehaviorInterceptor(autoScrollParams, libLogger),
                FlakySafeViewBehaviorInterceptor(flakySafetyParams, libLogger)
            )

            if (!::dataBehaviorInterceptors.isInitialized) dataBehaviorInterceptors = mutableListOf(
                FlakySafeDataBehaviorInterceptor(flakySafetyParams, libLogger)
            )

            if (!::webBehaviorInterceptors.isInitialized) webBehaviorInterceptors = mutableListOf(
                AutoScrollWebBehaviorInterceptor(autoScrollParams, libLogger),
                FlakySafeWebBehaviorInterceptor(flakySafetyParams, libLogger)
            )

            if (!::stepWatcherInterceptors.isInitialized) stepWatcherInterceptors = mutableListOf(
                LoggingStepWatcherInterceptor(libLogger)
            )

            if (!::testRunWatcherInterceptors.isInitialized) testRunWatcherInterceptors = mutableListOf(
                NitrogenTestRunLoggerWatcherInterceptor(libLogger),
                NitrogenBuildStepReportWatcherInterceptor(AllureReportWriter(libLogger)),
                defaultsTestRunWatcherInterceptor
            )
        }

        /**
         * Terminating method to build [KaspressoNitrogen] instance. Can be called only inside the framework package.
         * Actually called when the base [com.kaspersky.kaspressonitrogen.testcases.api.testcase.NitrogenBaseTestCase] class is
         * instantiated.
         *
         * @return build [Kaspresso] instance.
         */
        internal fun build(): KaspressoNitrogen {
            postInitVariables()
            postBaseInitInterceptors()

            val kaspresso = KaspressoNitrogen(
                libLogger = libLogger,
                testLogger = testLogger,

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

                viewBehaviorInterceptors = viewBehaviorInterceptors,
                dataBehaviorInterceptors = dataBehaviorInterceptors,
                webBehaviorInterceptors = webBehaviorInterceptors,

                stepWatcherInterceptors = stepWatcherInterceptors,
                testRunWatcherInterceptors = testRunWatcherInterceptors
            )

            LibraryInterceptorsInjector.injectKaspressoInKakao(
                kaspresso.viewBehaviorInterceptors,
                kaspresso.dataBehaviorInterceptors,
                kaspresso.webBehaviorInterceptors,
                kaspresso.viewActionWatcherInterceptors,
                kaspresso.viewAssertionWatcherInterceptors,
                kaspresso.atomWatcherInterceptors,
                kaspresso.webAssertionWatcherInterceptors
            )

            //failureHandler?.let { Espresso.setFailureHandler(it) }

            return kaspresso
        }
    }

    internal fun reset(): Unit = Kakao.reset()
}
