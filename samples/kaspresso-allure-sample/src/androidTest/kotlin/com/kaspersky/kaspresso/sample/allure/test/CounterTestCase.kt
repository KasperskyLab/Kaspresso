package com.kaspersky.kaspresso.sample.allure.test

import androidx.test.rule.ActivityTestRule
import com.kaspersky.components.interceptors.allure.withAllureSupport
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.sample.allure.MainActivity
import com.kaspersky.kaspresso.sample.allure.screen.MainScreen
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test

/**
 * Use [withAllureSupport] function to add the all available interceptors.
 */
class CounterTestCase : TestCase(kaspressoBuilder = Kaspresso.Builder.advanced().withAllureSupport()) {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java, true, false)

    @Test
    fun counter() = run {
        step("Launch the app") {
            activityRule.launchActivity(null)

            MainScreen {
                incrementButton.isDisplayed()
                decrementButton.isDisplayed()
                clearButton.isDisplayed()
                valueText.isDisplayed()
            }
        }

        step("Check increase and decrease buttons") {
            step("Increase value up to five") {
                MainScreen {
                    incrementButton {
                        repeat(5) {
                            click()
                        }
                    }

                    assertValue(5)
                }
            }

            step("Decrease value down to three") {
                MainScreen {
                    decrementButton {
                        repeat(2) {
                            click()
                        }
                    }

                    assertValue(3)
                }
            }
        }

        step("Clear the value") {
            MainScreen() {
                clearButton.click()
                assertValue(0)
            }
        }
    }
}
