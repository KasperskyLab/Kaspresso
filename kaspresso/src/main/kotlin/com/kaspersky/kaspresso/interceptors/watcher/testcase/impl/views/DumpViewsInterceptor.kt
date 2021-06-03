package com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.views

import com.kaspersky.kaspresso.device.viewhierarchy.ViewHierarchyDumper
import com.kaspersky.kaspresso.interceptors.watcher.testcase.TestRunWatcherInterceptor
import com.kaspersky.kaspresso.testcases.models.info.TestInfo

class DumpViewsInterceptor(
    private val viewHierarchyDumper: ViewHierarchyDumper
) : TestRunWatcherInterceptor {

    override fun onBeforeSectionFinishedFailed(testInfo: TestInfo, throwable: Throwable) {
        viewHierarchyDumper.dump(makeTag("BeforeTestSection", throwable))
    }

    override fun onMainSectionFinishedFailed(testInfo: TestInfo, throwable: Throwable) {
        viewHierarchyDumper.dump(makeTag("MainTestSection", throwable))
    }

    override fun onAfterSectionFinishedFailed(testInfo: TestInfo, throwable: Throwable) {
        viewHierarchyDumper.dump(makeTag("AfterTestSection", throwable))
    }

    private fun makeTag(section: String, throwable: Throwable): String =
        "ViewHierarchy_${section}_failure_${throwable.javaClass.simpleName}"
}
