package com.kaspersky.components.allure_support.interceptors.testrun

import com.kaspersky.components.allure_support.files.attachVideoToAllureReport
import com.kaspersky.kaspresso.device.video.Videos
import com.kaspersky.kaspresso.interceptors.watcher.testcase.TestRunWatcherInterceptor
import com.kaspersky.kaspresso.testcases.models.info.TestInfo

class VideoRecordingTestInterceptor(
    private val videos: Videos
) : TestRunWatcherInterceptor {

    override fun onTestStarted(testInfo: TestInfo) {
        videos.record("Video_${testInfo.testName}")
    }

    override fun onTestFinished(testInfo: TestInfo, success: Boolean) {
        videos.saveAndApply { attachVideoToAllureReport() }
    }
}
