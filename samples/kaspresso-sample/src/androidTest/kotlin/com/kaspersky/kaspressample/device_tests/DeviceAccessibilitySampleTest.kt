package com.kaspersky.kaspressample.device_tests

import android.Manifest
import android.os.Build
import android.provider.Settings
import android.provider.Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.rule.GrantPermissionRule
import com.kaspersky.kaspressample.device.DeviceSampleAccessibilityService
import com.kaspersky.kaspressample.device.DeviceSampleActivity
import com.kaspersky.kaspressample.utils.SafeAssert.assertFalseSafely
import com.kaspersky.kaspressample.utils.SafeAssert.assertTrueSafely
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.testcases.core.testcontext.BaseTestContext
import org.junit.Assume.assumeTrue
import org.junit.Ignore
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
 * Sometimes, this test is unstable.
 * The possible reason is a slow (not immediate) update of ContentResolver by which the test is checking
 */
@Ignore("Unstable test. See test class docs")
class DeviceAccessibilitySampleTest : TestCase() {

    companion object {
        private val SERVICE_CLASS_NAME = DeviceSampleAccessibilityService::class.java.canonicalName!!
    }

    @get:Rule
    val runtimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    @get:Rule
    val activityRule = activityScenarioRule<DeviceSampleActivity>()

    @Test
    fun accessibilitySampleTest() {
        assumeTrue(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)

        before {
            device.accessibility.disable()
        }.after {
            device.accessibility.disable()
        }.run {

            step("Enable accessibility service") {
                device.accessibility.enable(
                    device.targetContext.packageName,
                    SERVICE_CLASS_NAME
                )
                assertTrueSafely { isAccessibilityServiceEnabled() }
            }

            step("Disable accessibility service") {
                device.accessibility.disable()
                assertFalseSafely { isAccessibilityServiceEnabled() }
            }
        }
    }

    private fun BaseTestContext.isAccessibilityServiceEnabled(): Boolean {
        return Settings.Secure.getString(
            device.targetContext.contentResolver,
            ENABLED_ACCESSIBILITY_SERVICES
        )?.contains(SERVICE_CLASS_NAME) ?: false
    }
}
