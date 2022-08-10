package com.kaspersky.kaspresso.kautomatorsample.test.components

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.kautomatorsample.ComponentsActivity
import com.kaspersky.kaspresso.kautomatorsample.screen.ComponentsScreen
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test

class BottomNavigationViewTest : TestCase() {

    companion object {
        private const val ITEM_0_TEXT = "Menu Item 1"
        private const val ITEM_1_TEXT = "Menu Item 2"

        private const val ITEM_0_ID = "menu_item_1"
        private const val ITEM_1_ID = "menu_item_2"
    }

    @get:Rule
    val activityRule = activityScenarioRule<ComponentsActivity>()

    @Test
    fun test() = run {

        step("Select item by id") {
            ComponentsScreen {
                bottomNav {
                    setSelectedItemWithId(ITEM_1_ID)
                    hasSelectedItemWithId(ITEM_1_ID)
                    hasNotSelectedItemWithId(ITEM_0_ID)
                }
            }
        }

        step("Select item by index") {
            ComponentsScreen {
                bottomNav {
                    setSelectedItemWithIndex(0)
                    hasSelectedItemWithIndex(0)
                    hasNotSelectedItemWithIndex(1)
                }
            }
        }

        step("Select item by label") {
            ComponentsScreen {
                bottomNav {
                    setSelectedItemWithTitle(ITEM_1_TEXT)
                    hasSelectedItemWithTitle(ITEM_1_TEXT)
                    hasNotSelectedItemWithTitle(ITEM_0_TEXT)
                }
            }
        }
    }
}
