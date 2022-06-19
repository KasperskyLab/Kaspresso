package com.kaspersky.kaspressample.simple_tests

import android.Manifest
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.rule.GrantPermissionRule
import com.kaspersky.kaspressample.MainActivity
import com.kaspersky.kaspressample.R
import com.kaspersky.kaspressample.screen.MainScreen
import com.kaspersky.kaspressample.screen.SimpleScreen
import com.kaspersky.kaspresso.testcases.api.testcaserule.TestCaseRule
import org.junit.Rule
import org.junit.Test

/**
 * In this example you can observe a test tuned by default Kaspresso configuration.
 * When you start the test you can see output of default Kaspresso interceptors:
 * - a lot of useful logs
 * - failure handling
 * - screenshots in the device
 * Also you can observe the test dsl simplifying a writing of any test
 */
class SimpleTestWithRule {

    @get:Rule
    val runtimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @get:Rule
    val testCaseRule = TestCaseRule(javaClass.simpleName)

    @Test
    fun test() =
        testCaseRule.run {
            step("Open Simple Screen") {
                testLogger.i("I am testLogger")
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
