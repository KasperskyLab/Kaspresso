package com.kaspersky.kaspressample.adb_server_tests

import android.Manifest
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.filters.SdkSuppress
import androidx.test.rule.GrantPermissionRule
import com.kaspersky.kaspressample.MainActivity
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test

class AdbServerTest : TestCase() {
    @get:Rule
    val runtimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

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
        adbServer.performCmd("sh", arguments = listOf("-c", "adb shell dumpsys deviceidle | grep mForceIdle"))
    }
}
