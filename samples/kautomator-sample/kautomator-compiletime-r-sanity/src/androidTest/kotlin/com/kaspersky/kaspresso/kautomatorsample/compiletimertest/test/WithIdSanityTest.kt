package com.kaspersky.kaspresso.kautomatorsample.compiletimertest.test

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.kautomatorsample.compiletimertest.MainActivity
import com.kaspersky.kaspresso.kautomatorsample.compiletimertest.screen.MainScreen
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test

class WithIdSanityTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun withIdResolvesResourcesCorrectly() = run {
        step("Locate views by integer R.id references") {
            MainScreen {
                editText { isDisplayed() }
                button { isDisplayed() }
                textView { isDisplayed() }
            }
        }
    }
}
