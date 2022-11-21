package com.kaspersky.kaspressample.docloc_tests

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspressample.devicefull.DeviceFullWindowSampleActivity
import com.kaspersky.kaspresso.annotations.ScreenShooterTest
import com.kaspersky.kaspresso.testcases.api.testcase.DocLocScreenshotTestCase
import org.junit.Rule
import org.junit.Test

/**
 * An example of [DocLocScreenshotTestCase] usage.
 * For more information see DocLoc wiki page.
 */
class ScreenshotFullWindowSampleTest : DocLocScreenshotTestCase(locales = "en,ru") {

    @get:Rule
    val activityRule = activityScenarioRule<DeviceFullWindowSampleActivity>()

    @ScreenShooterTest
    @Test
    fun test() = run {
        step("1. Launch activity") {
            captureFullWindowScreenshot("1. Simple screen")
        }
    }
}
