package com.kaspersky.kaspresso.kautomatorsample.test.components

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.kautomatorsample.scroll.NestedScrollViewsActivity
import com.kaspersky.kaspresso.kautomatorsample.screen.NestedScrollViewsScreen
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test

/**
 * This sample shows how to work with nested vertical and horizontal ScrollViews
 */
class NestedScrollViewsTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<NestedScrollViewsActivity>()

    @Test
    fun click_last_button_in_ScrollView_and_then_first_one() =
        run {
            step("Click button20 in ScrollView, last item") {
                NestedScrollViewsScreen {
                    button20.click()
                }
            }

            step("Click button1 in ScrollView, first item") {
                NestedScrollViewsScreen {
                    button1.click()
                }
            }
        }

    @Test
    fun click_last_button_in_ScrollView_and_then_last_one_in_HorizontalScrollView() =
        run {
            step("Click button20 in ScrollView, last item") {
                NestedScrollViewsScreen {
                    button20.click()
                }
            }

            step("Click hbutton7 in HorizontalScrollView, first item") {
                NestedScrollViewsScreen {
                    hscrollView.scrollToView(hbutton7)
                    hbutton7.click()
                }
            }
        }

    @Test
    fun click_last_button_in_HorizontalScrollView_and_then_first_one() =
        run {
            step("Click hbutton7 in HorizontalScrollView, last item") {
                NestedScrollViewsScreen {
                    hscrollView.scrollToView(hbutton7)
                    hbutton7.click()
                }
            }
            step("Click hbutton1 in HorizontalScrollView, first item") {
                NestedScrollViewsScreen {
                    hscrollView.scrollToView(hbutton1)
                    hbutton1.click()
                }
            }
        }

    @Test
    fun click_last_button_in_3LevelNestedHorizontalScrollView_and_then_last_one_in_1LevelNestedHorizontalScrollView() =
        run {
            step("Click nnHbutton5 in 3LevelNestedScrollView, middle item") {
                NestedScrollViewsScreen {
                    nnHscrollView.scrollToView(nnHbutton5)
                    nnHbutton5.click()
                }
            }

            step("Click button7 in HorizontalScrollView, last item") {
                NestedScrollViewsScreen {
                    hscrollView.scrollToView(hbutton7)
                    hbutton7.click()
                }
            }
        }
}
