package com.kaspersky.kaspresso.sanity.interceptors.autoscroll

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspressample.MainActivity
import com.kaspersky.kaspressample.screen.MainScreen
import com.kaspersky.kaspressample.screen.ScrollViewWithPaddingScreen
import com.kaspersky.kaspresso.interceptors.behavior.impl.autoscroll.AutoScrollViewBehaviorInterceptor
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.logger.UiTestLoggerImpl
import com.kaspersky.kaspresso.params.AutoScrollParams
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test

class AutoScrollViewEnabledSanityTest : TestCase(
    kaspressoBuilder = Kaspresso.Builder.simple {
        viewBehaviorInterceptors = mutableListOf(
            AutoScrollViewBehaviorInterceptor(
                AutoScrollParams.default(),
                UiTestLoggerImpl(Kaspresso.DEFAULT_TEST_LOGGER_TAG)
            )
        )
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
                    button20 {
                        click()
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
