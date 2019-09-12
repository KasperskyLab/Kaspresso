package com.kaspersky.kaspressample.docloc_tests

import android.Manifest
import android.content.Intent
import android.provider.Settings
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.rule.GrantPermissionRule
import com.kaspersky.kaspressample.MainActivity
import com.kaspersky.kaspresso.testcases.api.testcase.DocLocScreenshotTestCase
import java.io.File
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * An example of [DocLocScreenshotTestCase] usage with locale's changing at OS.
 * For more information see DocLoc wiki page.
 */
@RunWith(AndroidJUnit4::class)
class ChangeSysLanguageTestCase : DocLocScreenshotTestCase(
    screenshotsDirectory = File("screenshots"),
    locales = "en,es-US,pt-BR,ru",
    changeSystemLocale = true
) {

    @get:Rule
    val runtimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    @get:Rule
    val activityTestRule = ActivityTestRule(MainActivity::class.java, true, false)

    @Test
    fun showAndroidSettings() {
        before {
        }.after {
        }.run {
            step("Start Android OS Settings") {
                val intent = Intent(Settings.ACTION_SETTINGS)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                device.targetContext.startActivity(intent)
                device.uiDevice.waitForIdle(3000)
            }
            step("Capture Android OS Settings") {
                captureScreenshot("android_setting")
            }
        }
    }
}