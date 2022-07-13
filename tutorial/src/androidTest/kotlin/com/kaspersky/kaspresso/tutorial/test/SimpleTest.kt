package com.kaspersky.kaspresso.tutorial.test

import android.Manifest
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.rule.GrantPermissionRule
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.tutorial.R
import com.kaspersky.kaspresso.tutorial.screen.SimpleScreen
import com.kaspersky.kaspresso.tutorial.simple.SimpleActivity
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
    val activityRule = activityScenarioRule<SimpleActivity>()

    @Test
    fun test() =
        before {

        }.after {

        }.run {
            step("Open Simple Screen") {
                SimpleScreen {
                    title {
                        isVisible()
                        hasText(R.string.simple_activity_default_title)
                        hasTextColor(R.color.black)
                    }

                    button {
                        isVisible()
                        withText(R.string.simple_activity_change_title_button)
                        isClickable()
                    }
                    input {
                        isVisible()
                        hasHint(R.string.simple_activity_input_hint)
                        hasEmptyText()
                    }
                }
            }

            step("Type \" Kaspresso \"") {
                SimpleScreen {
                    input.typeText("Kaspresso")
                    closeSoftKeyboard()
                    button.click()
                }
            }

            step("Check title content") {
                SimpleScreen {
                    title.hasText("Kaspresso")
                }
            }
        }
}
