package com.kaspersky.kaspresso.kautomatorsample.kaspresso.sanity

import android.os.Build
import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.kautomatorsample.ComponentsActivity
import com.kaspersky.kaspresso.kautomatorsample.screen.ComponentsScreen
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Assume
import org.junit.Rule
import org.junit.Test

class HintMatchersTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<ComponentsActivity>()

    @Test
    fun test() = run {
        Assume.assumeTrue("Hint matchers are available on API 26+", Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)

        ComponentsScreen {
            editTextByHintResId { isDisplayed() }
            editTextByHintText { isDisplayed() }
            editTextByHintPattern { isDisplayed() }
        }
    }
}
