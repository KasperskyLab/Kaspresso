package com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.composite

import com.kaspersky.kaspresso.interceptors.watcher.testcase.TestRunWatcherInterceptor
import com.kaspersky.kaspresso.internal.extensions.other.forEachSafely
import com.kaspersky.kaspresso.testcases.core.testcontext.BaseTestContext
import com.kaspersky.kaspresso.testcases.models.info.TestInfo

/**
 * The implementation of the [TestRunWatcherInterceptor] interface.
 * Composes all of [TestRunWatcherInterceptor]s list into one composite [TestRunWatcherInterceptor] that is actually
 * called by [com.kaspersky.kaspresso.testcases.core.TestRunner] on each test event.
 */
class TestRunCompositeWatcherInterceptor(
    private val watcherInterceptors: List<TestRunWatcherInterceptor>,
    private val exceptions: MutableList<Throwable>
) : TestRunWatcherInterceptor {

    /**
     * Called on the whole test starts, delegates the interception to [watcherInterceptors].
     *
     * @param testInfo the test info to pass to [watcherInterceptors].
     */
    override fun BaseTestContext.onTestStarted(testInfo: TestInfo) {
        watcherInterceptors.forEachSafely(exceptions) { it.run { onTestStarted(testInfo) } }
    }

    /**
     * Called on "before" section starts, delegates the interception to [watcherInterceptors].
     *
     * @param testInfo the test info to pass to [watcherInterceptors].
     */
    override fun BaseTestContext.onBeforeSectionStarted(testInfo: TestInfo) {
        watcherInterceptors.forEachSafely(exceptions) { it.run { onBeforeSectionStarted(testInfo) } }
    }

    /**
     * Called on "before" section finishes with success, delegates the interception to [watcherInterceptors].
     *
     * @param testInfo the test info to pass to [watcherInterceptors].
     */
    override fun BaseTestContext.onBeforeSectionFinishedSuccess(testInfo: TestInfo) {
        watcherInterceptors.forEachSafely(exceptions) { it.run { onBeforeSectionFinishedSuccess(testInfo) } }
    }

    /**
     * Called on "before" section finishes with failure, delegates the interception to [watcherInterceptors].
     *
     * @param testInfo the test info to pass to [watcherInterceptors].
     * @param throwable the error occured to pass to  [watcherInterceptors].
     */
    override fun BaseTestContext.onBeforeSectionFinishedFailed(testInfo: TestInfo, throwable: Throwable) {
        watcherInterceptors.forEachSafely(exceptions) { it.run { onBeforeSectionFinishedFailed(testInfo, throwable) } }
    }

    /**
     * Called on "main" section starts, delegates the interception to [watcherInterceptors].
     *
     * @param testInfo the test info to pass to [watcherInterceptors].
     */
    override fun BaseTestContext.onMainSectionStarted(testInfo: TestInfo) {
        watcherInterceptors.forEachSafely(exceptions) { it.run { onMainSectionStarted(testInfo) } }
    }

    /**
     * Called on "main" section finishes with success, delegates the interception to [watcherInterceptors].
     *
     * @param testInfo the test info to pass to [watcherInterceptors].
     */
    override fun BaseTestContext.onMainSectionFinishedSuccess(testInfo: TestInfo) {
        watcherInterceptors.forEachSafely(exceptions) { it.run { onMainSectionFinishedSuccess(testInfo) } }
    }

    /**
     * Called on "main" section finishes with failure, delegates the interception to [watcherInterceptors].
     *
     * @param testInfo the test info to pass to [watcherInterceptors].
     * @param throwable the error occured to pass to  [watcherInterceptors].
     */
    override fun BaseTestContext.onMainSectionFinishedFailed(testInfo: TestInfo, throwable: Throwable) {
        watcherInterceptors.forEachSafely(exceptions) { it.run { onMainSectionFinishedFailed(testInfo, throwable) } }
    }

    /**
     * Called on "after" section starts, delegates the interception to [watcherInterceptors].
     *
     * @param testInfo the test info to pass to [watcherInterceptors].
     */
    override fun BaseTestContext.onAfterSectionStarted(testInfo: TestInfo) {
        watcherInterceptors.forEachSafely(exceptions) { it.run { onAfterSectionStarted(testInfo) } }
    }

    /**
     * Called on "after" section finishes with success, delegates the interception to [watcherInterceptors].
     *
     * @param testInfo the test info to pass to [watcherInterceptors].
     */
    override fun BaseTestContext.onAfterSectionFinishedSuccess(testInfo: TestInfo) {
        watcherInterceptors.forEachSafely(exceptions) { it.run { onAfterSectionFinishedSuccess(testInfo) } }
    }

    /**
     * Called on "after" section finishes with failure, delegates the interception to [watcherInterceptors].
     *
     * @param testInfo the test info to pass to [watcherInterceptors].
     * @param throwable the error occured to pass to  [watcherInterceptors].
     */
    override fun BaseTestContext.onAfterSectionFinishedFailed(testInfo: TestInfo, throwable: Throwable) {
        watcherInterceptors.forEachSafely(exceptions) { it.run { onAfterSectionFinishedFailed(testInfo, throwable) } }
    }

    /**
     * Called on the whole test finishes, delegates the interception to [watcherInterceptors].
     *
     * @param testInfo the test info to pass to [watcherInterceptors].
     * @param success the while test was finished successfully or not, to pass to [watcherInterceptors].
     */
    override fun BaseTestContext.onTestFinished(testInfo: TestInfo, success: Boolean) {
        watcherInterceptors.forEachSafely(exceptions) { it.run { onTestFinished(testInfo, success) } }
    }
}