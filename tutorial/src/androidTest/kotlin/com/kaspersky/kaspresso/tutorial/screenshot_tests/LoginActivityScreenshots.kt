package com.kaspersky.kaspresso.tutorial.screenshot_tests

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.testcases.api.testcase.DocLocScreenshotTestCase
import com.kaspersky.kaspresso.tutorial.login.LoginActivity
import com.kaspersky.kaspresso.tutorial.screen.LoginScreen
import org.junit.Rule
import org.junit.Test

class LoginActivityScreenshots : DocLocScreenshotTestCase(locales = "fr,en", changeSystemLocale = true) {

    @get:Rule
    val activityRule = activityScenarioRule<LoginActivity>()

    @Test
    fun takeScreenshots() = run {
        step("Take screenshots initial state") {
            LoginScreen {
                waitForScreen()
                captureScreenshot("Initial state")
            }
        }
    }
}
