package com.kaspersky.components.alluresupport.visual

import android.graphics.Bitmap
import com.kaspersky.components.alluresupport.files.attachScreenshotToAllureReport
import com.kaspersky.kaspresso.files.resources.ResourceFileNamesProvider
import com.kaspersky.kaspresso.files.resources.ResourcesDirsProvider
import com.kaspersky.kaspresso.files.resources.ResourcesRootDirsProvider
import com.kaspersky.kaspresso.internal.visual.DefaultScreenshotsComparator
import com.kaspersky.kaspresso.logger.Logger
import com.kaspersky.kaspresso.visual.VisualTestParams
import java.io.File

class AllureScreenshotsComparator(
    visualTestParams: VisualTestParams,
    logger: Logger,
    resourcesRootDirsProvider: ResourcesRootDirsProvider,
    resourcesDirsProvider: ResourcesDirsProvider,
    resourceFileNamesProvider: ResourceFileNamesProvider,
) : DefaultScreenshotsComparator(visualTestParams, logger, resourcesRootDirsProvider, resourcesDirsProvider, resourceFileNamesProvider) {
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
