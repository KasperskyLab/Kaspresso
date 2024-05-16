package com.kaspersky.kaspresso.kautomatorsample.test

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.kautomatorsample.ParentSearchActivity
import com.kaspersky.kaspresso.kautomatorsample.screen.ParentSearchScreen
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test
import com.kaspersky.kaspresso.kautomatorsample.R

class ParentSearchTest : TestCase() {
    @get:Rule
    val activityRule = activityScenarioRule<ParentSearchActivity>()

    @Test
    fun test() = run {
        ParentSearchScreen {
            textByParentId {
                val text1 = device.targetContext.resources.getString(R.string.parent_search_value1)
                hasText(text1)
            }
            textByParentChild {
                val text2 = device.targetContext.resources.getString(R.string.parent_search_value2)
                hasText(text2)
            }
        }
    }
}
