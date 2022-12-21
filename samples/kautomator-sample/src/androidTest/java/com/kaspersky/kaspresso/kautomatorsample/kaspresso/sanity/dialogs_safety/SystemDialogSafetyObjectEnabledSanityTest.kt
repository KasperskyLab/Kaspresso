package com.kaspersky.kaspresso.kautomatorsample.kaspresso.sanity.dialogs_safety

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.kautomatorsample.screen.SystemDialogsScreen
import com.kaspersky.kaspresso.kautomatorsample.systemdialogs.SystemDialogsActivity
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test

class SystemDialogSafetyObjectEnabledSanityTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<SystemDialogsActivity>()

    @Test
    fun test() = run {
        step("Click first button") {
            SystemDialogsScreen {
                btn1 {
                    click()
                }
            }
        }

        step("Click second button") {
            SystemDialogsScreen {
                btn2 {
                    isDisplayed()
                    click()
                }
            }
        }
    }
}
