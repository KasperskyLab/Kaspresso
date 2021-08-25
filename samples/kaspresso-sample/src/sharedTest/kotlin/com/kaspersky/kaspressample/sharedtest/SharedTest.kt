package com.kaspersky.kaspressample.sharedtest

import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kaspersky.kaspressample.screen.kaspresso.SharedTestScreen
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import io.github.kakaocup.kakao.screen.Screen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SharedTest : TestCase(Kaspresso.Builder.simple(sharedTest = true)) {

    @get:Rule
    val activityRule = activityScenarioRule<SharedTestActivity>()

    @Test
    fun test() {
        before {
            activityRule.scenario
        }.after {
            //no-op
        }.run {
            step("Fill info") {
                Screen.onScreen<SharedTestScreen> {
                    firstNameEditText{
                        replaceText("Kaspersky")
                    }
                    lastNameEditText{
                        replaceText("Kaspresso")
                    }
                    ageEditText{
                        replaceText("8")
                    }
                    maleButton{
                        click()
                    }
                }
            }

            step("Verify Full Name info") {
                Screen.onScreen<SharedTestScreen> {
                    firstNameEditText {
                        hasText("Kaspersky")
                    }
                    lastNameEditText {
                        hasText("Kaspresso")
                    }
                }
            }

            step("Click on find me button") {
                Screen.onScreen<SharedTestScreen> {
                    findMeButton {
                        click()
                    }
                }
            }
        }
    }
}

