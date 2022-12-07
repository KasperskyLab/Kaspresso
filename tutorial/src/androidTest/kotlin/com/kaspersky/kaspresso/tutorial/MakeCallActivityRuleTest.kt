package com.kaspersky.kaspresso.tutorial

import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.rule.GrantPermissionRule
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.tutorial.screen.MainScreen
import com.kaspersky.kaspresso.tutorial.screen.MakeCallActivityScreen
import com.kaspersky.kaspresso.tutorial.screen.PhoneCallScreen
import org.junit.Rule
import org.junit.Test

class MakeCallActivityRuleTest : TestCase() {

    @get:Rule
    val grantPermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        android.Manifest.permission.CALL_PHONE
    )

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun checkSuccessCall() = before {

    }.after {
        PhoneCallScreen {
            endCallButton.click()
        }
    }.run {
        step("Open make call activity") {
            MainScreen {
                makeCallActivityButton {
                    isVisible()
                    isClickable()
                    click()
                }
            }
        }
        step("Check UI elements") {
            MakeCallActivityScreen {
                inputNumber.isVisible()
                inputNumber.hasHint(R.string.phone_number_hint)
                makeCallButton.isVisible()
                makeCallButton.isClickable()
                makeCallButton.hasText(R.string.make_call_btn)
            }
        }
        step("Try to call number") {
            MakeCallActivityScreen {
                inputNumber.replaceText("111")
                makeCallButton.click()
            }
        }
        step("Check call screen") {
            PhoneCallScreen {
                contactName.isDisplayed()
                contactName.hasText("111")
                endCallButton.isDisplayed()
                endCallButton.isClickable()
            }
        }
    }
}
