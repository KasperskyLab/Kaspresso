package com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.report

import com.kaspersky.kaspresso.device.logcat.dumper.LogcatDumper
import com.kaspersky.kaspresso.interceptors.watcher.testcase.TestRunWatcherInterceptor
import com.kaspersky.kaspresso.testcases.models.info.TestInfo

class BuildStepReportWatcherInterceptor(
    private val logcatDumper: LogcatDumper
) : TestRunWatcherInterceptor {

    override fun onTestFinished(testInfo: TestInfo, success: Boolean) {
        logcatDumper.dump("TestLogcat")
    }
}
