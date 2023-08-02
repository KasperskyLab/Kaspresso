package com.kaspersky.kaspresso.sanity.interceptors.autoscroll

import androidx.test.espresso.PerformException
import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspressample.MainActivity
import com.kaspersky.kaspressample.screen.MainScreen
import com.kaspersky.kaspressample.screen.ScrollViewWithPaddingScreen
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class AutoScrollViewDisabledSanityTest : TestCase(
    kaspressoBuilder = Kaspresso.Builder.simple {
        viewBehaviorInterceptors = mutableListOf()
    }
) {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun click_last_button_in_ScrollView_and_then_first_one() =
        run {
            step("Open scrollView with padding screen") {
                MainScreen {
                    autoScrollScrollViewWithPaddingButton {
                        isVisible()
                        click()
                    }
                }
            }

            step("Click button20 in ScrollView, last item") {
                ScrollViewWithPaddingScreen {
                    Assert.assertThrows(null, PerformException::class.java) {
                        button20 {
                            click()
                        }
                    }
                    button20 {
                        isNotDisplayed()
                    }
                }
            }

            step("Click button1 in ScrollView, first item") {
                ScrollViewWithPaddingScreen {
                    button1 {
                        click()
                    }
                }
            }
        }
}
