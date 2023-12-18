package com.kaspersky.kaspresso.kautomatorsample.kaspresso.sanity.flaky

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.flakysafety.FlakySafeObjectBehaviorInterceptor
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.kautomatorsample.flaky.FlakyActivity
import com.kaspersky.kaspresso.kautomatorsample.R
import com.kaspersky.kaspresso.kautomatorsample.screen.FlakyScreen
import com.kaspersky.kaspresso.logger.UiTestLoggerImpl
import com.kaspersky.kaspresso.params.FlakySafetyParams
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import io.github.kakaocup.kakao.common.utilities.getResourceString
import org.junit.Rule
import org.junit.Test

class FlakyObjectEnabledSanityTest : TestCase(
    kaspressoBuilder = Kaspresso.Builder.simple {
        objectBehaviorInterceptors = mutableListOf(
            FlakySafeObjectBehaviorInterceptor(
                FlakySafetyParams.default(), UiTestLoggerImpl(Kaspresso.DEFAULT_TEST_LOGGER_TAG)
            )
        )
    }
) {

    @get:Rule
    val activityRule = activityScenarioRule<FlakyActivity>()

    @Test
    fun test() = run {
        step("Click actions") {
            FlakyScreen {
                btn1 {
                    hasText(getResourceString(R.string.menu_item_2))
                    click()
                }
            }
        }
    }
}
