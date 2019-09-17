package com.kaspersky.kaspresso.report.impl

import com.google.gson.Gson
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso.report.ReportWriter
import com.kaspersky.kaspresso.testcases.models.info.TestInfo

class AllureReportWriter(
    private val uiTestLogger: UiTestLogger
) : ReportWriter {

    companion object {
        private const val STEPS_INFO_LABEL = "#AllureStepsInfoJson#:"
        private const val MAX_LOG_CHUNK_LENGTH = 4000 - STEPS_INFO_LABEL.length - 1
    }

    private val stepInfoConverter: StepInfoConverter by lazy { StepInfoConverter() }
    private val gson: Gson by lazy { Gson() }

    override fun processTestResults(testInfo: TestInfo) {
        val steps = testInfo.stepInfos.map { stepInfoConverter.convert(it) }
        val stepsJson = gson.toJson(steps)

        if (stepsJson.length > MAX_LOG_CHUNK_LENGTH) {
            val chunkCount = stepsJson.length / MAX_LOG_CHUNK_LENGTH
            for (i in 0..chunkCount) {
                val max = MAX_LOG_CHUNK_LENGTH * (i + 1)
                if (max >= stepsJson.length) {
                    uiTestLogger.i("$STEPS_INFO_LABEL ${stepsJson.substring(MAX_LOG_CHUNK_LENGTH * i)}")
                } else {
                    uiTestLogger.i("$STEPS_INFO_LABEL ${stepsJson.substring(MAX_LOG_CHUNK_LENGTH * i, max)}")
                }
            }
        } else {
            uiTestLogger.i("$STEPS_INFO_LABEL $stepsJson")
        }
    }
}