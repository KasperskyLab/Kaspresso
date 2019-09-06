package com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.composite

import com.kaspersky.kaspresso.interceptors.watcher.testcase.TestRunWatcherInterceptor
import com.kaspersky.kaspresso.internal.extensions.other.forEachSafely
import com.kaspersky.kaspresso.testcases.models.info.TestInfo

class TestRunCompositeWatcherInterceptor(
    private val watcherInterceptors: List<TestRunWatcherInterceptor>,
    private val exceptions: MutableList<Throwable>
) : TestRunWatcherInterceptor {

    override fun onTestStarted(testInfo: TestInfo) {
        watcherInterceptors.forEachSafely(exceptions) { it.onTestStarted(testInfo) }
    }

    override fun onBeforeSectionStarted(testInfo: TestInfo) {
        watcherInterceptors.forEachSafely(exceptions) { it.onBeforeSectionStarted(testInfo) }
    }

    override fun onBeforeSectionFinishedSuccess(testInfo: TestInfo) {
        watcherInterceptors.forEachSafely(exceptions) { it.onBeforeSectionFinishedSuccess(testInfo) }
    }

    override fun onBeforeSectionFinishedFailed(testInfo: TestInfo, throwable: Throwable) {
        watcherInterceptors.forEachSafely(exceptions) { it.onBeforeSectionFinishedFailed(testInfo, throwable) }
    }

    override fun onMainSectionStarted(testInfo: TestInfo) {
        watcherInterceptors.forEachSafely(exceptions) { it.onMainSectionStarted(testInfo) }
    }

    override fun onMainSectionFinishedSuccess(testInfo: TestInfo) {
        watcherInterceptors.forEachSafely(exceptions) { it.onMainSectionFinishedSuccess(testInfo) }
    }

    override fun onMainSectionFinishedFailed(testInfo: TestInfo, throwable: Throwable) {
        watcherInterceptors.forEachSafely(exceptions) { it.onMainSectionFinishedFailed(testInfo, throwable) }
    }

    override fun onAfterSectionStarted(testInfo: TestInfo) {
        watcherInterceptors.forEachSafely(exceptions) { it.onAfterSectionStarted(testInfo) }
    }

    override fun onAfterSectionFinishedSuccess(testInfo: TestInfo) {
        watcherInterceptors.forEachSafely(exceptions) { it.onAfterSectionFinishedSuccess(testInfo) }
    }

    override fun onAfterSectionFinishedFailed(testInfo: TestInfo, throwable: Throwable) {
        watcherInterceptors.forEachSafely(exceptions) { it.onAfterSectionFinishedFailed(testInfo, throwable) }
    }

    override fun onTestFinished(testInfo: TestInfo, success: Boolean) {
        watcherInterceptors.forEachSafely(exceptions) { it.onTestFinished(testInfo, success) }
    }
}