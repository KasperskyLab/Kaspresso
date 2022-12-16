package com.kaspersky.kaspresso.tutorial

import android.content.Context
import android.media.AudioManager
import android.os.Build
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.filters.SdkSuppress
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.tutorial.screen.MainScreen
import com.kaspersky.kaspresso.tutorial.screen.MakeCallActivityScreen
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class MakeCallActivityDevicePermissionsTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    private val testNumber = "111"

    @Test
    fun checkSuccessCall() = before {
    }.after {
        device.phone.cancelCall(testNumber)
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
                inputNumber.replaceText(testNumber)
                makeCallButton.click()
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            step("Accept permission") {
                device.permissions.apply {
                    flakySafely {
                        Assert.assertTrue(isDialogVisible())
                        allowViaDialog()
                    }
                }
            }
        }
        step("Check phone is calling") {
            flakySafely {
                val manager = device.context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
                Assert.assertTrue(manager.mode == AudioManager.MODE_IN_CALL)
            }
        }
    }

    @SdkSuppress(minSdkVersion = 23)
    @Test
    fun checkCallIfPermissionDenied() = run {
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
                inputNumber.replaceText(testNumber)
                makeCallButton.click()
            }
        }
        step("Deny permission") {
            device.permissions.apply {
                flakySafely {
                    Assert.assertTrue(isDialogVisible())
                    denyViaDialog()
                }
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
