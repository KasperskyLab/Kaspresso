package com.kaspersky.components.alluresupport.visual

import android.graphics.Bitmap
import com.kaspersky.components.alluresupport.files.attachScreenshotToAllureReport
import com.kaspersky.kaspresso.files.dirs.DirsProvider
import com.kaspersky.kaspresso.files.resources.ResourcesRootDirsProvider
import com.kaspersky.kaspresso.internal.visual.DefaultScreenshotsComparator
import com.kaspersky.kaspresso.logger.Logger
import com.kaspersky.kaspresso.visual.VisualTestParams
import java.io.File

class AllureScreenshotsComparator(
    visualTestParams: VisualTestParams,
    logger: Logger,
    dirsProvider: DirsProvider,
    resourcesRootDirsProvider: ResourcesRootDirsProvider,
) : DefaultScreenshotsComparator(visualTestParams, logger, dirsProvider, resourcesRootDirsProvider) {
    override fun compare(originalScreenshot: File, newScreenshot: File): Boolean {
        val doScreenshotsMatch = super.compare(originalScreenshot, newScreenshot)
        if (!doScreenshotsMatch) {
            originalScreenshot.attachScreenshotToAllureReport()
            newScreenshot.attachScreenshotToAllureReport()
        }

        return doScreenshotsMatch
    }

    override fun processScreenshotDiff(original: Bitmap, diffPixels: IntArray, diffName: String): File {
        return super.processScreenshotDiff(original, diffPixels, diffName).also {
            it.attachScreenshotToAllureReport()
        }
    }
}
