package com.kaspersky.kaspresso.interceptors.testcase.impl.composite

import com.kaspersky.kaspresso.extensions.other.forEachSafely
import com.kaspersky.kaspresso.interceptors.testcase.TestRunInterceptor
import com.kaspersky.kaspresso.testcases.models.info.TestInfo

class TestRunCompositeInterceptor(
    private val interceptors: List<TestRunInterceptor>,
    private val exceptions: MutableList<Throwable>
) : TestRunInterceptor {

    override fun onTestStarted(testInfo: TestInfo) {
        interceptors.forEachSafely(exceptions) { it.onTestStarted(testInfo) }
    }

    override fun onBeforeSectionStarted(testInfo: TestInfo) {
        interceptors.forEachSafely(exceptions) { it.onBeforeSectionStarted(testInfo) }
    }

    override fun onBeforeSectionFinishedSuccess(testInfo: TestInfo) {
        interceptors.forEachSafely(exceptions) { it.onBeforeSectionFinishedSuccess(testInfo) }
    }

    override fun onBeforeSectionFinishedFailed(testInfo: TestInfo, throwable: Throwable) {
        interceptors.forEachSafely(exceptions) { it.onBeforeSectionFinishedFailed(testInfo, throwable) }
    }

    override fun onMainSectionStarted(testInfo: TestInfo) {
        interceptors.forEachSafely(exceptions) { it.onMainSectionStarted(testInfo) }
    }

    override fun onMainSectionFinishedSuccess(testInfo: TestInfo) {
        interceptors.forEachSafely(exceptions) { it.onMainSectionFinishedSuccess(testInfo) }
    }

    override fun onMainSectionFinishedFailed(testInfo: TestInfo, throwable: Throwable) {
        interceptors.forEachSafely(exceptions) { it.onMainSectionFinishedFailed(testInfo, throwable) }
    }

    override fun onAfterSectionStarted(testInfo: TestInfo) {
        interceptors.forEachSafely(exceptions) { it.onAfterSectionStarted(testInfo) }
    }

    override fun onAfterSectionFinishedSuccess(testInfo: TestInfo) {
        interceptors.forEachSafely(exceptions) { it.onAfterSectionFinishedSuccess(testInfo) }
    }

    override fun onAfterSectionFinishedFailed(testInfo: TestInfo, throwable: Throwable) {
        interceptors.forEachSafely(exceptions) { it.onAfterSectionFinishedFailed(testInfo, throwable) }
    }

    override fun onTestFinished(testInfo: TestInfo, success: Boolean) {
        interceptors.forEachSafely(exceptions) { it.onTestFinished(testInfo, success) }
    }
}