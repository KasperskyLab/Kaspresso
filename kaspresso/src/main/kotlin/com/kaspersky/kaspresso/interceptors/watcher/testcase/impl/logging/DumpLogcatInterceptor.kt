package com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.logging

import com.kaspersky.kaspresso.interceptors.watcher.testcase.TestRunWatcherInterceptor
import com.kaspersky.kaspresso.device.logcat.dumper.LogcatDumper
import com.kaspersky.kaspresso.testcases.models.info.TestInfo

class DumpLogcatInterceptor(
    private val logcatDumper: LogcatDumper
) : TestRunWatcherInterceptor {

    override fun onTestStarted(testInfo: TestInfo) {
        logcatDumper.charge()
    }

    override fun onTestFinished(testInfo: TestInfo, success: Boolean) {
        logcatDumper.dump("TestLogcat")
    }
}
