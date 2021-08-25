package com.kaspersky.kaspressample.sharedtest

import android.Manifest
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import androidx.test.rule.GrantPermissionRule
import com.kaspersky.kaspressample.MainActivity
import com.kaspersky.kaspressample.screen.kautomator.UiContinuouslyDialogScreen
import com.kaspersky.kaspressample.screen.kautomator.UiContinuouslyScreen
import com.kaspersky.kaspressample.screen.kautomator.UiMainScreen
import com.kaspersky.kaspresso.failure.exceptions.KautomatorOnSharedTestException
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FailingKautomatorSharedTest : TestCase(Kaspresso.Builder.simple(sharedTest = true)) {

    @get:Rule
    val runtimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    @get:Rule
    val activityTestRule = ActivityTestRule(MainActivity::class.java, true, false)

    @Test(expected = KautomatorOnSharedTestException::class)
    fun testDialogPresentUntilAndroidO() {
        before {
            activityTestRule.launchActivity(null)
        }.after {
        }.run {

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
}
