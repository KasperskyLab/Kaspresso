package com.kaspersky.kaspressample.tests.docloc

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.agoda.kakao.screen.Screen
import com.kaspersky.kaspressample.MainActivity
import com.kaspersky.kaspressample.screen.MainScreen
import com.kaspersky.kaspressample.screen.SimpleScreen
import com.kaspersky.kaspresso.annotations.ScreenShooterTest
import com.kaspersky.kaspresso.testcases.api.testcase.DocLocScreenshotTestCase
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.File

@RunWith(AndroidJUnit4::class)
class ScreenshotSampleTest : DocLocScreenshotTestCase(locales = "en,ru", screenshotsDirectory = File("screenshots")) {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java, false, true)

    @ScreenShooterTest
    @Test
    fun test() {
        before {
        }.after {
        }.run {

            step("1. Launch activity") {
                captureScreenshot("1. Main screen")
            }

            step("2. Press the 'Next' button") {
                MainScreen {
                    nextButton {
                        click()
                    }
                }

                captureScreenshot("2. Simple fragment - one button")
            }

            step("3. Press Button 1") {
                SimpleScreen {
                    button1 {
                        click()
                    }
                }

                captureScreenshot("3. Simple fragment - two buttons")
            }

            step("4. Press Button 2") {
                SimpleScreen {
                    button2 {
                        click()
                    }
                }

                Screen.idle(2_500L) // Wait for timeout

                captureScreenshot("4. Simple fragment - input")
            }

            step("5. Type text") {
                SimpleScreen {
                    edit {
                        clearText()
                        typeText("Kaspresso")
                    }
                }

                captureScreenshot("5. Simple fragment - typed text")
            }
        }
    }
}