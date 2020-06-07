package com.kaspersky.kaspressample.docloc_tests

import android.Manifest
import androidx.test.rule.ActivityTestRule
import androidx.test.rule.GrantPermissionRule
import com.agoda.kakao.screen.Screen
import com.kaspersky.kaspressample.MainActivity
import com.kaspersky.kaspressample.compose.ComplexComposeSampleActivity
import com.kaspersky.kaspressample.external_screens.UiMainScreen
import com.kaspersky.kaspressample.screen.SimpleScreen
import com.kaspersky.kaspressample.simple.SimpleActivity
import com.kaspersky.kaspresso.annotations.ScreenShooterTest
import com.kaspersky.kaspresso.device.screenshots.ScreenshotsImpl
import com.kaspersky.kaspresso.device.screenshots.screenshotfiles.DefaultScreenshotDirectoryProvider
import com.kaspersky.kaspresso.device.screenshots.screenshotfiles.DefaultScreenshotNameProvider
import com.kaspersky.kaspresso.device.screenshots.screenshotmaker.CombinedScreenshotMaker
import com.kaspersky.kaspresso.device.screenshots.screenshotmaker.ExternalScreenshotMaker
import com.kaspersky.kaspresso.device.screenshots.screenshotmaker.InternalScreenshotMaker
import com.kaspersky.kaspresso.device.screenshots.screenshotmaker.ScrollScreenshotMaker
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.testcases.api.testcase.DocLocScreenshotTestCase
import java.io.File
import org.junit.Rule
import org.junit.Test

/**
 * An example of [DocLocScreenshotTestCase] usage.
 * For more information see DocLoc wiki page.
 */
class ScreenshotSampleTest : DocLocScreenshotTestCase(
    screenshotsDirectory = File("screenshots"),
    locales = "en,ru"
) {

    @get:Rule
    val runtimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java, false, true)

    @ScreenShooterTest
    @Test
    fun test() {
        before {
        }.after {
        }.run {
            step("1. Launch activity") {
                UiMainScreen {
                    flakyButton { click() }
                }
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