package com.kaspersky.components.alluresupport.interceptors.testrun

import com.kaspersky.components.alluresupport.files.resources.impl.AllureResourceFilesProvider
import com.kaspersky.components.alluresupport.results.AllureResultsHack
import com.kaspersky.kaspresso.device.video.Videos
import com.kaspersky.kaspresso.interceptors.watcher.testcase.TestRunWatcherInterceptor
import com.kaspersky.kaspresso.testcases.models.info.TestInfo

/**
 * Due to screen recorder bug we have to perform a workaround which requires VideoRecordingTestInterceptor
 * and MoveReportsInterceptor to be the last two interceptors in allure reports. So if you use VideoRecordingTestInterceptor
 * be sure to use MoveReportsInterceptor too. Otherwise your report will be under /data/data/your.package.name/files/allure-results
 * and videos under /sdcard
 */
class HackyVideoRecordingTestInterceptor(
    private val videos: Videos,
    private val resourceFilesProvider: AllureResourceFilesProvider,
    private val hack: AllureResultsHack
) : TestRunWatcherInterceptor {

    override fun onTestStarted(testInfo: TestInfo) {
        videos.record("Video_${testInfo.testName}")
    }

    override fun onTestFinished(testInfo: TestInfo, success: Boolean) {
        videos.saveAndApply {
            hack.onVideoRecorded(resourceFilesProvider = resourceFilesProvider, videoFile = this)
        }
    }
}
