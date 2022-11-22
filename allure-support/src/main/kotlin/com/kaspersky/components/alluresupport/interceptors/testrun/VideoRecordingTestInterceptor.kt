package com.kaspersky.components.alluresupport.interceptors.testrun

import com.kaspersky.components.alluresupport.files.attachVideoToAllureReport
import com.kaspersky.kaspresso.device.video.Videos
import com.kaspersky.kaspresso.files.dirs.AllureDirsProvider
import com.kaspersky.kaspresso.interceptors.watcher.testcase.TestRunWatcherInterceptor
import com.kaspersky.kaspresso.testcases.models.info.TestInfo
import io.qameta.allure.kotlin.Allure
import io.qameta.allure.kotlin.model.Status

/**
 * Due to screen recorder bug we have to perform a workaround which requires VideoRecordingTestInterceptor
 * and MoveReportsInterceptor to be the last two interceptors in allure reports. So if you use VideoRecordingTestInterceptor
 * be sure to use MoveReportsInterceptor too. Otherwise your report will be under /data/data/your.package.name/files/allure-results
 * and videos under /sdcard
 */
class VideoRecordingTestInterceptor(
    private val videos: Videos,
    private val allureDirsProvider: AllureDirsProvider,
    private val stateHolder: AttachedAllureVideosHolder
) : TestRunWatcherInterceptor {

    override fun onTestStarted(testInfo: TestInfo) {
        videos.record("Video_${testInfo.testName}")
    }

    override fun onTestFinished(testInfo: TestInfo, success: Boolean) {
        videos.saveAndApply {
            val stubFile = allureDirsProvider.provideReportVideoAttachmentStub(this)
            stubFile.attachVideoToAllureReport()
            val uuid = Allure.lifecycle.getCurrentTestCase() ?: ""
            Allure.lifecycle.updateTestCase {
                it.status = if (success) Status.PASSED else Status.FAILED
            }
            Allure.lifecycle.stopTestCase(uuid)
            Allure.lifecycle.writeTestCase(uuid)
            stateHolder.rememberAttachedVideo(stubFile = stubFile, actualFile = this)
        }
    }
}
