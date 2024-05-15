package com.kaspersky.components.alluresupport.interceptors.testrun

import com.kaspersky.components.alluresupport.results.AllureVisualTestFlag
import com.kaspersky.kaspresso.device.screenshots.ScreenshotsImpl
import com.kaspersky.kaspresso.interceptors.watcher.testcase.TestRunWatcherInterceptor
import com.kaspersky.kaspresso.testcases.models.info.TestInfo

class VisualTestLateFailInterceptor : TestRunWatcherInterceptor {
    override fun onAfterSectionStarted(testInfo: TestInfo) {
        if (AllureVisualTestFlag.shouldFailLate.get()) {
            throw ScreenshotsImpl.ScreenshotDoesntMatchException("There were failed screenshot comparisons. Check the allure report")
        }
    }
}
