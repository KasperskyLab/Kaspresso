package com.kaspersky.kaspressample.adb_server_tests

import android.Manifest
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.filters.SdkSuppress
import androidx.test.rule.GrantPermissionRule
import com.kaspersky.kaspressample.MainActivity
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
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

class AdbServerTest : TestCase() {
    @get:Rule
    val runtimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    private fun isWindows(): Boolean {
        try {
            val res = adbServer.performCmd("cmd", arguments = listOf("/c", "ver"))
            return res.contains("Windows")
        } catch (_: Exception) {
            return false
        }
    }

    @Test
    fun singleCommandTest() = run {
        val devices = adbServer.performAdb("devices")
        assert(devices.isNotEmpty())
    }

    @Test
    fun singleComplexCommandTest() = run {
        val devices = adbServer.performAdb("devices", arguments = emptyList())
        assert(devices.isNotEmpty())
    }

    @Test
    fun commandWithArgumentsTest() = run {
        adbServer.performShell("mkdir", arguments = listOf("-p", "\"/sdcard/Documents/path with whitespace/dir\""))
        adbServer.performShell("touch", arguments = listOf("\"/sdcard/Documents/path with whitespace/dir/myfile.txt\""))
        adbServer.performShell("rm", arguments = listOf("\"/sdcard/Documents/path with whitespace/dir/myfile.txt\""))
    }

    @SdkSuppress(minSdkVersion = 23)
    @Test
    fun commandWithAnotherCommandAsArgumentsTest() = run {
        if (isWindows()) {
            adbServer.performCmd("cmd", arguments = listOf("/c", "adb shell dumpsys deviceidle | findstr mForceIdle"))
        } else {
            adbServer.performCmd("sh", arguments = listOf("-c", "adb shell dumpsys deviceidle | grep mForceIdle"))
        }
    }
}
