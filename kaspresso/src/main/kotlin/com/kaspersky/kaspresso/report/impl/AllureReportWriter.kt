package com.kaspersky.kaspresso.report.impl

import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso.report.ReportWriter
import com.kaspersky.kaspresso.testcases.models.info.TestInfo

class AllureReportWriter(
    private val uiTestLogger: UiTestLogger
) : ReportWriter {

    override fun processTestResults(testInfo: TestInfo) {
        // TODO add real implementation
        uiTestLogger.section("Allure test results: $testInfo")
    }
}