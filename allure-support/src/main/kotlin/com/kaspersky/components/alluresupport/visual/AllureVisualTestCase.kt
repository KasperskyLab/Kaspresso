package com.kaspersky.components.alluresupport.visual

import com.kaspersky.components.alluresupport.results.AllureVisualTestFlag
import com.kaspersky.components.alluresupport.withForcedAllureSupport
import com.kaspersky.kaspresso.device.screenshots.ScreenshotsImpl
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.testcases.api.testcase.VisualTestCase
import io.qameta.allure.kotlin.Allure
import io.qameta.allure.kotlin.model.Status
import io.qameta.allure.kotlin.model.StatusDetails

abstract class AllureVisualTestCase(
    private val failEarly: Boolean = false,
    kaspressoBuilder: Kaspresso.Builder = Kaspresso.Builder.withForcedAllureSupport()
) : VisualTestCase(kaspressoBuilder = kaspressoBuilder) {

    override fun assertScreenshot(tag: String, isFullWindow: Boolean) {
        try {
            device.screenshots.assert(tag, isFullWindow)
        } catch (ex: ScreenshotsImpl.ScreenshotDoesntMatchException) {
            if (failEarly) {
                // Wrap with assertion error so test would be marked as FAILED instead of BROKEN
                // See https://github.com/allure-framework/allure-kotlin allure-kotlin-commons/src/main/kotlin/io/qameta/allure/kotlin/util/ResultsUtils.kt
                throw AssertionError(ex)
            }

            Allure.lifecycle.updateStep {
                it.status = Status.FAILED
                it.statusDetails = StatusDetails(known = true, muted = true, message = ex.message, trace = ex.stackTraceToString())
            }
            Allure.lifecycle.stopStep()
            AllureVisualTestFlag.shouldFailLate.set(true)
        }
    }
}
