package com.kaspersky.kaspresso.report.impl

import com.google.gson.Gson
import com.kaspersky.kaspresso.report.ReportWriter
import com.kaspersky.kaspresso.steps.StepsResultsConsumer
import com.kaspersky.kaspresso.testcases.models.info.TestInfo

/**
 * This [com.kaspersky.kaspresso.report.ReportWriter] processes [com.kaspersky.kaspresso.testcases.models.info.TestInfo]
 * for generating <a href="https://docs.qameta.io/allure/#_steps">Allure's steps</a> info JSON.
 * After JSON generation it will be sent into special consumer which will propagate steps information further.
 */
class AllureReportWriter(
    private val stepsResultsConsumers: List<StepsResultsConsumer>
) : ReportWriter {

    private val stepInfoConverter: StepInfoConverter by lazy { StepInfoConverter() }
    private val gson: Gson by lazy { Gson() }

    override fun processTestResults(testInfo: TestInfo) {
        val steps = testInfo.stepInfos.map { stepInfoConverter.convert(it) }
        val stepsJson = gson.toJson(steps)

        stepsResultsConsumers.forEach { it.consume(testInfo.testIdentifier, stepsJson) }
    }
}