package com.kaspersky.kaspressample.scrollresolver_tests.mutiplescrollable_kautomator_tests

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspressample.MainActivity
import com.kaspersky.kaspressample.screen.MainScreen
import com.kaspersky.kaspressample.screen.UiScrollViewWithPaddingScreen
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test

class MultipleScrollablesPassingTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun click_last_button_in_HorizontalScrollView_and_then_first_one_with_autoscroll() =
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
                    hbutton7 {
                        click()
                    }
                }
            }

            step("Click hbutton_1 in HorizontalScrollView, first item from last one (leftwards)") {
                UiScrollViewWithPaddingScreen {
                    hbutton1 {
                        click()
                    }
                }
            }
        }

    /**
     * This tests proves that it is faster to make the Scrollable scroll to the view we want to e.g. click,
     * specially if nested
     */
    @Test
    fun click_last_button_in_HorizontalScrollView_and_then_first_one_without_autoscroll() =
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
                    scrollView.scrollToView(hScrollView)
                    hScrollView.scrollToView(hbutton7)
                    hbutton7 {
                        click()
                    }
                }
            }

            step("Click hbutton_1 in HorizontalScrollView, first item from last one (leftwards)") {
                UiScrollViewWithPaddingScreen {
                    scrollView.scrollToView(hScrollView)
                    hScrollView.scrollToView(hbutton1)
                    hbutton1 {
                        click()
                    }
                }
            }
        }

    @Test
    fun click_last_button_in_ScrollView_and_then_first_one_with_autoscroll() =
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
                    button1 {
                        click()
                    }
                }
            }

            step("Click button_1 in ScrollView, first item from last one (upwards)") {
                UiScrollViewWithPaddingScreen {
                    button1 {
                        click()
                    }
                }
            }
        }

}
