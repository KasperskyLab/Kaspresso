package com.kaspersky.kaspressample.device_tests

import android.Manifest
import android.os.Environment
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.rule.GrantPermissionRule
import com.kaspersky.kaspressample.MainActivity
import com.kaspersky.kaspresso.device.files.Files
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import java.io.File
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

/**
 * Pushes and then removes a file file placed at /artifacts directory.
 * [Files.push] uses the [FILE_PATH] relative path to push the file.
 * So, you should run the server with command `cd /absolute/path/to/project/directory & java -jar artifacts/adbserver-desktop.jar`
 */
class DeviceFilesSampleTest : TestCase() {

    companion object {
        private const val FILE_NAME = "hello_world.apk"
        private const val FILE_RELATIVE_PATH = "artifacts/$FILE_NAME"
    }

    @get:Rule
    val runtimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun filesSampleTest() {
        run {

            step("Push $FILE_RELATIVE_PATH to device") {
                device.files.push(FILE_RELATIVE_PATH, Environment.getExternalStorageDirectory().absolutePath)
                val file = File(Environment.getExternalStorageDirectory(), FILE_NAME)
                assertTrue(file.exists())
            }

            step("Delete pushed file") {
                val file = File(Environment.getExternalStorageDirectory(), FILE_NAME)
                device.files.remove(file.absolutePath)
                assertFalse(file.exists())
            }
        }
    }
}
