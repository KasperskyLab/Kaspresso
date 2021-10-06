package com.kaspersky.kaspressample.docloc_tests.customdirectory.legacy

import com.kaspersky.kaspresso.device.screenshots.screenshotfiles.ScreenshotDirectoryProvider
import com.kaspersky.kaspresso.device.screenshots.screenshotfiles.TestMethod

internal object FlatDirectoryProvider : ScreenshotDirectoryProvider {
    override fun getDirectoryForTest(testMethod: TestMethod, runNumber: Int): String = ""
}
