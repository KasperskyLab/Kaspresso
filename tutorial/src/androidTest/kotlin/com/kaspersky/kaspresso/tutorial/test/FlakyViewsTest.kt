package com.kaspersky.kaspresso.tutorial.test

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.tutorial.flaky.FlakyActivity
import com.kaspersky.kaspresso.tutorial.flaky.ResultActivity
import com.kaspersky.kaspresso.tutorial.screen.FlakyScreen
import com.kaspersky.kaspresso.tutorial.screen.ResultScreen
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.TimeUnit

class FlakyViewsTest : TestCase() {

    @get:Rule
    val activityTestRule = activityScenarioRule<FlakyActivity>()

    @Test
    fun flakyViewsTest() = run {
            step("Open Flaky screen") {

            step("Check ScrollView screen is visible") {
                FlakyScreen {
                    scrollView.isVisible()
                }
            }

            step("Check flaky text view is visible") {
                FlakyScreen {
                    flakyTextView {
                        flakySafely(timeoutMs = 3000) { isVisible() }
                        hasText("second element text")
                    }
                }
            }

            step("Check flaky text view's text") {
                FlakyScreen {
                    flakySafely(timeoutMs = TimeUnit.SECONDS.toMillis(4)) {
                        flakyTextView.hasText("new second element text")
                    }
                }
            }

            step("Check flaky button is visible") {
                FlakyScreen {
                    flakyBtn {
                        isVisible()
                        hasText("sixth element text")
                    }
                }
            }

            step("Check flaky button's text") {
                FlakyScreen {
                    flakySafely(timeoutMs = TimeUnit.SECONDS.toMillis(5)) {
                        flakyBtn {
                            hasText("new sixth element text")
                            click()
                        }
                    }
                }
            }

            step("Check success") {
                ResultScreen {
                    title {
                        isVisible()
                        hasText("Result activity")
                    }
                }
            }
        }
    }
}
