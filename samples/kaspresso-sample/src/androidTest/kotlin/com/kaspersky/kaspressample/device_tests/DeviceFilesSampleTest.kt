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

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

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
