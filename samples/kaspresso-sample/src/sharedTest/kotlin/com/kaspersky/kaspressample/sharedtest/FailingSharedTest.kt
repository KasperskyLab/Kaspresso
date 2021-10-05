package com.kaspersky.kaspressample.sharedtest

import android.Manifest
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import androidx.test.rule.GrantPermissionRule
import com.kaspersky.kaspressample.device.DeviceSampleActivity
import com.kaspersky.kaspresso.instrumental.exception.NotSupportedInstrumentalTestException
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FailingSharedTest : TestCase() {

    @get:Rule
    val runtimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    @get:Rule
    val activityTestRule = ActivityTestRule(DeviceSampleActivity::class.java, false, true)

    @Test
    fun instrumentalTest() = exploitSampleTest()

    @Test(expected = NotSupportedInstrumentalTestException::class)
    fun unitTest() = exploitSampleTest()

    private fun exploitSampleTest() =
        run {
            step("Wait for Launcher") {
                device.exploit.pressHome()
            }
        }
}
