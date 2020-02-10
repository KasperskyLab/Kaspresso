package com.kaspersky.kaspresso.device.screenshots

import com.kaspersky.kaspresso.device.screenshots.screenshotfiles.ScreenshotDirectoryProvider
import com.kaspersky.kaspresso.device.screenshots.screenshotfiles.ScreenshotFileProvider
import com.kaspersky.kaspresso.device.screenshots.screenshotfiles.ScreenshotNameProvider
import com.kaspersky.kaspresso.device.screenshots.screenshotmaker.ScreenshotMaker
import com.kaspersky.kaspresso.internal.extensions.other.getStackTraceAsString
import com.kaspersky.kaspresso.logger.UiTestLogger
import java.io.File

/**
 * The implementation of the [Screenshots] interface.
 */
class ScreenshotsImpl(
    private val logger: UiTestLogger,
    private val screenshotMaker: ScreenshotMaker,
    screenshotDirectoryProvider: ScreenshotDirectoryProvider,
    screenshotNameProvider: ScreenshotNameProvider,
    screenshotRootDir: File = File("screenshots")
) : Screenshots, ScreenshotTestStartListener {

    private val fileProvider = ScreenshotFileProvider(screenshotDirectoryProvider, screenshotNameProvider, screenshotRootDir)

    /**
     * Takes a screenshot if it is possible, otherwise logs the error.
     * By default a screenshot name looks like <device storage>/screenshotRootDir/<test run number>/<test class name>/<test method name>/[tag].png
     * See [ScreenshotFileProvider], [ScreenshotDirectoryProvider] for more details
     *
     * Required Permissions: WRITE_EXTERNAL_STORAGE.
     *
     * @param tag a unique tag to further identify the screenshot. Must match [a-zA-Z0-9_-]+.
     */
    override fun take(tag: String) {
        runCatching {
            val file = fileProvider.getScreenshotFile(tag)
            screenshotMaker.takeScreenshot(file)
        }.onFailure { e ->
            logger.e("An error while making screenshot occurred: ${e.getStackTraceAsString()}")
        }
    }

    override fun onTestStarted() {
        fileProvider.incrementRunNumberOfCurrentTest()
    }
}