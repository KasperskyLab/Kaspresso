package com.kaspersky.components.alluresupport.interceptors.testrun

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
