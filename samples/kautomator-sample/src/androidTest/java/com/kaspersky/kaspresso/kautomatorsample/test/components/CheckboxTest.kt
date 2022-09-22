package com.kaspersky.kaspresso.kautomatorsample.test.components

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.kautomatorsample.ComponentsActivity
import com.kaspersky.kaspresso.kautomatorsample.screen.ComponentsScreen
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test

class CheckboxTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<ComponentsActivity>()

    @Test
    fun test() = run {
        step("Set checked") {
            ComponentsScreen {
                checkbox {
                    setChecked(true)
                    isChecked()
                }
            }
        }

        step("Set not checked") {
            ComponentsScreen {
                checkbox {
                    setChecked(false)
                    isNotChecked()
                }
            }
        }
    }
}
