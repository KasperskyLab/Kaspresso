package com.kaspersky.kaspressample.robolectric

import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kaspersky.kaspressample.screen.RobolectricScreen
import com.kaspersky.kaspresso.kaspresso.Kaspresso

import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import io.github.kakaocup.kakao.screen.Screen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RobolectricTest : TestCase(Kaspresso.Builder.simple(sharedTest = true)) {

    @get:Rule
    val activityRule = activityScenarioRule<RobolectricActivity>()

    @Test
    fun test() {
        before {
            activityRule.scenario
        }.after {

        }.run {
            step("Fill info") {
                Screen.onScreen<RobolectricScreen> {
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
                Screen.onScreen<RobolectricScreen> {
                    firstNameEditText {
                        hasText("Kaspersky")
                    }
                    lastNameEditText {
                        hasText("Kaspresso")
                    }
                }
            }

            step("Click on find me button") {
                Screen.onScreen<RobolectricScreen> {
                    findMeButton {
                        click()
                    }
                }
            }
        }
    }
}

