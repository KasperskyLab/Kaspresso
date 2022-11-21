package com.kaspersky.kaspressample.docloc_tests

import android.content.Intent
import android.provider.Settings
import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspressample.MainActivity
import com.kaspersky.kaspresso.testcases.api.testcase.DocLocScreenshotTestCase
import org.junit.Rule
import org.junit.Test
import java.io.File

/**
 * An example of [DocLocScreenshotTestCase] usage with locale's changing at OS.
 * For more information see DocLoc wiki page.
 */
class ChangeSysLanguageTestCaseLegacy : DocLocScreenshotTestCase(
    screenshotsDirectory = File("screenshots"),
    locales = "en,es-US,pt-BR,ru",
    changeSystemLocale = true
) {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun showAndroidSettings() = run {
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
