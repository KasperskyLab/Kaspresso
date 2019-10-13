package com.kaspersky.kaspressample.flaky_tests

import android.Manifest
import android.os.Build
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import androidx.test.rule.GrantPermissionRule
import com.kaspersky.kaspressample.MainActivity
import com.kaspersky.kaspressample.screen.CheckDuringScreen
import com.kaspersky.kaspressample.screen.MainScreen
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import java.util.concurrent.TimeUnit
import org.junit.Assume
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CheckDuringTest : TestCase() {

    companion object {
        private val FAKE_MAX_DELAY = TimeUnit.SECONDS.toMillis(5)
    }

    @get:Rule
    val runtimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    @get:Rule
    val activityTestRule = ActivityTestRule(MainActivity::class.java, true, false)

    @Test
    fun testDialogPresentUntilAndroidO() {
        before {
            activityTestRule.launchActivity(null)
        }.after {
        }.run {

            // Don`t allow to run this test on Android >= Oreo
            Assume.assumeTrue(Build.VERSION.SDK_INT < Build.VERSION_CODES.O)

            step("Open Check During Screen") {
                MainScreen {
                    checkDuringButton {
                        click()
                    }
                }
            }
            step("Push start button") {
                CheckDuringScreen {
                    startButton {
                        click()
                    }
                }
            }
            step("Check dialog appeared") {
                CheckDuringScreen {
                    flakySafely(FAKE_MAX_DELAY) {
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
        before {
            activityTestRule.launchActivity(null)
        }.after {
        }.run {

            // Don`t allow to run this test on Android < Oreo
            Assume.assumeTrue(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)

            step("Open Check During Screen") {
                MainScreen {
                    checkDuringButton {
                        click()
                    }
                }
            }
            step("Push start button") {
                CheckDuringScreen {
                    startButton {
                        click()
                    }
                }
            }
            step("Check dialog not appeared") {
                CheckDuringScreen {
                    checkDuring(FAKE_MAX_DELAY) {
                        dialogTitle {
                            doesNotExist()
                        }
                    }
                }
            }
        }
    }
}