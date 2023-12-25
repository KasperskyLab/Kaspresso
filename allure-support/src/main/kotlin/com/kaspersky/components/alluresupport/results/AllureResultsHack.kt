package com.kaspersky.components.alluresupport.results

import androidx.test.uiautomator.UiDevice
import com.kaspersky.components.alluresupport.files.attachVideoToAllureReport
import com.kaspersky.components.alluresupport.files.dirs.AllureDirsProvider
import com.kaspersky.components.alluresupport.files.resources.AllureResourcesRootDirsProvider
import com.kaspersky.components.alluresupport.files.resources.impl.AllureResourceFilesProvider
import com.kaspersky.kaspresso.runner.listener.KaspressoRunListener
import io.qameta.allure.kotlin.Allure
import org.junit.runner.Result
import java.io.File

class AllureResultsHack(
    private val uiDevice: UiDevice,
    resourcesRootDirsProvider: AllureResourcesRootDirsProvider,
    dirsProvider: AllureDirsProvider,
) : KaspressoRunListener {

    private val allureResultsSourceDir: File =
        dirsProvider.provideNew(resourcesRootDirsProvider.allureRootDir)

    private val allureResultsTargetDir: File =
        dirsProvider.provideNewOnSdCard(resourcesRootDirsProvider.allureRootDir)

    private val stubVideosDir: File =
        dirsProvider.provideNew(resourcesRootDirsProvider.stubVideoDir)

    private val videosToInject = mutableListOf<VideoBinding>()

    fun onVideoRecorded(resourceFilesProvider: AllureResourceFilesProvider, videoFile: File) {
        val stubVideoFile = resourceFilesProvider.provideStubVideoFile(videoFile)
        stubVideoFile.attachVideoToAllureReport()
        val testUuid = Allure.lifecycle.getCurrentTestCase() ?: ""
        videosToInject += VideoBinding(testUuid, stubVideoFile, videoFile)
    }

    override fun testRunFinished(result: Result) {
        if (videosToInject.isEmpty()) {
            return
        }
        val allureResultsInjector = AllureResultInjector(
            parser = AllureResultJsonParser(),
            uiDevice = uiDevice,
            resultsDir = allureResultsTargetDir
        )
        allureResultsSourceDir.copyRecursively(allureResultsTargetDir)
        allureResultsInjector.injectVideos(allureResultsTargetDir, videosToInject)
        allureResultsSourceDir.deleteRecursively()
        stubVideosDir.deleteRecursively()
    }

    data class VideoBinding(
        val testUuid: String,
        val stub: File,
        val actual: File
    )
}
