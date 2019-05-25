package com.kaspersky.kaspresso.report

import com.kaspersky.kaspresso.testcases.models.TestInfo

interface ReportWriter {
    fun processTestResults(allureTestResult: TestInfo)
}