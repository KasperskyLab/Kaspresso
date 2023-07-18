package com.kaspersky.kaspressample.docloc_tests

import androidx.test.espresso.action.ViewActions
import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspressample.MainActivity
import com.kaspersky.kaspressample.screen.MainScreen
import com.kaspersky.kaspressample.screen.WithToolbarScreen
import com.kaspersky.kaspresso.testcases.api.testcase.DocLocScreenshotTestCase
import org.junit.Rule
import org.junit.Test

class ScreenshotActivityWithToolbarTest : DocLocScreenshotTestCase(locales="en") {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun takeScreenshot() = run {
        step("Open screen with toolbar") {
            MainScreen {
                withToolbarButton {
                    click()
                }
            }
        }

        step("Take screenshot") {
            WithToolbarScreen {
                collapsingToolbar {
                    view.perform(ViewActions.swipeUp())
                }
            }
            captureScreenshot("screen with toolbar")
        }
    }
}
