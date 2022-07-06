package com.kaspersky.kaspressample.docloc_tests

import android.Manifest
import android.content.Intent
import android.os.Build
import android.provider.Settings
import androidx.test.rule.ActivityTestRule
import androidx.test.rule.GrantPermissionRule
import com.kaspersky.kaspressample.MainActivity
import com.kaspersky.kaspresso.testcases.api.testcase.DocLocScreenshotTestCase
import org.junit.Assume
import org.junit.Rule
import org.junit.Test

/**
 * An example of [DocLocScreenshotTestCase] usage with locale's changing at OS.
 * For more information see DocLoc wiki page.
 */
class ChangeSysLanguageTestCase : DocLocScreenshotTestCase(
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
        Assume.assumeTrue(Build.VERSION.SDK_INT <= Build.VERSION_CODES.O) // Can't change system locale on newer versions

        run {
            step("Start Android OS Settings") {
                val intent = Intent(Settings.ACTION_SETTINGS)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                device.targetContext.startActivity(intent)
                Thread.sleep(3000)
            }

            step("Capture Android OS Settings") {
                captureScreenshot("android_setting")
            }
        }
    }
}
