package com.kaspersky.kaspresso.kautomatorsample.kaspresso.sanity.autoscroll

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.components.kautomator.intercept.exception.UnfoundedUiObjectException
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.kautomatorsample.autoscroll.AutoScrollActivity
import com.kaspersky.kaspresso.kautomatorsample.screen.AutoScrollScreen
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class AutoScrollObjectDisabledSanityTest : TestCase(
    kaspressoBuilder = Kaspresso.Builder.simple {
        objectBehaviorInterceptors = mutableListOf()
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
                    Assert.assertThrows(UnfoundedUiObjectException::class.java) {
                        click()
                    }
                }
            }
        }
    }
}
