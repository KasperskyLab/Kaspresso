package com.kaspersky.kaspressample.scrollresolver_tests.mutiplescrollable_kautomator_tests

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspressample.MainActivity
import com.kaspersky.kaspressample.screen.MainScreen
import com.kaspersky.kaspressample.screen.UiScrollViewWithPaddingScreen
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test

/**
 * Kautomator autoscroll works fine when the screen contains a unique scrollable view.
 * That might not be the case for screens with multiple scrollable views.
 * That's because of the way UiAutomator performs scrolling under the hood.
 *
 * To overcome such issues, you should avoid relying on autoscroll and perform the necessary
 * scrolling before executing the desired action, as seen in the examples below.
 */
class MultipleScrollablesPassingTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun click_last_button_in_HorizontalScrollView_and_then_first_one() =
        run {
            step("Open Scroll Resolver Screen") {
                MainScreen {
                    scrollResolverButton {
                        isVisible()
                        click()
                    }
                }
            }

            step("Click hbutton_7 in HorizontalScrollView, last item") {
                UiScrollViewWithPaddingScreen {
                    hScrollView.scrollToView(hbutton7)
                    hbutton7 {
                        click()
                    }
                }
            }
            step("Click hbutton_1 in HorizontalScrollView, first item from last one (leftwards)") {
                UiScrollViewWithPaddingScreen {
                    hScrollView.scrollToView(hbutton1)
                    hbutton1 {
                        click()
                    }
                }
            }
        }

    @Test
    fun click_last_button_in_ScrollView_and_then_first_one() =
        run {
            step("Open Auto Scroll Fallback Screen") {
                MainScreen {
                    scrollResolverButton {
                        isVisible()
                        click()
                    }
                }
            }

            step("Click button_20 in ScrollView, last item") {
                UiScrollViewWithPaddingScreen {
                    scrollView.scrollToView(button20)
                    button20 {
                        click()
                    }
                }
            }

            step("Click button_1 in ScrollView, first item from last one (upwards)") {
                UiScrollViewWithPaddingScreen {
                    scrollView.scrollToView(button1)
                    button1 {
                        click()
                    }
                }
            }
        }
}
