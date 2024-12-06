package com.kaspersky.kaspresso.internal.visual

import com.kaspersky.kaspresso.device.files.Files
import com.kaspersky.kaspresso.files.dirs.DirsProvider
import com.kaspersky.kaspresso.files.resources.ResourcesRootDirsProvider
import com.kaspersky.kaspresso.internal.exceptions.AdbServerException
import com.kaspersky.kaspresso.logger.Logger
import com.kaspersky.kaspresso.visual.VisualTestParams
import com.kaspersky.kaspresso.visual.VisualTestType
import com.kaspersky.kaspresso.visual.VisualTestWatcher
import java.io.File

internal class DefaultVisualTestWatcher(
    private val params: VisualTestParams,
    private val logger: Logger,
    private val dirsProvider: DirsProvider,
    resourcesRootDirsProvider: ResourcesRootDirsProvider,
    private val files: Files,
) : VisualTestWatcher {
    private val diffDir = dirsProvider.provideNew(resourcesRootDirsProvider.screenshotsDiffRootDir)
    private val originalScreenshotsTargetDir: File
        get() {
            val rootDir = dirsProvider.provideNew(File("")).absolutePath
            return File(rootDir, File(params.hostScreenshotsDir).name)
        }
    private val newScreenshotsDir = dirsProvider.provideNew(resourcesRootDirsProvider.screenshotsRootDir)

    override fun prepare() {
        logger.i("Visual test run started. Parameters: $params")

        if (params.testType == VisualTestType.Compare) {
            logger.i("Pushing the screenshots unto the device...")
            dirsProvider.provideCleared(diffDir)

            dirsProvider.provideCleared(originalScreenshotsTargetDir)
            try {
                files.push(params.hostScreenshotsDir, dirsProvider.provideNew(File("")).absolutePath)
            } catch (ex: AdbServerException) {
                throw RuntimeException("Failed to push screenshots. Please, check that they exist by the path: ${params.hostScreenshotsDir} (relatively to the ADB server executable", ex)
            }
            logger.i("Done pushing the screenshots unto the device")
        }
    }

    override fun cleanUp() {
        logger.i("Visual test finished")

        if (params.testType == VisualTestType.Compare) {
            logger.i("Pulling diff files from the device...")
            files.pull(diffDir.absolutePath, ".")
            logger.i("Done pulling diff files from the device")
            logger.i("Pulling new screenshot files from the device...")
            files.pull(newScreenshotsDir.absolutePath, ".")
            logger.i("Done pulling new screenshot files from the device...")
        }
    }
}
