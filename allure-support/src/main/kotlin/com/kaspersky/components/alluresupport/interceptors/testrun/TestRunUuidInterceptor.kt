package com.kaspersky.components.alluresupport.interceptors.testrun

import com.kaspersky.kaspresso.interceptors.watcher.testcase.TestRunWatcherInterceptor
import com.kaspersky.kaspresso.testcases.models.info.TestInfo
import io.qameta.allure.kotlin.Allure

class TestRunUuidInterceptor(
    private val stateHolder: TestRunStateHolder
) : TestRunWatcherInterceptor {
    override fun onTestStarted(testInfo: TestInfo) {
        stateHolder.lastTestCaseUuid = Allure.lifecycle.getCurrentTestCase()
    }
}
