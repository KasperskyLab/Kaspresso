package com.kaspersky.kaspressample.continuously_tests

import android.Manifest
import android.os.Build
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.rule.GrantPermissionRule
import com.kaspersky.kaspressample.MainActivity
import com.kaspersky.kaspressample.external_screens.UiContinuouslyDialogScreen
import com.kaspersky.kaspressample.external_screens.UiContinuouslyScreen
import com.kaspersky.kaspressample.external_screens.UiMainScreen
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Assume
import org.junit.Rule
import org.junit.Test

class UiContinuouslyTest : TestCase() {

    companion object {
        private const val FAKE_MAX_DELAY_MS: Long = 5_000
    }

    @get:Rule
    val runtimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun testDialogPresentUntilAndroidO() {
        // Don`t allow to run this test on Android >= Oreo
        Assume.assumeTrue(Build.VERSION.SDK_INT < Build.VERSION_CODES.O)
        run {

            step("Open Continuously Screen") {
                UiMainScreen {
                    continuouslyButton {
                        click()
                    }
                }
            }
            step("Push start button") {
                UiContinuouslyScreen {
                    startButton {
                        click()
                    }
                }
            }
            step("Check dialog appeared") {
                UiContinuouslyDialogScreen {
                    dialogTitle {
                        isDisplayed()
                    }
                }
            }
        }
    }

    @Test
    fun testDialogNotPresentAfterAndroidO() {
        // Don`t allow to run this test on Android < Oreo
        Assume.assumeTrue(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)

        run {

            step("Open Continuously Screen") {
                UiMainScreen {
                    continuouslyButton {
                        click()
                    }
                }
            }
            step("Push start button") {
                UiContinuouslyScreen {
                    startButton {
                        click()
                    }
                }
            }
            step("Check dialog not appeared") {
                UiContinuouslyDialogScreen {
                    continuously(FAKE_MAX_DELAY_MS) {
                        dialogTitle {
                            isNotDisplayed()
                        }
                    }
                }
            }
        }
    }
}
