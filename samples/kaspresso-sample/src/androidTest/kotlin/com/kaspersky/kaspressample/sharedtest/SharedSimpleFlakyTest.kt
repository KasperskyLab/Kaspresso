package com.kaspersky.kaspressample.sharedtest

import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kaspersky.kaspressample.screen.SharedTestScreen
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SharedSimpleFlakyTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<SharedTestActivity>()

    @Test
    fun test() = run {
        step("Fill info") {
            SharedTestScreen {
                firstNameEditText {
                    replaceText("Kaspersky")
                }
                lastNameEditText {
                    replaceText("Kaspresso")
                }
                ageEditText {
                    replaceText("8")
                }
                maleButton {
                    click()
                }
            }
        }

        step("Verify Full Name info") {
            SharedTestScreen {
                firstNameEditText {
                    hasText("Kaspersky")
                }
                lastNameEditText {
                    hasText("Kaspresso")
                }
            }
        }

        step("Click on find me button") {
            SharedTestScreen {
                findMeButton {
                    click()
                }
            }
        }
    }
}
