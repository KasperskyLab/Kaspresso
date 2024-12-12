package com.kaspersky.components.alluresupport.visual

import com.kaspersky.components.alluresupport.files.dirs.AllureDirsProvider
import com.kaspersky.kaspresso.device.files.Files
import com.kaspersky.kaspresso.files.resources.ResourcesRootDirsProvider
import com.kaspersky.kaspresso.logger.Logger
import com.kaspersky.kaspresso.visual.VisualTestParams
import com.kaspersky.kaspresso.visual.VisualTestType
import com.kaspersky.kaspresso.visual.VisualTestWatcher
import java.io.File

class AllureVisualTestWatcher(
    private val params: VisualTestParams,
    private val logger: Logger,
    private val dirsProvider: AllureDirsProvider,
    resourcesRootDirsProvider: ResourcesRootDirsProvider,
    private val files: Files,
) : VisualTestWatcher {

    private val diffDir = dirsProvider.provideNew(resourcesRootDirsProvider.screenshotsDiffRootDir)
    private val originalScreenshotsTargetDir: File
        get() {
            val rootDir = dirsProvider.provideNewOnSdCard(File("")).absolutePath
            return File(rootDir, File(params.hostScreenshotsDir).name)
        }

    override fun prepare() {
        logger.i("Visual test run started. Parameters: $params")

        if (params.testType == VisualTestType.Compare) {
            logger.i("Pushing the screenshots unto the device...")
            dirsProvider.provideCleared(diffDir)

            // Allure stores all files in the app's private directory. We can't "adb push" directly there,
            // so we have to do this in 2 steps
            dirsProvider.provideCleared(originalScreenshotsTargetDir)
            val tmp = dirsProvider.provideNewOnSdCard(File(""))
            files.push(params.hostScreenshotsDir, tmp.absolutePath)
            val target = dirsProvider.provideNew(File("")).resolve(params.hostScreenshotsDir)
            File(tmp, params.hostScreenshotsDir).copyRecursively(target, overwrite = true)
            logger.i("Done pushing the screenshots unto the device")
        }
    }

    override fun cleanUp() {
        // Do nothing
    }
}
