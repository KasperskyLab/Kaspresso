package com.kaspersky.kaspresso.tutorial.test

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.tutorial.MainActivity
import com.kaspersky.kaspresso.tutorial.R
import com.kaspersky.kaspresso.tutorial.screen.MainScreen
import com.kaspersky.kaspresso.tutorial.screen.SimpleScreen
import org.junit.Rule
import org.junit.Test

/**
 * In this example you can see a test tuned by default Kaspresso configuration.
 * When you start the test you can see output of default Kaspresso interceptors:
 * - a lot of useful logs
 * - failure handling
 * - screenshots in the device
 * Also you can see the test DSL simplifying the writing of any test
 */
class SimpleTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun test() =
        before {
            testLogger.i("Before section")
        }.after {
            testLogger.i("After section")
        }.run {
            step("Open Simple Screen") {
                testLogger.i("Main section")
                device.screenshots.take("Additional_screenshot")

                MainScreen {
                    simpleButton {
                        isVisible()
                        click()
                    }
                }
            }

            step("Click button_1 and check button_2") {
                SimpleScreen {
                    button1 {
                        click()
                    }
                    button2 {
                        isVisible()
                    }
                }
            }

            step("Click button_2 and check edit") {
                SimpleScreen {
                    button2 {
                        click()
                    }
                    edit {
                        flakySafely(timeoutMs = 7000) { isVisible() }
                        hasText(R.string.simple_fragment_text_edittext)
                    }
                }
            }

            step("Check all possibilities of edit") {
                scenario(
                    CheckEditScenario()
                )
            }
    }
}
