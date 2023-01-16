package com.kaspersky.kaspressample.docloc_tests

import android.os.Build
import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspressample.screen.SimpleScreen
import com.kaspersky.kaspressample.simple.SimpleActivity
import com.kaspersky.kaspresso.annotations.ScreenShooterTest
import com.kaspersky.kaspresso.testcases.api.testcase.DocLocScreenshotTestCase
import io.github.kakaocup.kakao.screen.Screen
import org.junit.Assume
import org.junit.Rule
import org.junit.Test

/**
 * An example of [DocLocScreenshotTestCase] usage.
 * For more information see DocLoc wiki page.
 */
class ScreenshotSampleTest : DocLocScreenshotTestCase(locales = "en,ru") {

    @get:Rule
    val activityRule = activityScenarioRule<SimpleActivity>()

    @ScreenShooterTest
    @Test
    fun test() = before {
        Assume.assumeTrue(
            "Due to the bug in the UiAutomation this test doesn't work and API <= 22",
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
        )
    }.after {
    }.run {
        step("1. Launch activity") {
            activityRule.scenario.onActivity { // Test that screenshot shooter works in app's main thread
                captureScreenshot("1. Simple screen")
            }
        }

        step("2. Press Button 1") {
            SimpleScreen {
                button1 {
                    click()
                }
            }
            captureScreenshot("2. Simple fragment - two buttons")
        }

        step("3. Press Button 2") {
            SimpleScreen {
                button2 {
                    click()
                }
            }
            Screen.idle(2_500L) // Wait for timeout
            captureScreenshot("3. Simple fragment - input")
        }

        step("4. Type text") {
            SimpleScreen {
                closeSoftKeyboard()
                edit {
                    clearText()
                    typeText("Kaspresso")
                }
            }
            captureScreenshot("4. Simple fragment - typed text")
        }
    }
}
