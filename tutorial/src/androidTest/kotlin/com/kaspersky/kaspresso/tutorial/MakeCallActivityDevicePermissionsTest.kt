package com.kaspersky.kaspresso.tutorial

import android.os.Build
import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.tutorial.screen.MainScreen
import com.kaspersky.kaspresso.tutorial.screen.MakeCallActivityScreen
import com.kaspersky.kaspresso.tutorial.screen.PhoneCallScreen
import org.junit.Assert
import org.junit.Assume
import org.junit.Rule
import org.junit.Test

class MakeCallActivityDevicePermissionsTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun checkSuccessCall() {
        Assume.assumeTrue(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)

        before {
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
            step("Accept permission") {
                device.permissions.apply {
                    Assert.assertTrue(isDialogVisible())
                    allowViaDialog()
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

    @Test
    fun checkCallIfPermissionDenied() {
        Assume.assumeTrue(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)

        run {
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
            step("Deny permission") {
                device.permissions.apply {
                    Assert.assertTrue(isDialogVisible())
                    denyViaDialog()
                }
            }
            step("Check stay on the same screen") {
                MakeCallActivityScreen {
                    inputNumber.isDisplayed()
                    makeCallButton.isDisplayed()
                }
            }
        }
    }
}
