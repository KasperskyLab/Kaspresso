package com.kaspersky.components.alluresupport.interceptors.testrun

import android.app.Instrumentation
import androidx.test.uiautomator.UiDevice
import com.google.common.io.CharStreams
import com.kaspersky.components.alluresupport.files.resources.AllureResourcesRootDirsProvider
import com.kaspersky.kaspresso.files.dirs.DirsProvider
import com.kaspersky.kaspresso.interceptors.watcher.testcase.TestRunWatcherInterceptor
import com.kaspersky.kaspresso.testcases.models.info.TestInfo
import io.qameta.allure.kotlin.Allure
import io.qameta.allure.kotlin.util.PropertiesUtils
import org.json.JSONObject
import java.io.File

private const val ATTACHMENTS_JSON_FIELD = "attachments"
private const val NAME_JSON_FIELD = "name"
private const val SOURCE_JSON_FIELD = "source"
private const val MP4_EXTENSION = "mp4"

/**
 * Current allure version stores reports to /data/data/your.package.name/files/allure-results.
 * This interceptor moves them to default artifacts folder after test and replaces mock videos with real ones
 */
class MoveReportsInterceptor(
    private val instrumentation: Instrumentation,
    private val dirsProvider: DirsProvider,
    private val rootDirsProvider: AllureResourcesRootDirsProvider,
    private val videosHolder: AttachedAllureVideosHolder,
    private val device: UiDevice
) : TestRunWatcherInterceptor {

    private var lastTestCaseUuid: String? = null

    override fun onMainSectionStarted(testInfo: TestInfo) {
        lastTestCaseUuid = Allure.lifecycle.getCurrentTestCase()
    }

    override fun onTestFinished(testInfo: TestInfo, success: Boolean) {
        val allureTargetDir = moveAllureReportToSdCard()
        removeStubs(allureTargetDir)
        moveAttachedVideosToProperDirectories(allureTargetDir)
        cleanUp()
    }

    /**
     * Deletes allure report under /data/data/your.package.name/files and empty directories after moving real videos from them
     */
    private fun cleanUp() {
        getOriginalAllureDir().deleteRecursively()
        val videosDir = dirsProvider.provideNew(rootDirsProvider.videoRootDir)
        device.executeShellCommand("rm -rf ${videosDir.absolutePath}")

        dirsProvider.provideNew(rootDirsProvider.screenshotsRootDir).deleteRecursively()
        dirsProvider.provideNew(rootDirsProvider.logcatRootDir).deleteRecursively()
        dirsProvider.provideNew(rootDirsProvider.viewHierarchy).deleteRecursively()
    }

    /**
     * @return allure results dir under /data/data/your.package.name/files
     */
    private fun getOriginalAllureDir(): File = instrumentation.targetContext.filesDir.resolve(PropertiesUtils.resultsDirectoryPath)

    /**
     * Moves allure report from /data/data/your.package.name/files/allure-report to external storage e.g.
     * /sdcard/Documents/.../allure-results
     * @return allure target directory on sdcard
     */
    private fun moveAllureReportToSdCard(): File {
        val allureResultsFile = getOriginalAllureDir()
        val allureTargetDir = dirsProvider.provideNew(rootDirsProvider.allureRootDir)
        if (!allureResultsFile.exists()) {
            throw IllegalArgumentException("Unable to move allure results from $allureResultsFile. File not found")
        }
        allureResultsFile.copyRecursively(allureTargetDir)

        return allureTargetDir
    }

    /**
     * During allure test stubs are attached to report because real videos can't be recorded directly to /data/data.
     * After kaspresso moves report to sdcard it has to replace stubs with real videos. Videos are moved using adb shell
     * 👀 https://issuetracker.google.com/issues/258277873
     * @param allureTargetDir allure directory under /sdcard
     */
    private fun removeStubs(allureTargetDir: File) {
        allureTargetDir.listFiles()?.forEach {
            if (it.extension.contains(MP4_EXTENSION, ignoreCase = true)) {
                it.delete()
            }
        }
    }

    /**
     * Move (and rename) real videos from their original directories to report dir
     * @param allureTargetDir allure directory under /sdcard
     */
    private fun moveAttachedVideosToProperDirectories(allureTargetDir: File) {
        videosHolder.attachedVideos.forEach {
            saveAttachedVideo(it, allureTargetDir)
        }
    }

    /**
     * Used for screen recording workaround:
     * screen cast can't be recorded to /data/data/.../allure-results because of permissions issues (👀 https://issuetracker.google.com/issues/258277873).
     * We have to save video on /sdcard and in the same time attach a stub to allure report. Before moving report to /sdcard
     * we need to parse report and save stub file name to rename and move an actual one to allure report dir after moving report to /sdcard. Then we
     * remove stub videos in /sdcard and /data/data and move real video file to allure report directory to replace stubs
     * @param attachedVideo video attached to report
     * @param allureTargetDir allure directory under /sdcard
     */
    private fun saveAttachedVideo(attachedVideo: AttachedVideo, allureTargetDir: File) {
        val allureReportFile = allureTargetDir.resolve("${lastTestCaseUuid ?: ""}-result.json")
        if (!allureReportFile.exists()) {
            throw IllegalStateException("Can't attach video to report because the latter not found. Tried path ${allureReportFile.absolutePath}")
        }

        allureReportFile.inputStream().use { inputStream ->
            val json = JSONObject(CharStreams.toString(inputStream.reader()))
            val attachments = json.getJSONArray(ATTACHMENTS_JSON_FIELD)
            for (i in 0 until attachments.length()) {
                val attachment = attachments.getJSONObject(i)
                val attachmentName = attachment.getString(NAME_JSON_FIELD)
                if (attachmentName.equals(attachedVideo.attachedStubFile.name, ignoreCase = true)) {
                    val source = attachment.getString(SOURCE_JSON_FIELD) // Attachment real filename
                    device.executeShellCommand("mv ${attachedVideo.actualFile.absolutePath} ${allureTargetDir.resolve(source)}")
                    attachedVideo.attachedStubFile.delete()
                    break
                }
            }
        }
    }
}
