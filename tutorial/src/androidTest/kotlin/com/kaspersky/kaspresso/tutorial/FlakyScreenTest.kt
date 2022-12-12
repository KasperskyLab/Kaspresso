package com.kaspersky.kaspresso.tutorial

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.tutorial.screen.FlakyScreen
import com.kaspersky.kaspresso.tutorial.screen.MainScreen
import org.junit.Rule
import org.junit.Test

class FlakyScreenTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun checkFlakyScreen() = run {
        step("Open flaky screen") {
            MainScreen {
                flakyActivityButton {
                    isVisible()
                    isClickable()
                    click()
                }
            }
        }
        step("Check initial elements") {
            FlakyScreen {
                button1.hasText(R.string.button_1)
                button2.hasText(R.string.button_2)
                button3.hasText(R.string.button_3)
                button4.hasText(R.string.button_4)
                button5.hasText(R.string.button_5)
                button6.hasText(R.string.button_6)
                button1.isDisplayed()
                button6.isNotDisplayed()
            }
        }
        step("Check scroll to end") {
            FlakyScreen {
                button1.isNotDisplayed()
                button6.isDisplayed()
            }
        }
        step("Check button 5 text changed") {
            FlakyScreen {
                button5.hasText(R.string.button_5_changed)
            }
        }
        step("Check scroll to start") {
            flakySafely(12_000) {
                FlakyScreen {
                    button6.isNotDisplayed()
                    button1.isDisplayed()
                }
            }
        }
        step("Check button 1 text changed") {
            FlakyScreen {
                button1.hasText(R.string.button_1_changed)
            }
        }
    }
}
