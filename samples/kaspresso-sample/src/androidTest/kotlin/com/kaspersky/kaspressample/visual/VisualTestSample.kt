package com.kaspersky.kaspressample.visual

import android.Manifest
import android.os.Build
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.rule.GrantPermissionRule
import com.kaspersky.kaspressample.MainActivity
import com.kaspersky.kaspressample.screen.MainScreen
import com.kaspersky.kaspresso.testcases.api.testcase.VisualTestCase
import org.junit.Assume
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test

class VisualTestSample : VisualTestCase() {
    @get:Rule
    val runtimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.READ_MEDIA_IMAGES,
    )

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun test() = runScreenshotTest{
        Assume.assumeTrue(
            "Granting READ_MEDIA_IMAGES fails on the lower APIs",
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU
        )
        step("Open Simple Screen") {
            MainScreen {
                simpleButton {
                    isVisible()
                    click()
                    assertScreenshot("some_tag")
                }
            }
        }
    }
}
