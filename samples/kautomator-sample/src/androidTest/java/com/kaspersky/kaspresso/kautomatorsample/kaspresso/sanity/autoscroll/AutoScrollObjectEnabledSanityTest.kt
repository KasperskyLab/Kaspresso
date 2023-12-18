package com.kaspersky.kaspresso.kautomatorsample.kaspresso.sanity.autoscroll

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspresso.interceptors.behaviorkautomator.impl.autoscroll.AutoScrollObjectBehaviorInterceptor
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.kautomatorsample.autoscroll.AutoScrollActivity
import com.kaspersky.kaspresso.kautomatorsample.screen.AutoScrollScreen
import com.kaspersky.kaspresso.logger.UiTestLoggerImpl
import com.kaspersky.kaspresso.params.AutoScrollParams
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test

class AutoScrollObjectEnabledSanityTest : TestCase(
    kaspressoBuilder = Kaspresso.Builder.simple {
        objectBehaviorInterceptors = mutableListOf(AutoScrollObjectBehaviorInterceptor(UiTestLoggerImpl(Kaspresso.DEFAULT_TEST_LOGGER_TAG), AutoScrollParams.default()))
    }
) {

    @get:Rule
    val activityRule = activityScenarioRule<AutoScrollActivity>()

    @Test
    fun test() = run {
        step("Click actions") {
            AutoScrollScreen {
                button1 {
                    click()
                }
                button20 {
                    click()
                }
            }
        }
    }
}
