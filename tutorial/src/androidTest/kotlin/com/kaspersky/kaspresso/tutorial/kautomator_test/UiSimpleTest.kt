package com.kaspersky.kaspresso.tutorial.kautomator_test

import android.Manifest
import androidx.test.rule.ActivityTestRule
import androidx.test.rule.GrantPermissionRule
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.tutorial.MainActivity
import com.kaspersky.kaspresso.tutorial.R
import com.kaspersky.kaspresso.tutorial.kautomator_screen.UiMainScreen
import com.kaspersky.kaspresso.tutorial.kautomator_screen.UiSimpleScreen
import io.github.kakaocup.kakao.common.utilities.getResourceString
import org.junit.Rule
import org.junit.Test

/**
 * In this example you can see a test tuned by default Kaspresso configuration.
 * When you start the test you can see output of default Kaspresso interceptors:
 * - a lot of useful logs
 * - failure handling
 * - screenshots in the device
 * Also you can see the test DSL simplifying the writing of any test
 */
class UiSimpleTest : TestCase() {

    @get:Rule
    val runtimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    @get:Rule
    val activityTestRule = ActivityTestRule(MainActivity::class.java, true, false)

    @Test
    fun test() =
        before {
            testLogger.i("Before section")
        }.after {
            testLogger.i("After section")
        }.run {
            step("Open Simple Screen") {
                testLogger.i("Main section")
                activityTestRule.launchActivity(null)
                device.screenshots.take("Additional_screenshot")

                UiMainScreen {
                    simpleButton {
                        isDisplayed()
                        click()
                    }
                }
            }

            step("Click button_1 and check button_2") {
                UiSimpleScreen {
                    button1 {
                        click()
                    }
                    button2 {
                        isDisplayed()
                    }
                }
            }

            step("Click button_2 and check edit") {
                UiSimpleScreen {
                    button2 {
                        click()
                    }
                    edit {
                        flakySafely(timeoutMs = 7000) { isDisplayed() }
                        hasText(getResourceString(R.string.simple_fragment_text_edittext))
                    }
                }
            }

            step("Check all possibilities of edit") {
                scenario(
                    UiCheckEditScenario()
                )
            }
        }
}
