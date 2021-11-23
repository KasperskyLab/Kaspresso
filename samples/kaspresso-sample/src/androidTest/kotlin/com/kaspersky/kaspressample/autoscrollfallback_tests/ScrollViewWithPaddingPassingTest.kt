package com.kaspersky.kaspressample.autoscrollfallback_tests

import android.Manifest
import androidx.test.rule.ActivityTestRule
import androidx.test.rule.GrantPermissionRule
import com.kaspersky.kaspressample.MainActivity
import com.kaspersky.kaspressample.screen.MainScreen
import com.kaspersky.kaspressample.screen.ScrollViewWithPaddingScreen
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test

class ScrollViewWithPaddingPassingTest : TestCase() {

    @get:Rule
    val runtimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    @get:Rule
    val activityTestRule = ActivityTestRule(MainActivity::class.java, true, false)

    @Test
    fun click_button_in_the_middle() =
        run {
            step("Open Auto Scroll Fallback Screen") {
                activityTestRule.launchActivity(null)
                testLogger.i("I am testLogger")
                device.screenshots.take("Additional_screenshot")
                MainScreen {
                    autoScrollFallbackButton {
                        isVisible()
                        click()
                    }
                }
            }

            step("Click button_18, middle item") {
                ScrollViewWithPaddingScreen {
                    button18 {
                        click()
                    }
                }
            }
        }

    @Test
    fun click_last_button() =
        run {
            step("Open Auto Scroll Fallback Screen") {
                activityTestRule.launchActivity(null)
                testLogger.i("I am testLogger")
                device.screenshots.take("Additional_screenshot")
                MainScreen {
                    autoScrollFallbackButton {
                        isVisible()
                        click()
                    }
                }
            }

            step("Click button_20, last item") {
                ScrollViewWithPaddingScreen {
                    button20 {
                        click()
                    }
                }
            }
        }

    @Test
    fun click_hbutton_in_the_end() =
        run {
            step("Open Auto Scroll Fallback Screen") {
                activityTestRule.launchActivity(null)
                testLogger.i("I am testLogger")
                device.screenshots.take("Additional_screenshot")
                MainScreen {
                    autoScrollFallbackButton {
                        isVisible()
                        click()
                    }
                }
            }

            step("Click hbutton_7, last item") {
                ScrollViewWithPaddingScreen {
                    hbutton7 {
                        click()
                    }
                }
            }
        }
}
