package com.kaspersky.kaspressonitrogen.interceptors.watcher.testcase.impl.report

import com.kaspersky.kaspresso.interceptors.watcher.testcase.NitrogenTestRunWatcherInterceptor
import com.kaspersky.kaspresso.report.ReportWriter
import com.kaspersky.kaspresso.testcases.models.info.TestInfo

class NitrogenBuildStepReportWatcherInterceptor(
        private val reportWriter: ReportWriter
) : NitrogenTestRunWatcherInterceptor {

    override fun onTestFinished(testInfo: TestInfo, success: Boolean) {
        reportWriter.processTestResults(testInfo)
    }
}
