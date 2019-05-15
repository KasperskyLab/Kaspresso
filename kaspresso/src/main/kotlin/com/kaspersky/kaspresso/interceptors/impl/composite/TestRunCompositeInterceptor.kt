package com.kaspersky.kaspresso.interceptors.impl.composite

import com.kaspersky.kaspresso.extensions.other.forEachSafely
import com.kaspersky.kaspresso.interceptors.TestRunInterceptor
import com.kaspersky.kaspresso.testcases.TestBody


class TestRunCompositeInterceptor(
    private val interceptors: List<TestRunInterceptor>,
    private val exceptions: MutableList<Throwable>
) : TestRunInterceptor {
    override fun onTestStarted(testBody: TestBody) {
        interceptors.forEachSafely(exceptions) { it.onTestStarted(testBody) }
    }

    override fun onBeforeSectionStarted(testBody: TestBody) {
        interceptors.forEachSafely(exceptions) { it.onBeforeSectionStarted(testBody) }
    }

    override fun onBeforeSectionFinishedSuccess(testBody: TestBody) {
        interceptors.forEachSafely(exceptions) { it.onBeforeSectionFinishedSuccess(testBody) }
    }

    override fun onBeforeSectionFinishedFailed(testBody: TestBody, throwable: Throwable) {
        interceptors.forEachSafely(exceptions) { it.onBeforeSectionFinishedFailed(testBody, throwable) }
    }

    override fun onMainSectionStarted(testBody: TestBody) {
        interceptors.forEachSafely(exceptions) { it.onMainSectionStarted(testBody) }
    }

    override fun onMainSectionFinishedSuccess(testBody: TestBody) {
        interceptors.forEachSafely(exceptions) { it.onMainSectionFinishedSuccess(testBody) }
    }

    override fun onMainSectionFinishedFailed(testBody: TestBody, throwable: Throwable) {
        interceptors.forEachSafely(exceptions) { it.onMainSectionFinishedFailed(testBody, throwable) }
    }

    override fun onAfterSectionStarted(testBody: TestBody) {
        interceptors.forEachSafely(exceptions) { it.onAfterSectionStarted(testBody) }
    }

    override fun onAfterSectionFinishedSuccess(testBody: TestBody) {
        interceptors.forEachSafely(exceptions) { it.onAfterSectionFinishedSuccess(testBody) }
    }

    override fun onAfterSectionFinishedFailed(testBody: TestBody, throwable: Throwable) {
        interceptors.forEachSafely(exceptions) { it.onAfterSectionFinishedFailed(testBody, throwable) }
    }

    override fun onTestFinished(testBody: TestBody, success: Boolean) {
        interceptors.forEachSafely(exceptions) { it.onTestFinished(testBody, success) }
    }
}