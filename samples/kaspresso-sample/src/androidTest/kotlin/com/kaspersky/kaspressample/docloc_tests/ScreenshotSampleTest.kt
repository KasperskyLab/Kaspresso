package com.kaspersky.kaspressample.docloc_tests

import android.Manifest
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.rule.GrantPermissionRule
import io.github.kakaocup.kakao.screen.Screen
import com.kaspersky.kaspressample.screen.SimpleScreen
import com.kaspersky.kaspressample.simple.SimpleActivity
import com.kaspersky.kaspresso.annotations.ScreenShooterTest
import com.kaspersky.kaspresso.testcases.api.testcase.DocLocScreenshotTestCase
import org.junit.Rule
import org.junit.Test

/**
 * An example of [DocLocScreenshotTestCase] usage.
 * For more information see DocLoc wiki page.
 */
class ScreenshotSampleTest : DocLocScreenshotTestCase(locales = "en,ru") {

    @get:Rule
    val runtimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    @get:Rule
    val activityRule = activityScenarioRule<SimpleActivity>()

    @ScreenShooterTest
    @Test
    fun test() = run {
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
