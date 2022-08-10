package com.kaspersky.kaspresso.kautomatorsample.test.components

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.kautomatorsample.ComponentsActivity
import com.kaspersky.kaspresso.kautomatorsample.screen.ComponentsScreen
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test

class ChipGroupTest : TestCase() {

    companion object {
        private const val CHIP_ID = "chip3"
        private const val CHIP_TEXT = "Chip 1"
        private const val CHIP_INDEX = 1
    }

    @get:Rule
    val activityRule = activityScenarioRule<ComponentsActivity>()

    @Test
    fun test() = run {
        step("Select chip with id") {
            ComponentsScreen {
                chipGroup {
                    isNotChipWithIdSelected(CHIP_ID)
                    selectChipWithId(CHIP_ID)
                    isChipWithIdSelected(CHIP_ID)
                }
            }
        }

        step("Select chip with text") {
            ComponentsScreen {
                chipGroup {
                    isNotChipWithTextSelected(CHIP_TEXT)
                    selectChipWithText(CHIP_TEXT)
                    isChipWithTextSelected(CHIP_TEXT)
                }
            }
        }

        step("Select chip with index") {
            ComponentsScreen {
                chipGroup {
                    isNotChipWithIndexSelected(CHIP_INDEX)
                    selectChipWithIndex(CHIP_INDEX)
                    isChipWithIndexSelected(CHIP_INDEX)
                }
            }
        }
    }
}
