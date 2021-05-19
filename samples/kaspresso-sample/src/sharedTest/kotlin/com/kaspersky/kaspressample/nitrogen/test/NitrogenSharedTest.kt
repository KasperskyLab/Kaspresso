package com.kaspersky.kaspressample.nitrogen.test

import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.agoda.kakao.screen.Screen.Companion.onScreen
import com.kaspersky.kaspressample.nitrogen.NitrogenActivity
import com.kaspersky.kaspressample.nitrogen.screen.NitrogenFragmentScrollingScreen
import com.kaspersky.kaspressonitrogen.testcases.api.testcase.NitrogenTestCase
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NitrogenSharedTest : NitrogenTestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<NitrogenActivity>()

    @Test
    fun kaspressoTest() {
        before {
            activityRule.scenario
        }.after {

        }.run {
            step("Fill info and find button") {
                onScreen<NitrogenFragmentScrollingScreen> {
                    writeFirstName("Kaspresso")
                    writeLastName("Nitrogen")
                    writeAge("8")
                    clickOnMale()
                }
            }
            step("Check Info filled") {
                onScreen<NitrogenFragmentScrollingScreen> {
                    verifyFirstNameIs("Kaspresso")
                    verifyLastNameIs("Nitrogen")
                }
            }

            step("Click on find button") {
                onScreen<NitrogenFragmentScrollingScreen> {
                    clickOnFindMeButton()
                }
            }
        }
    }
}
