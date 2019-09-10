package com.kaspersky.kaspressample.docloc_tests

import android.content.Intent
import android.provider.Settings
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.kaspersky.kaspressample.MainActivity
import com.kaspersky.kaspresso.testcases.api.testcase.DocLocScreenshotTestCase
import java.io.File
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ChangeSysLanguageTestCase : DocLocScreenshotTestCase(
    screenshotsDirectory = File("screenshots"),
    locales = "en,es-US,pt-BR,ru",
    changeSystemLocale = true
) {

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

                val context = InstrumentationRegistry.getInstrumentation().targetContext
                context.startActivity(intent)
                device.uiDevice.waitForIdle(3000)
            }
            step("Capture Android OS Settings") {
                captureScreenshot("android_setting")
            }
        }
    }
}