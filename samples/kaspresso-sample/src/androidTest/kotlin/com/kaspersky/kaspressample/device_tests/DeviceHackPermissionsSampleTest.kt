package com.kaspersky.kaspressample.device_tests

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.rule.GrantPermissionRule
import com.kaspersky.kaspressample.device.DeviceSampleActivity
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.testcases.core.testcontext.BaseTestContext
import org.junit.Assert.assertTrue
import org.junit.Assume.assumeTrue
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
 * Attention please!
 * Before you start this test you must remove Kaspresso sample application at the device or
 * execute adb shell commands like "pm clear com.kaspersky.kaspressample"
 * to reset permissions granted before.
 * It's needed measure because of AndroidJUnit Runner specific that doesn't reset state of app between tests.
 */
class DeviceHackPermissionsSampleTest : TestCase() {

    @get:Rule
    val runtimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    @get:Rule
    val activityRule = activityScenarioRule<DeviceSampleActivity>()

    @Test
    fun permissionsSampleTest() {
        // Run only on devices with Android M or later and skip the test otherwise.
        assumeTrue(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)

        run {
            step("Request permissions") {
                device.hackPermissions.grant(device.targetContext.packageName, Manifest.permission.READ_CALL_LOG)
                // Contacts permission should be granted now
                assertTrue(hasCallLogPermission())
            }
        }
    }

    private fun BaseTestContext.hasCallLogPermission(): Boolean =
        device.targetContext.checkSelfPermission(Manifest.permission.READ_CALL_LOG) ==
                PackageManager.PERMISSION_GRANTED
}
