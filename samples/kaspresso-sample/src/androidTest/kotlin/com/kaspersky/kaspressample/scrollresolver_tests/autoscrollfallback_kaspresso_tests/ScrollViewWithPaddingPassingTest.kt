package com.kaspersky.kaspressample.scrollresolver_tests.autoscrollfallback_kaspresso_tests

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspressample.MainActivity
import com.kaspersky.kaspressample.screen.MainScreen
import com.kaspersky.kaspressample.screen.KScrollViewWithPaddingScreen
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test

class ScrollViewWithPaddingPassingTest : TestCase(
    kaspressoBuilder = Kaspresso.Builder.simple().withAutoScrollFallback()
) {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun click_button_in_the_middle_of_ScrollView() =
        run {
            step("Open Auto Scroll Fallback Screen") {
                MainScreen {
                    scrollResolverButton {
                        isVisible()
                        click()
                    }
                }
            }

            step("Click button_18 in ScrollView, middle item") {
                KScrollViewWithPaddingScreen {
                    button18 {
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
                KScrollViewWithPaddingScreen {
                    button20 {
                        click()
                    }
                }
            }

            step("Click button_1 in ScrollView, first item from last one (upwards)") {
                KScrollViewWithPaddingScreen {
                    button1 {
                        click()
                    }
                }
            }
        }

    @Test
    fun click_last_button_in_HorizontalScrollView_and_then_first_one() =
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
                KScrollViewWithPaddingScreen {
                    hbutton7 {
                        click()
                    }
                }
            }
            step("Click hbutton_1 in HorizontalScrollView, first item from last one (leftwards)") {
                KScrollViewWithPaddingScreen {
                    hbutton1 {
                        click()
                    }
                }
            }
        }
}
