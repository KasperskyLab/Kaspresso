package com.kaspersky.kaspressample.device_tests

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.rule.GrantPermissionRule
import com.kaspersky.kaspressample.device.DeviceSampleActivity
import com.kaspersky.kaspressample.screen.DeviceSampleScreen
import com.kaspersky.kaspressample.utils.SafeAssert.assertTrueSafely
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.testcases.core.testcontext.BaseTestContext
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

class DevicePermissionsSampleTest : TestCase() {

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

        before {
            adbServer.performShell("pm revoke ${device.targetContext.packageName} ${Manifest.permission.READ_CALL_LOG}")
        }.after {
        }.run {

            step("Request permissions") {
                DeviceSampleScreen {
                    // Button click requests permission using default Android dialog
                    requestPermissionButton {
                        click()
                    }
                }

                device.permissions.apply {
                    assertTrueSafely { isDialogVisible() }
                    allowViaDialog()
                }

                // Contacts permission should be granted now
                assertTrueSafely { hasCallLogPermission() }
            }
        }
    }

    private fun BaseTestContext.hasCallLogPermission(): Boolean =
        device.targetContext.checkSelfPermission(Manifest.permission.READ_CALL_LOG) ==
                PackageManager.PERMISSION_GRANTED
}
