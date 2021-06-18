package com.kaspersky.kaspresso.device.screenshots

import android.util.Log
import com.kaspersky.kaspresso.device.screenshots.screenshotmaker.ScreenshotMaker
import com.kaspersky.kaspresso.files.resources.ResourceFilesProvider
import com.kaspersky.kaspresso.logger.UiTestLogger
import java.io.File

/**
 * The implementation of the [Screenshots] interface.
 */
class ScreenshotsImpl(
    private val logger: UiTestLogger,
    private val resourceFilesProvider: ResourceFilesProvider,
    private val screenshotMaker: ScreenshotMaker,
) : Screenshots {

    /**
     * Takes a screenshot if it is possible, otherwise logs the error.
     * By default a screenshot name looks like <device storage>/screenshotRootDir/<test run number>/<test class name>/<test method name>/[tag].png
     * See [ScreenshotFileProvider], [ScreenshotDirectoryProvider] for more details
     *
     * Required Permissions: WRITE_EXTERNAL_STORAGE.
     *
     * @param tag a unique tag to further identify the screenshot. Must match [a-zA-Z0-9_-]+.
     */
    override fun take(tag: String): Unit = doTakeAndApply(tag, null)

    override fun takeAndApply(tag: String, block: File.() -> Unit): Unit = doTakeAndApply(tag, block)

    private fun doTakeAndApply(tag: String, block: (File.() -> Unit)?) {
        try {
            val screenshotFile: File = resourceFilesProvider.provideScreenshotFile(tag)
            screenshotMaker.takeScreenshot(screenshotFile)
            block?.invoke(screenshotFile)
        } catch (e: Throwable) {
            logger.e("An error while making screenshot occurred: ${Log.getStackTraceString(e)}")
        }
    }
}
