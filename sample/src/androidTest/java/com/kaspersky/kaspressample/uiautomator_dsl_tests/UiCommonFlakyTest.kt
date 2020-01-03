package com.kaspersky.kaspressample.uiautomator_dsl_tests

import android.Manifest
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import androidx.test.rule.GrantPermissionRule
import com.kaspersky.components.uiautomator_dsl.dsl.system.UiSystem
import com.kaspersky.kaspressample.MainActivity
import com.kaspersky.kaspressample.R
import com.kaspersky.kaspressample.external_screens.UiCommonFlakyScreen
import com.kaspersky.kaspressample.external_screens.UiMainScreen
import com.kaspersky.kaspressample.screen.CommonFlakyScreen
import com.kaspersky.kaspressample.screen.MainScreen
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import java.util.concurrent.TimeUnit
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UiCommonFlakyTest : TestCase() {

    @get:Rule
    val runtimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    @get:Rule
    val activityTestRule = ActivityTestRule(MainActivity::class.java, true, false)

    @Test
    fun test() {
        before {
            activityTestRule.launchActivity(null)
        }.after {
        }.run {
            step("Open Scroll View Stub Screen") {
                UiMainScreen {
                    scrollViewStubButton {
                        click()
                    }
                }
            }

            step("Check ScrollView screen is visible") {
                UiCommonFlakyScreen {
                    // todo the same check in Kakao is called as isVisible.
                    // todo maybe we need to add isVisible to UiAutomator DSL
                    scrollView {
                        isDisplayed()
                        // todo In UiAutomator DSL there is not autoscroll feature of all Scrollable views.
                        // todo That's why we are forced to make scrolls manually
                        // todo maybe to fix it somehow
                        swipeUp()
                    }
                }
            }

            step("Check btn5's text") {
                UiCommonFlakyScreen {
                    btn5 {
                        hasText(device.targetContext.getString(R.string.common_flaky_final_button).toUpperCase())
                    }
                }
            }

            step("Check tv6's text") {
                UiCommonFlakyScreen {
                    tv6 {
                        hasText(device.targetContext.getString(R.string.common_flaky_final_textview))
                    }
                }
            }
        }
    }
}