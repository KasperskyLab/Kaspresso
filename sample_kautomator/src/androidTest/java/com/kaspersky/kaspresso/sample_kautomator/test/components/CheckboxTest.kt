package com.kaspersky.kaspresso.sample_kautomator.test.components

import androidx.test.rule.ActivityTestRule
import com.kaspersky.kaspresso.sample_kautomator.ComponentsActivity
import com.kaspersky.kaspresso.sample_kautomator.screen.ComponentsScreen
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