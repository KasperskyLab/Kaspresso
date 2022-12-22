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
        step("Check display of elements") {
            FlakyScreen {
                text1.isDisplayed()
                text5.isDisplayed()
            }
        }
        step("Check initial elements") {
            FlakyScreen {
                text1.isVisible()
                text2.isVisible()
                text3.isVisible()
                text4.isVisible()
                text5.isVisible()
                progressBar1.isVisible()
                progressBar2.isVisible()
                progressBar3.isVisible()
                progressBar4.isVisible()
                progressBar5.isVisible()
            }
        }
        step("Check first element after loading") {
            FlakyScreen {
                text1.hasText(R.string.text_1)
                progressBar1.isGone()
            }
        }
        step("Check second element after loading") {
            FlakyScreen {
                text2.hasText(R.string.text_2)
                progressBar2.isGone()
            }
        }
        step("Check left elements after loading") {
            FlakyScreen {
                flakySafely(15000) {
                    text3.hasText(R.string.text_3)
                    progressBar3.isGone()
                    text4.hasText(R.string.text_4)
                    progressBar4.isGone()
                    text5.hasText(R.string.text_5)
                    progressBar5.isGone()
                }
            }
        }
    }
}
