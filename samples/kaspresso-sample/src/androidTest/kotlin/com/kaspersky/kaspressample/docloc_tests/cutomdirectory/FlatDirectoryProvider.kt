package com.kaspersky.kaspressample.docloc_tests.cutomdirectory

import com.kaspersky.kaspresso.device.screenshots.screenshotfiles.ScreenshotDirectoryProvider
import com.kaspersky.kaspresso.files.TestMethod

internal object FlatDirectoryProvider : ScreenshotDirectoryProvider {
    override fun getDirectoryForTest(testMethod: TestMethod, runNumber: Int): String = ""
}
