package com.kaspersky.components.alluresupport.interceptors.testrun

import com.kaspersky.kaspresso.interceptors.watcher.testcase.TestRunWatcherInterceptor
import com.kaspersky.kaspresso.testcases.models.info.TestInfo
import com.kaspersky.components.alluresupport.files.attachLogcatToAllureReport
import com.kaspersky.kaspresso.device.logcat.dumper.LogcatDumper

class DumpLogcatTestInterceptor(
    private val logcatDumper: LogcatDumper
) : TestRunWatcherInterceptor {

    override fun onTestStarted(testInfo: TestInfo) {
        logcatDumper.charge()
    }

    override fun onTestFinished(testInfo: TestInfo, success: Boolean) {
        logcatDumper.dumpAndApply("TestLogcat") { attachLogcatToAllureReport() }
    }
}
