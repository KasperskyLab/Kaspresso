package com.kaspersky.kaspressample.docloc_tests

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspressample.localization.LocalizationExerciseActivity
import com.kaspersky.kaspressample.screen.LocalizationExerciseScreen
import com.kaspersky.kaspresso.annotations.ScreenShooterTest
import com.kaspersky.kaspresso.testcases.api.testcase.DocLocScreenshotTestCase
import org.junit.Rule
import org.junit.Test

class LocalizationExerciseScreenshotTest : DocLocScreenshotTestCase(locales = "en,ru") {

    @get:Rule
    val activityRule = activityScenarioRule<LocalizationExerciseActivity>()

    @ScreenShooterTest
    @Test
    fun profileSettingsScreen() = run {
        step("1. Capture the profile settings screen") {
            captureScreenshot("1. Profile settings")
        }

        step("2. Save the profile") {
            LocalizationExerciseScreen {
                saveButton.click()
                savedMessage.isDisplayed()
            }
            captureScreenshot("2. Profile saved")
        }
    }
}
