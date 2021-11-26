package com.kaspersky.kaspresso.kautomatorsample.test.components

import androidx.test.rule.ActivityTestRule
import com.kaspersky.kaspresso.kautomatorsample.ComponentsActivity
import com.kaspersky.kaspresso.kautomatorsample.screen.ComponentsScreen
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test

class CheckboxTest : TestCase() {

    @get:Rule
    val rule = ActivityTestRule(ComponentsActivity::class.java, true, true)

    @Test
    fun test() {
        run {
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
}
