package com.kaspersky.kaspresso.sanity.interceptors.flaky

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspressample.MainActivity
import com.kaspersky.kaspressample.R
import com.kaspersky.kaspressample.screen.CommonFlakyScreen
import com.kaspersky.kaspressample.screen.MainScreen
import com.kaspersky.kaspresso.interceptors.behavior.impl.flakysafety.FlakySafeViewBehaviorInterceptor
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.logger.UiTestLoggerImpl
import com.kaspersky.kaspresso.params.FlakySafetyParams
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test

class FlakyViewEnabledSanityTest : TestCase(
    kaspressoBuilder = Kaspresso.Builder.simple {
        viewBehaviorInterceptors = mutableListOf(
            FlakySafeViewBehaviorInterceptor(
                FlakySafetyParams.default(),
                UiTestLoggerImpl(Kaspresso.DEFAULT_TEST_LOGGER_TAG)
            )
        )
    }
) {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun test() = run {
        step("Open Flaky screen") {
            MainScreen {
                flakyButton {
                    click()
                }
            }
        }

        step("Check ScrollView screen is displayed") {
            CommonFlakyScreen {
                scrollView {
                    isDisplayed()
                }
            }
        }

        step("Check btn5's text") {
            CommonFlakyScreen {
                btn5 {
                    // automate flaky safety handling is in action
                    // the text is changing during 3 seconds
                    // the default value of flaky safety timeout = 10 seconds
                    hasText(R.string.common_flaky_final_button)
                }
            }
        }
    }
}
