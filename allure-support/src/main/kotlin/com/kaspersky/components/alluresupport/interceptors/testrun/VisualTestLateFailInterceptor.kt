package com.kaspersky.components.alluresupport.interceptors.testrun

import com.kaspersky.components.alluresupport.results.AllureVisualTestFlag
import com.kaspersky.kaspresso.device.screenshots.ScreenshotsImpl
import com.kaspersky.kaspresso.interceptors.watcher.testcase.TestRunWatcherInterceptor
import com.kaspersky.kaspresso.testcases.models.info.TestInfo

class VisualTestLateFailInterceptor : TestRunWatcherInterceptor {
    override fun onAfterSectionStarted(testInfo: TestInfo) {
        if (AllureVisualTestFlag.shouldFailLate.get()) {
            // Wrap with assertion error so test would be marked as FAILED instead of BROKEN
            // See https://github.com/allure-framework/allure-kotlin allure-kotlin-commons/src/main/kotlin/io/qameta/allure/kotlin/util/ResultsUtils.kt
            throw AssertionError(ScreenshotsImpl.ScreenshotDoesntMatchException("There were failed screenshot comparisons. Check the allure report"))
        }
    }
}
