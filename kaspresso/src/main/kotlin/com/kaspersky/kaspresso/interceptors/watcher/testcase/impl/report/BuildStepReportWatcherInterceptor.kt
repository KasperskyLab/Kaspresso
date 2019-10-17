package com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.report

import com.kaspersky.kaspresso.interceptors.watcher.testcase.TestRunWatcherInterceptor
import com.kaspersky.kaspresso.report.ReportWriter
import com.kaspersky.kaspresso.testcases.core.testcontext.BaseTestContext
import com.kaspersky.kaspresso.testcases.models.info.TestInfo

class BuildStepReportWatcherInterceptor(
    private val reportWriter: ReportWriter
) : TestRunWatcherInterceptor {

    override fun BaseTestContext.onTestFinished(testInfo: TestInfo, success: Boolean) {
        reportWriter.processTestResults(testInfo)
    }
}