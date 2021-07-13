package com.kaspersky.kaspressample.robolectric

import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kaspersky.kaspressample.screen.RobolectricScreen
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import io.github.kakaocup.kakao.screen.Screen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RobolectricTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<RobolectricActivity>()

    @Test
    fun test() {
        before {
            activityRule.scenario
        }.after {

        }.run {
            step("Fill info and find button") {
                Screen.onScreen<RobolectricScreen> {
                    writeFirstName("Sergio")
                    writeLastName("Sastre Florez")
                    writeAge("8")
                    clickOnMale()
                }
            }
            step("Check Info filled") {
                Screen.onScreen<RobolectricScreen> {
                    verifyFirstNameIs("Sergio")
                }
            }

            step("Click on find button") {
                Screen.onScreen<RobolectricScreen> {
                    findMeButton {
                        hasText("FIND ME")
                    }
                }
            }
        }
    }


    @Test
    fun test3() {
        before {
            activityRule.scenario
        }.after {

        }.run {
            step("Fill info and find button") {
                Screen.onScreen<RobolectricScreen> {
                    writeFirstName("Sergio")
                    writeLastName("Sastre Florez")
                    writeAge("8")
                    clickOnMale()
                }
            }

            step("Check Info filled") {
                Screen.onScreen<RobolectricScreen> {
                    verifyFirstNameIs("Sergio")
                }
            }

            step("Click on find button") {
                Screen.onScreen<RobolectricScreen> {
                    clickOnFindMeButton()
                }
            }

        }
    }

    @Test
    fun test4() {
        before {
            activityRule.scenario
        }.after {

        }.run {
            step("Fill info and find button") {
                Screen.onScreen<RobolectricScreen> {
                    writeFirstName("Sergio")
                    writeLastName("Sastre Florez")
                    writeAge("8")
                    clickOnMale()
                }
            }

            step("Check Info filled") {
                Screen.onScreen<RobolectricScreen> {
                    verifyFirstNameIs("Sergio")
                }
            }

            step("Click on find button") {
                Screen.onScreen<RobolectricScreen> {
                    clickOnFindMeButton()
                }
            }
        }
    }
}

