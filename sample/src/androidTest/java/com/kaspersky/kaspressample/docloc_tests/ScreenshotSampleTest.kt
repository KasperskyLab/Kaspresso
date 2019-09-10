package com.kaspersky.kaspressample.docloc_tests

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.agoda.kakao.screen.Screen
import com.kaspersky.kaspressample.SimpleActivity
import com.kaspersky.kaspressample.screen.SimpleScreen
import com.kaspersky.kaspresso.annotations.ScreenShooterTest
import com.kaspersky.kaspresso.testcases.api.testcase.DocLocScreenshotTestCase
import java.io.File
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * An example of [DocLocScreenshotTestCase] usage. For more information see
 * help/DocLocScreenshotTests.md page.
 */
@RunWith(AndroidJUnit4::class)
class ScreenshotSampleTest : DocLocScreenshotTestCase(
    screenshotsDirectory = File("screenshots"),
    locales = "en,ru"
) {

    @get:Rule
    val activityRule = ActivityTestRule(SimpleActivity::class.java, false, true)

    @ScreenShooterTest
    @Test
    fun test() {
        before {
        }.after {
        }.run {

            step("1. Launch activity") {
                captureScreenshot("1. Simple screen")
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
                    edit {
                        clearText()
                        typeText("Kaspresso")
                    }
                }

                captureScreenshot("4. Simple fragment - typed text")
            }
        }
    }
}