package com.kaspersky.kaspressample.device_tests

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.kaspressample.MainActivity
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.hamcrest.CoreMatchers.instanceOf
import org.junit.Assert.assertThat
import org.junit.Rule
import org.junit.Test

class DeviceActivitiesSampleTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun activitiesSampleTest() {
        run {

            step("Check if MainActivity is currently resumed") {
                device.activities.isCurrent(MainActivity::class.java)
            }

            step("Check if currently resumed activity is an instance of MainActivity") {
                assertThat(device.activities.getResumed(), instanceOf(MainActivity::class.java))
            }
        }
    }
}
