package com.kaspersky.components.interceptors.allure.interceptors.testrun

import com.kaspersky.components.interceptors.allure.files.attachLogcatToAllureReport
import com.kaspersky.kaspresso.device.logcat.dumper.LogcatDumper
import com.kaspersky.kaspresso.interceptors.watcher.testcase.TestRunWatcherInterceptor
import com.kaspersky.kaspresso.testcases.models.info.TestInfo

class DumpLogcatTestInterceptor(
    private val logcatDumper: LogcatDumper
) : TestRunWatcherInterceptor {

    override fun onTestFinished(testInfo: TestInfo, success: Boolean) {
        logcatDumper.dumpAndApply("TestLogcat") { attachLogcatToAllureReport() }
    }
}
