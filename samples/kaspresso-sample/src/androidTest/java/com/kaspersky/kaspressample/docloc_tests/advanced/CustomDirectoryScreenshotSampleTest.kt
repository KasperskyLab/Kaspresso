package com.kaspersky.kaspressample.docloc_tests.advanced

import android.Manifest
import androidx.test.rule.ActivityTestRule
import androidx.test.rule.GrantPermissionRule
import com.agoda.kakao.screen.Screen
import com.kaspersky.kaspressample.screen.SimpleScreen
import com.kaspersky.kaspressample.simple.SimpleActivity
import com.kaspersky.kaspresso.annotations.ScreenShooterTest
import com.kaspersky.kaspresso.device.screenshots.screenshotfiles.ScreenshotDirectoryProvider
import com.kaspersky.kaspresso.device.screenshots.screenshotfiles.ScreenshotNameProvider
import com.kaspersky.kaspresso.device.screenshots.screenshotfiles.TestMethod
import com.kaspersky.kaspresso.testcases.api.testcase.DocLocScreenshotTestCase
import org.junit.Rule
import org.junit.Test
import java.io.File

class CustomDirectoryScreenshotSampleTest : DocLocScreenshotTestCase(
    screenshotsDirectory = File("custom_directory"),
    screenshotDirectoryProvider = FlatDirectoryProvider,
    screenshotNameProvider = AutoNumeratedNameProvider(),
    locales = "en,ru"
) {

    @get:Rule
    val runtimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

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

private object FlatDirectoryProvider : ScreenshotDirectoryProvider {
    override fun getDirectoryForTest(testMethod: TestMethod, runNumber: Int): String = ""
}

private class AutoNumeratedNameProvider : ScreenshotNameProvider {

    private var counter = 0

    override fun getScreenshotName(tag: String): String = "screenshot#${counter++}_$tag"
}