package com.kaspersky.kaspressample.measure_tests

import android.Manifest
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import androidx.test.rule.GrantPermissionRule
import com.kaspersky.kaspressample.MainActivity
import com.kaspersky.kaspressample.R
import com.kaspersky.kaspressample.external_screens.UiMainScreen
import com.kaspersky.kaspressample.external_screens.UiMeasureScreen
import com.kaspersky.kaspresso.idlewaiting.KautomatorWaitForIdleSettings
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class KautomatorMeasureTest : TestCase(
    kaspressoBuilder = Kaspresso.Builder.advanced {
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
    val activityTestRule = ActivityTestRule(MainActivity::class.java, true, false)

    @Test
    fun test() =
        before {
            activityTestRule.launchActivity(null)
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
                            hasText(device.targetContext.getString(R.string.measure_fragment_text_button_1).toUpperCase())
                        }
                    }
                }
            }

            step("Measure screen. Button_2 clicks and TextView changes comparing") {
                UiMeasureScreen {
                    RANGE.forEach { index ->
                        button2 {
                            click()
                            hasText(device.targetContext.getString(R.string.measure_fragment_text_button_2).toUpperCase())
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