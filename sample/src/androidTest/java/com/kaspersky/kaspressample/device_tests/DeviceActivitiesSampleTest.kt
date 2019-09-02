package com.kaspersky.kaspressample.device_tests

import android.Manifest
import android.support.test.rule.ActivityTestRule
import android.support.test.rule.GrantPermissionRule
import android.support.test.runner.AndroidJUnit4
import com.kaspersky.kaspressample.MainActivity
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.hamcrest.CoreMatchers.instanceOf
import org.junit.Assert.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DeviceActivitiesSampleTest : TestCase() {

    @get:Rule
    val activityTestRule = ActivityTestRule(MainActivity::class.java, true, true)

    @get:Rule
    val runtimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    @Test
    fun activitiesSampleTest() {
        before {
        }.after {
        }.run {

            step("Check if MainActivity is currently resumed") {
                device.activities.isCurrent(MainActivity::class.java)
            }

            step("Check if currently resumed activity is an instance of MainActivity") {
                assertThat(device.activities.getResumed(), instanceOf(MainActivity::class.java))
            }
        }
    }
}