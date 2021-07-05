package com.kaspersky.kaspresso.device.screenshots.screenshotfiles

import com.kaspersky.kaspresso.files.TestMethod

/**
 * Provides a directory for screenshots of a separate test
 */
interface ScreenshotDirectoryProvider {

    /**
     * @param testMethod specifies what test is running
     * @param runNumber specifies how many times a given test have already run
     */
    fun getDirectoryForTest(testMethod: TestMethod, runNumber: Int): String
}
