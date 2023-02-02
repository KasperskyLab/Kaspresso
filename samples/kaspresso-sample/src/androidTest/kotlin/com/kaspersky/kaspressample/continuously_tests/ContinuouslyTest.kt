package com.kaspersky.kaspressample.continuously_tests

import android.Manifest
import android.os.Build
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.rule.GrantPermissionRule
import com.kaspersky.kaspressample.MainActivity
import com.kaspersky.kaspressample.screen.ContinuouslyDialogScreen
import com.kaspersky.kaspressample.screen.ContinuouslyScreen
import com.kaspersky.kaspressample.screen.MainScreen
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Assume
import org.junit.Rule
import org.junit.Test

class ContinuouslyTest : TestCase() {

    companion object {
        private const val FAKE_MAX_DELAY_MS: Long = 15_000
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
                MainScreen {
                    continuouslyButton {
                        click()
                    }
                }
            }
            step("Push start button") {
                ContinuouslyScreen {
                    startButton {
                        click()
                    }
                }
            }
            step("Check dialog appeared") {
                ContinuouslyDialogScreen {
                    flakySafely(FAKE_MAX_DELAY_MS) {
                        dialogTitle {
                            isVisible()
                        }
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
                MainScreen {
                    continuouslyButton {
                        click()
                    }
                }
            }
            step("Push start button") {
                ContinuouslyScreen {
                    startButton {
                        click()
                    }
                }
            }
            step("Check dialog not appeared") {
                ContinuouslyDialogScreen {
                    continuously(FAKE_MAX_DELAY_MS) {
                        dialogTitle {
                            doesNotExist()
                        }
                    }
                }
            }
        }
    }
}
