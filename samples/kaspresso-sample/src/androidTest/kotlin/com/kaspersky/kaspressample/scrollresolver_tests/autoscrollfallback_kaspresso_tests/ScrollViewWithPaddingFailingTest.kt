package com.kaspersky.kaspressample.scrollresolver_tests.autoscrollfallback_kaspresso_tests

import androidx.test.espresso.PerformException
import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspressample.MainActivity
import com.kaspersky.kaspressample.screen.MainScreen
import com.kaspersky.kaspressample.screen.KScrollViewWithPaddingScreen
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test

class ScrollViewWithPaddingFailingTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test(expected = PerformException::class)
    fun click_button_in_the_middle() =
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

    @Test(expected = PerformException::class)
    fun click_last_button() =
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
        }

    @Test(expected = PerformException::class)
    fun click_button_in_the_end_of_HorizontalScrollView() =
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
        }
}
