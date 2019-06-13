package com.kaspersky.kaspresso.report

import com.kaspersky.kaspresso.testcases.models.info.TestInfo

interface ReportWriter {

    fun processTestResults(testInfo: TestInfo)
}