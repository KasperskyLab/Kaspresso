package com.kaspersky.kaspressample.autoscrollfallback_tests

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspressample.MainActivity
import com.kaspersky.kaspressample.screen.MainScreen
import com.kaspersky.kaspressample.screen.ScrollViewWithPaddingScreen
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test

class ScrollViewWithPaddingTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun click_last_button_in_ScrollView_and_then_first_one() =
        run {
            step("Open Auto Scroll Fallback Screen") {
                MainScreen {
                    autoScrollFallbackButton {
                        isVisible()
                        click()
                    }
                }
            }

            step("Click button_20 in ScrollView, last item") {
                ScrollViewWithPaddingScreen {
                    button20 {
                        click()
                    }
                }
            }

            step("Click button_1 in ScrollView, first item") {
                ScrollViewWithPaddingScreen {
                    button1 {
                        click()
                    }
                }
            }
        }

    @Test
    fun click_last_button_in_ScrollView_and_then_last_one_in_HorizontalScrollView() =
        run {
            step("Open Auto Scroll Fallback Screen") {
                MainScreen {
                    autoScrollFallbackButton {
                        isVisible()
                        click()
                    }
                }
            }

            step("Click button_20 in ScrollView, last item") {
                ScrollViewWithPaddingScreen {
                    button20 {
                        click()
                    }
                }
            }

            step("Click hbutton_7 in HorizontalScrollView, first item") {
                ScrollViewWithPaddingScreen {
                    hbutton7 {
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
                    autoScrollFallbackButton {
                        isVisible()
                        click()
                    }
                }
            }

            step("Click hbutton_7 in HorizontalScrollView, last item") {
                ScrollViewWithPaddingScreen {
                    hbutton7 {
                        click()
                    }
                }
            }
            step("Click hbutton_1 in HorizontalScrollView, first item") {
                ScrollViewWithPaddingScreen {
                    hbutton1 {
                        click()
                    }
                }
            }
        }

    @Test
    fun click_last_button_in_3LevelNestedHorizontalScrollView_and_then_last_one_in_1LevelNestedHorizontalScrollView() =
        run {
            step("Open Auto Scroll Fallback Screen") {
                MainScreen {
                    autoScrollFallbackButton {
                        isVisible()
                        click()
                    }
                }
            }

            step("Click nnhbutton_5 in 3LevelNestedScrollView, middle item") {
                ScrollViewWithPaddingScreen {
                    nnhbutton5 {
                        click()
                    }
                }
            }

            step("Click button_7 in HorizontalScrollView, last item") {
                ScrollViewWithPaddingScreen {
                    hbutton7 {
                        click()
                    }
                }
            }
        }
}
