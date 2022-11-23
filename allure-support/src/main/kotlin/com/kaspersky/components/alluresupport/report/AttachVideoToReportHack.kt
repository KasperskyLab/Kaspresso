package com.kaspersky.components.alluresupport.report

import android.app.Instrumentation
import com.google.common.io.CharStreams
import com.kaspersky.components.alluresupport.files.resources.impl.DefaultAllureResourcesRootDirsProvider
import com.kaspersky.components.alluresupport.interceptors.testrun.AttachedAllureVideosHolder
import com.kaspersky.components.alluresupport.interceptors.testrun.AttachedVideo
import com.kaspersky.kaspresso.files.dirs.DefaultDirsProvider
import com.kaspersky.kaspresso.instrumental.InstrumentalDependencyProvider
import com.kaspersky.kaspresso.instrumental.InstrumentalDependencyProviderFactory
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import io.qameta.allure.kotlin.util.PropertiesUtils
import org.json.JSONObject
import java.io.File

private const val ATTACHMENTS_JSON_FIELD = "attachments"
private const val NAME_JSON_FIELD = "name"
private const val SOURCE_JSON_FIELD = "source"
private const val MP4_EXTENSION = "mp4"

class AttachVideoToReportHack(
    private val instrumentation: Instrumentation
) {
    private val allureDir: String = PropertiesUtils.resultsDirectoryPath
    private val instrumentalDependencyProvider: InstrumentalDependencyProvider =
        InstrumentalDependencyProviderFactory().getComponentProvider<Kaspresso>(instrumentation)
    private val dirsProvider = DefaultDirsProvider(instrumentalDependencyProvider)
    private val rootDirsProvider = DefaultAllureResourcesRootDirsProvider()

    fun hack() {
        val allureTargetDir = moveAllureReportToSdCard()
        removeStubs(allureTargetDir)
        moveAttachedVideosToProperDirectories(allureTargetDir)
        cleanUp()
    }

    private fun moveAllureReportToSdCard(): File {
        val allureResultsDir = getOriginalAllureDir()
        val allureTargetDir = dirsProvider.provideNew(rootDirsProvider.allureRootDir)
        if (!allureResultsDir.exists()) {
            throw IllegalArgumentException("Unable to move allure results from $allureResultsDir. File not found")
        }
        allureResultsDir.copyRecursively(allureTargetDir)
        return allureTargetDir
    }

    private fun removeStubs(allureTargetDir: File) {
        allureTargetDir.listFiles()?.forEach {
            if (it.extension.contains(MP4_EXTENSION, ignoreCase = true)) {
                it.delete()
            }
        }
    }

    private fun moveAttachedVideosToProperDirectories(allureTargetDir: File) {
        AttachedAllureVideosHolder.attachedVideos.forEach {
            saveAttachedVideo(it, allureTargetDir)
        }
    }

    private fun cleanUp() {
        getOriginalAllureDir().deleteRecursively()
        val videosDir = dirsProvider.provideNew(rootDirsProvider.videoRootDir)
        instrumentalDependencyProvider.uiDevice.executeShellCommand("rm -rf ${videosDir.absolutePath}")

        dirsProvider.provideNew(rootDirsProvider.screenshotsRootDir).deleteRecursively()
        dirsProvider.provideNew(rootDirsProvider.logcatRootDir).deleteRecursively()
        dirsProvider.provideNew(rootDirsProvider.viewHierarchy).deleteRecursively()
    }

    private fun getOriginalAllureDir(): File = instrumentation.targetContext.filesDir.resolve(allureDir)

    private fun saveAttachedVideo(attachedVideo: AttachedVideo, allureTargetDir: File) {
        val allureReportFile = allureTargetDir.resolve("${attachedVideo.uuid}-result.json")
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
                    instrumentalDependencyProvider.uiDevice.executeShellCommand("mv ${attachedVideo.actualFile.absolutePath} ${allureTargetDir.resolve(source)}")
                    attachedVideo.attachedStubFile.delete()
                    break
                }
            }
        }
    }
}
