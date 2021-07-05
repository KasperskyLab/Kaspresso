package com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.video

import com.kaspersky.kaspresso.device.video.Videos
import com.kaspersky.kaspresso.interceptors.watcher.testcase.TestRunWatcherInterceptor
import com.kaspersky.kaspresso.testcases.models.info.TestInfo

class VideoRecordingInterceptor(
    private val videos: Videos
) : TestRunWatcherInterceptor {

    override fun onTestStarted(testInfo: TestInfo) {
        videos.record("Video_${testInfo.testName}")
    }

    override fun onTestFinished(testInfo: TestInfo, success: Boolean) {
        videos.save()
    }
}
