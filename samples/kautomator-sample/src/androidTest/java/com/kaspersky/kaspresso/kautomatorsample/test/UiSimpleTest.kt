package com.kaspersky.kaspresso.kautomatorsample.test

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.kautomatorsample.MainActivity
import com.kaspersky.kaspresso.kautomatorsample.screen.MainScreen
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test

/**
 * The simple sample of how Kautomator looks and
 *     its beautiful possibility to intercept all actions and assertions as Kakao does
 */
class UiSimpleTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun upgradeTest() {
        before {
        }.after {
        }.run {

            step("Input text in EditText and check it") {
                MainScreen {
                    simpleEditText {
                        replaceText("Kaspresso")
                        hasText("Kaspresso")
                    }
                }
            }
            step("Type more text and check it") {
                MainScreen {
                    simpleEditText {
                        typeText(" is super useful")
                        hasText("Kaspresso is super useful")
                    }
                }
            }
            step("Click button") {
                MainScreen {
                    simpleButton {
                        click()
                    }
                }
            }
            step("Click checkbox and check it") {
                MainScreen {
                    checkBox {
                        setChecked(true)
                        isChecked()
                    }
                }
            }
        }
    }
}
