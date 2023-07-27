package com.kaspersky.kaspresso.kautomatorsample.kaspresso.sanity.dialogs_safety

import android.os.Build
import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.kautomatorsample.screen.SystemDialogsScreen
import com.kaspersky.kaspresso.kautomatorsample.systemdialogs.SystemDialogsActivity
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Assume
import org.junit.Rule
import org.junit.Test

class SystemDialogSafetyObjectEnabledSanityTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<SystemDialogsActivity>()

    @Test
    fun test() = run {

        Assume.assumeTrue(
            "No valid ways to test system dialogs on Lollipop, ignore on <23 Api",
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
        )

        step("Click first button") {
            SystemDialogsScreen {
                flakySafely(3000) {
                    btn1 {
                        doubleClick()
                    }
                }
            }
        }
        Thread.sleep(3000L)
        step("Click second button") {
            SystemDialogsScreen {
                flakySafely(3000) {
                    btn2 {
                        isDisplayed()
                        click()
                    }
                }
            }
        }
    }
}
