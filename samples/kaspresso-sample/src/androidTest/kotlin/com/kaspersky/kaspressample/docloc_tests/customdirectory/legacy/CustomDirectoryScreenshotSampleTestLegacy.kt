package com.kaspersky.kaspressample.docloc_tests.customdirectory.legacy

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
import java.io.File

/**
 * An example of how to change default screenshots directories and file names.
 * As result all screenshots will be stored at '/sdcard/custom_directory/<locale>' path.
 * Screenshot file names will have 'screenshot#<index>' prefix.
 * See [FlatDirectoryProvider] and [AutoNumeratedNameProvider] implementations.
 */
class CustomDirectoryScreenshotSampleTestLegacy : DocLocScreenshotTestCase(
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
    val activityRule = activityScenarioRule<SimpleActivity>()

    @ScreenShooterTest
    @Test
    fun test() = run {
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
