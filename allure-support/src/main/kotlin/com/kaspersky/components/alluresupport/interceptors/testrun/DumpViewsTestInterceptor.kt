package com.kaspersky.components.alluresupport.interceptors.testrun

class DumpViewsTestInterceptor(
    private val viewHierarchyDumper: ViewHierarchyDumper
) : TestRunWatcherInterceptor {

    override fun onBeforeSectionFinishedFailed(testInfo: TestInfo, throwable: Throwable) {
        onSectionFailed(makeTag("BeforeTestSection", throwable))
    }

    override fun onMainSectionFinishedFailed(testInfo: TestInfo, throwable: Throwable) {
        onSectionFailed(makeTag("MainTestSection", throwable))
    }

    override fun onAfterSectionFinishedFailed(testInfo: TestInfo, throwable: Throwable) {
        onSectionFailed(makeTag("AfterTestSection", throwable))
    }

    private fun onSectionFailed(tag: String) {
        viewHierarchyDumper.dumpAndApply(tag) { attachViewHierarchyToAllureReport() }
    }

    private fun makeTag(section: String, throwable: Throwable): String =
        "ViewHierarchy_${section}_failure_${throwable.javaClass.simpleName}"
}
