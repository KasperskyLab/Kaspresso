package com.kaspersky.kaspresso.kautomatorsample.test

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.kautomatorsample.AmbiguousActivity
import com.kaspersky.kaspresso.kautomatorsample.screen.AmbiguousScreen
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test

class AmbiguousScreenTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<AmbiguousActivity>()

    @Test
    fun ambiguousTest() {
        before {
        }.after {
        }.run {
            step("Ambiguous screen is shown") {
                AmbiguousScreen {
                    anyPossibleAmbiguousButtonByIdAndText {
                        isDisplayed()
                    }
                }
            }
            step("Click on ambiguous button") {
                AmbiguousScreen {
                    anyPossibleAmbiguousButtonByIdAndText {
                        click()
                    }
                }
            }
        }
    }
}
