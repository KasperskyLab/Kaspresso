package com.kaspersky.kaspresso.device.screenshots

import androidx.test.platform.app.InstrumentationRegistry
import com.kaspersky.kaspresso.device.screenshots.screenshotfiles.ScreenshotDirectoryProvider
import com.kaspersky.kaspresso.device.screenshots.screenshotfiles.ScreenshotFileProvider
import com.kaspersky.kaspresso.device.screenshots.screenshotmaker.ScreenshotMaker
import com.kaspersky.kaspresso.device.screenshots.screenshotfiles.ScreenshotNameProvider
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
    screenshotDir: File = File("screenshots")
) : Screenshots {

    private val fileProvider = ScreenshotFileProvider(screenshotDirectoryProvider, screenshotNameProvider, screenshotDir)

    /**
     * Takes screenshot if it is possible, otherwise logs the error.
     * The method adds System.currentTimeMillis() to the tag to save all screenshots of a test
     * running several times per the same suite. That's why a name will look
     * like "1570158949869_ScreenshotSampleTest_step_1".
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
}