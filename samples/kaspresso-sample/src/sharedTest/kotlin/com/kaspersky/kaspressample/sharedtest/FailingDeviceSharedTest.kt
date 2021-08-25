package com.kaspersky.kaspressample.sharedtest

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import androidx.test.rule.GrantPermissionRule
import com.kaspersky.kaspressample.device.DeviceSampleActivity
import com.kaspersky.kaspresso.failure.exceptions.ActionNotSupportedInSharedTestException
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.testcases.core.testcontext.BaseTestContext
import org.junit.Assert
import org.junit.Assume
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FailingDeviceSharedTest : TestCase(Kaspresso.Builder.simple(sharedTest = true)) {

    @get:Rule
    val runtimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    @get:Rule
    val activityTestRule = ActivityTestRule(DeviceSampleActivity::class.java, false, true)

    @Test(expected = ActionNotSupportedInSharedTestException::class)
    fun permissionsSampleTest() {
        // Run only on devices with Android M or later and skip the test otherwise.
        Assume.assumeTrue(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)

        run {
            step("Request permissions") {
                device.hackPermissions.grant(device.targetContext.packageName, Manifest.permission.READ_CALL_LOG)
                // Contacts permission should be granted now
                Assert.assertTrue(hasCallLogPermission())
            }
        }
    }

    private fun BaseTestContext.hasCallLogPermission(): Boolean =
        device.targetContext.checkSelfPermission(Manifest.permission.READ_CALL_LOG) ==
                PackageManager.PERMISSION_GRANTED
}
