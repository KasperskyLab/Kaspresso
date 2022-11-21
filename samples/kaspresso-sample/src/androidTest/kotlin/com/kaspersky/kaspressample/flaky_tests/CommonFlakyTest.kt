package com.kaspersky.kaspressample.flaky_tests

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspressample.MainActivity
import com.kaspersky.kaspressample.R
import com.kaspersky.kaspressample.screen.CommonFlakyScreen
import com.kaspersky.kaspressample.screen.MainScreen
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test

class CommonFlakyTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun test() = run {
        step("Open Scroll View Stub Screen") {
            MainScreen {
                flakyButton {
                    click()
                }
            }
        }

        step("Check ScrollView screen is displayed") {
            CommonFlakyScreen {
                scrollView {
                    isDisplayed()
                }
            }
        }

        step("Check btn5's text") {
            CommonFlakyScreen {
                btn5 {
                    // automate flaky safety handling is in action
                    // the text is changing during 3 seconds
                    // the default value of flaky safety timeout = 10 seconds
                    hasText(R.string.common_flaky_final_button)
                }
            }
        }

        step("Check tv6's text") {
            CommonFlakyScreen {
                tv6 {
                    // here, the text will be changing longer(summary = 15 seconds) than
                    //     the default value of flaky safety timeout(10 seconds)
                    // that's why we use flakySafely method obviously
                    flakySafely(timeoutMs = 16_000) {
                        hasText(R.string.common_flaky_final_textview)
                    }
                }
            }
        }
    }
}
