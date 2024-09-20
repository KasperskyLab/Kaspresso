package com.kaspersky.components.alluresupport.results

import androidx.test.uiautomator.UiDevice
import com.kaspersky.components.alluresupport.files.attachVideoToAllureReport
import com.kaspersky.components.alluresupport.files.dirs.AllureDirsProvider
import com.kaspersky.components.alluresupport.files.resources.AllureResourcesRootDirsProvider
import com.kaspersky.components.alluresupport.files.resources.impl.AllureResourceFilesProvider
import com.kaspersky.kaspresso.runner.listener.KaspressoRunListener
import com.kaspersky.kaspresso.visual.VisualTestParams
import com.kaspersky.kaspresso.visual.VisualTestType
import io.qameta.allure.kotlin.Allure
import org.junit.runner.Result
import java.io.File

class AllureResultsHack(
    private val uiDevice: UiDevice,
    private val visualTestParams: VisualTestParams,
    resourcesRootDirsProvider: AllureResourcesRootDirsProvider,
    private val dirsProvider: AllureDirsProvider,
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
        val allureResultsInjector = AllureResultInjector(
            parser = AllureResultJsonParser(),
            uiDevice = uiDevice,
            resultsDir = allureResultsTargetDir
        )

        allureResultsSourceDir.copyRecursively(allureResultsTargetDir)
        if (videosToInject.isNotEmpty()) {
            allureResultsInjector.injectVideos(allureResultsTargetDir, videosToInject)
        }

        allureResultsSourceDir.deleteRecursively()
        stubVideosDir.deleteRecursively()

        if (visualTestParams.testType == VisualTestType.Record) {
            val rootDir = dirsProvider.provideNew(File("")).absolutePath
            val newScreenshotsDir = File(rootDir, File(visualTestParams.hostScreenshotsDir).name)
            val targetScreenshotsDir = dirsProvider.provideNewOnSdCard(File(visualTestParams.hostScreenshotsDir))
            newScreenshotsDir.copyRecursively(targetScreenshotsDir)
        }
    }

    data class VideoBinding(
        val testUuid: String,
        val stub: File,
        val actual: File
    )
}
