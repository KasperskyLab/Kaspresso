package com.kaspersky.kaspressample.idlingwait_tests

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspressample.MainActivity
import com.kaspersky.kaspressample.R
import com.kaspersky.kaspressample.external_screens.UiMainScreen
import com.kaspersky.kaspressample.external_screens.UiWaitForIdleScreen
import com.kaspersky.kaspresso.idlewaiting.KautomatorWaitForIdleSettings
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test

class WaitForIdleTest : TestCase(
    kaspressoBuilder = Kaspresso.Builder.simple {
        kautomatorWaitForIdleSettings = KautomatorWaitForIdleSettings.boost()
    }
) {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun test() = run {
        step("Open Wait for Idle Screen") {
            UiMainScreen {
                idleWaitingButton {
                    click()
                }
            }
        }

        step("Check text in EditText") {
            UiWaitForIdleScreen {
                edit {
                    isDisplayed()
                    containsText(device.targetContext.getString(R.string.idlewaiting_fragment_text_edittext))
                }
            }
        }
    }
}
