package com.kaspersky.kaspressample.tests

import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kaspersky.components.kautomator.common.KautomatorInUnitTestException
import com.kaspersky.kaspressample.device.DeviceSampleActivity
import com.kaspersky.kaspressample.screen.UiMainScreen
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FailingKautomatorRobolectricTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<DeviceSampleActivity>()

    @Test
    fun unitTest() {
        Assert.assertThrows(KautomatorInUnitTestException::class.java) {
            kautomatorSampleTest()
        }
    }

    private fun kautomatorSampleTest() = run {
        step("Open Continuously Screen") {
            UiMainScreen {
                continuouslyButton {
                    click()
                }
            }
        }
    }
}
