package com.kaspersky.kaspresso.device.screenshots.screenshotfiles

/**
 * Returns a directory for screenshots of a separate test
 */
interface ScreenshotDirectoryProvider {

    fun getDirectoryForTest(testMethod: TestMethod): String
}