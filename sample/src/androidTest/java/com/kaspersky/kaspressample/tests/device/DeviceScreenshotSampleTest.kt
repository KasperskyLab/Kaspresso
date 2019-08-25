package com.kaspersky.kaspressample.tests.device

import android.Manifest
import android.content.Context
import android.os.Build
import android.os.Environment
import android.support.test.rule.ActivityTestRule
import android.support.test.rule.GrantPermissionRule
import android.support.test.runner.AndroidJUnit4
import com.kaspersky.kaspressample.devicesample.DeviceSampleActivity
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.testcases.core.testcontext.BaseTestContext
import java.io.File
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DeviceScreenshotSampleTest : TestCase() {

    companion object {
        private const val SCREENSHOT_TAG = "screenshot"
    }

    @get:Rule
    val activityRule = ActivityTestRule(DeviceSampleActivity::class.java, false, true)

    @get:Rule
    val permissionsRule: GrantPermissionRule = GrantPermissionRule.grant(Manifest.permission.WRITE_EXTERNAL_STORAGE)

    @Test
    fun screenshotSampleTest() {
        before {
            deleteDir(screenshotsDir())
        }.after {
        }.run {

            step("Take a screenshot") {
                assertFalse(screenshotExists())
                device.screenshots.take(SCREENSHOT_TAG)
                assertTrue(screenshotExists())
            }
        }
    }

    private fun BaseTestContext.screenshotExists(): Boolean {
        return containsFileWithName(screenshotsDir(), SCREENSHOT_TAG)
    }

    private fun BaseTestContext.screenshotsDir(): File? {
        val dir = File("screenshots")
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Use external storage.
            Environment.getExternalStorageDirectory().resolve(dir)
        } else {
            // Use internal storage.
            device.targetContext.getDir(dir.canonicalPath, Context.MODE_WORLD_READABLE)
        }
    }

    private fun containsFileWithName(dir: File?, name: String): Boolean {
        if (dir == null || !dir.exists()) return false

        dir.listFiles().forEach {
            if (it.isDirectory && containsFileWithName(it, name)) return true
            else if (it.name.contains(SCREENSHOT_TAG)) return true
        }
        return false
    }

    private fun deleteDir(dir: File?) {
        if (dir == null || !dir.exists()) return

        dir.listFiles().forEach {
            if (it.isDirectory) deleteDir(it)
            if (it.isFile) it.delete()
        }
        dir.delete()
    }
}