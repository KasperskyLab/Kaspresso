package com.kaspersky.kaspresso.kautomatorsample.test

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.kautomatorsample.MainActivity
import com.kaspersky.kaspresso.kautomatorsample.screen.MainNativeScreen
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test

/**
 * The sample of how to use native resources in combination with kAutomator
 */
class NativeSimpleTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun nativeTest() = run {
        step("Input text in EditText and check it") {
            MainNativeScreen {
                simpleEditText {
                    replaceText("Kaspresso")
                    hasText("Kaspresso")
                }
            }
        }
        step("Type more text and check it") {
            MainNativeScreen {
                simpleEditText {
                    typeText(" is super useful")
                    hasText("Kaspresso is super useful")
                }
            }
        }
        step("Click button") {
            MainNativeScreen {
                simpleButton {
                    click()
                }
            }
        }
        step("Click checkbox and check it") {
            MainNativeScreen {
                checkBox {
                    setChecked(true)
                    isChecked()
                }
            }
        }
        step("Check headers") {
            MainNativeScreen {
                header { isDisplayed() }
                subHeader { isDisplayed() }
            }
        }
        step("Check image") {
            MainNativeScreen {
                image { isDisplayed() }
            }
        }
    }
}
