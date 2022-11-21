package com.kaspersky.kaspressample.measure_tests

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspressample.MainActivity
import com.kaspersky.kaspressample.R
import com.kaspersky.kaspressample.external_screens.UiMainScreen
import com.kaspersky.kaspressample.external_screens.UiMeasureScreen
import com.kaspersky.kaspresso.idlewaiting.KautomatorWaitForIdleSettings
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
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
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun test() = run {
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
                        hasText(device.targetContext.getString(R.string.measure_fragment_text_button_1).uppercase())
                    }
                }
            }
        }

        step("Measure screen. Button_2 clicks and TextView changes comparing") {
            UiMeasureScreen {
                RANGE.forEach { index ->
                    button2 {
                        click()
                        hasText(device.targetContext.getString(R.string.measure_fragment_text_button_2).uppercase())
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
