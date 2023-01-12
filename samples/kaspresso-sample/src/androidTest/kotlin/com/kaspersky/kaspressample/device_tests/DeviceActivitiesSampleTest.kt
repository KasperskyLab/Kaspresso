package com.kaspersky.kaspressample.device_tests

import android.Manifest
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.rule.GrantPermissionRule
import com.kaspersky.kaspressample.MainActivity
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.hamcrest.CoreMatchers.instanceOf
import org.junit.Assert.assertThat
import org.junit.Rule
import org.junit.Test

class DeviceActivitiesSampleTest : TestCase() {

    @get:Rule
    val runtimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

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
