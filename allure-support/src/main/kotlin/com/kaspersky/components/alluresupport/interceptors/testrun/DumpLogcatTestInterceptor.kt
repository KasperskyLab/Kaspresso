package com.kaspersky.components.alluresupport.interceptors.testrun

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
