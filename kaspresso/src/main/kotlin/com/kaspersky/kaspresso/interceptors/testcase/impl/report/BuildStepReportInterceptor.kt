package com.kaspersky.kaspresso.interceptors.testcase.impl.report

import com.kaspersky.kaspresso.interceptors.testcase.TestRunInterceptor
import com.kaspersky.kaspresso.report.ReportWriter
import com.kaspersky.kaspresso.testcases.models.info.TestInfo

class BuildStepReportInterceptor(
    private val reportWriter: ReportWriter
) : TestRunInterceptor {

    override fun onTestFinished(testInfo: TestInfo, success: Boolean) {
        reportWriter.processTestResults(testInfo)
    }
}