package com.kaspersky.kaspressample.scrollresolver_tests.mutiplescrollable_kautomator_tests

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.components.kautomator.intercept.exception.UnfoundedUiObjectException
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
 * This showcases the problem
 */
class MultipleScrollablesFailingTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test(expected = UnfoundedUiObjectException::class)
    fun click_last_button_in_HorizontalScrollView_without_explicit_scrolling_fails() =
        run {
            step("Open Auto Scroll Fallback Screen") {
                MainScreen {
                    scrollResolverButton {
                        isVisible()
                        click()
                    }
                }
            }

            step("Click hbutton_7 in HorizontalScrollView, last item") {
                UiScrollViewWithPaddingScreen {
                    hbutton7 {
                        click()
                    }
                }
            }
        }

    @Test(expected = UnfoundedUiObjectException::class)
    fun click_last_button_in_ScrollView_without_explicit_scrolling_fails() =
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
                    button20 {
                        click()
                    }
                }
            }
        }
}
