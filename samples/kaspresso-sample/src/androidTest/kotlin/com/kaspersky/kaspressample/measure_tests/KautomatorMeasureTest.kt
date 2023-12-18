package com.kaspersky.kaspressample.measure_tests

import android.Manifest
import android.os.Build
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.rule.GrantPermissionRule
import com.kaspersky.kaspressample.MainActivity
import com.kaspersky.kaspressample.R
import com.kaspersky.kaspressample.external_screens.UiMainScreen
import com.kaspersky.kaspressample.external_screens.UiMeasureScreen
import com.kaspersky.kaspresso.idlewaiting.KautomatorWaitForIdleSettings
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Assume
import org.junit.Rule
import org.junit.Test

class KautomatorMeasureTest : TestCase(
    kaspressoBuilder = Kaspresso.Builder.simple {
        kautomatorWaitForIdleSettings = KautomatorWaitForIdleSettings.boost()
    }
) {

    companion object {
        private val RANGE = 0..20
    }

    @get:Rule
    val runtimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun test() = before {
        Assume.assumeTrue(" KautomatorWaitForIdleSettings.boost() works incorrectly on Android 5", Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP)
    }.after { }.run {
        step("MainScreen. Click on `measure fragment` button") {
            UiMainScreen {
                measureButton {
                    isDisplayed()
                    click()
                }
            }
        }

        step("Measure screen. Button_1 clicks comparing") {
            UiMeasureScreen {
                RANGE.forEach { _ ->
                    button1 {
                        click()
                        hasText(device.targetContext.getString(R.string.measure_fragment_text_button_1))
                    }
                }
            }
        }

        step("Measure screen. Button_2 clicks and TextView changes comparing") {
            UiMeasureScreen {
                RANGE.forEach { index ->
                    button2 {
                        click()
                        hasText(device.targetContext.getString(R.string.measure_fragment_text_button_2))
                    }
                    textView {
                        hasText(
                            "${device.targetContext.getString(R.string.measure_fragment_text_textview)}${index + 1}"
                        )
                    }
                }
            }
        }

        step("Measure fragment. EditText updates comparing") {
            UiMeasureScreen {
                edit {
                    isDisplayed()
                    hasText(device.targetContext.getString(R.string.measure_fragment_text_edittext))
                    RANGE.forEach { _ ->
                        clearText()
                        typeText("bla-bla-bla")
                        hasText("bla-bla-bla")
                        clearText()
                        typeText("mo-mo-mo")
                        hasText("mo-mo-mo")
                        clearText()
                    }
                }
            }
        }

        step("Measure fragment. Checkbox clicks comparing") {
            UiMeasureScreen {
                RANGE.forEach { index ->
                    checkBox {
                        if (index % 2 == 0) {
                            setChecked(true)
                            isChecked()
                        } else {
                            setChecked(false)
                            isNotChecked()
                        }
                    }
                }
            }
        }
    }
}
