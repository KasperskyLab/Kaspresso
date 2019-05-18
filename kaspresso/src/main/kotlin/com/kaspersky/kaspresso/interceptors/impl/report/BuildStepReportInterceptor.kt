package com.kaspersky.kaspresso.interceptors.impl.report

import com.kaspersky.kaspresso.interceptors.TestRunInterceptor
import com.kaspersky.kaspresso.report.ReportWriter
import com.kaspersky.kaspresso.testcases.models.TestInfo

class BuildStepReportInterceptor(
    private val allureReportWriter: ReportWriter
) : TestRunInterceptor {

    override fun onTestFinished(testInfo: TestInfo, success: Boolean) {
        allureReportWriter.processTestResults(testInfo)
    }

}