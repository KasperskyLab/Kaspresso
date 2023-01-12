package com.kaspersky.kaspressample.docloc_tests

import android.Manifest
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.rule.GrantPermissionRule
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
    val runtimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

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
