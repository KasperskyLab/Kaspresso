package com.kaspersky.kaspressample.docloc_tests

import android.Manifest
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.rule.GrantPermissionRule
import com.kaspersky.kaspressample.devicefull.DeviceFullWindowSampleActivity
import com.kaspersky.kaspressample.screen.DeviceFullWindowScreen
import com.kaspersky.kaspresso.annotations.ScreenShooterTest
import com.kaspersky.kaspresso.testcases.api.testcase.DocLocScreenshotTestCase
import org.junit.Rule
import org.junit.Test

class ScreenshotDialogSampleTest : DocLocScreenshotTestCase(locales = "en,ru", toggleNightMode = true) {

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
            DeviceFullWindowScreen {
                showDialog {
                    click()
                }
            }
            captureScreenshot("1. Simple dialog screen")
            captureFullWindowScreenshot("2. Simple full screen")
        }
    }
}
