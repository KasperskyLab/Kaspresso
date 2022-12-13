package com.kaspersky.kaspresso.kautomatorsample.kaspresso.sanity.flaky

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.kautomatorsample.flaky.FlakyActivity
import com.kaspersky.kaspresso.kautomatorsample.R
import com.kaspersky.kaspresso.kautomatorsample.screen.FlakyScreen
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import io.github.kakaocup.kakao.common.utilities.getResourceString
import org.junit.Rule
import org.junit.Test

class FlakyObjectEnabledSanityTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<FlakyActivity>()

    @Test
    fun test() = run {
        step("Click actions") {
            FlakyScreen {
                btn1 {
                    hasText(getResourceString(R.string.menu_item_2))
                    click()
                }
            }
        }
    }
}
