package com.kaspersky.kaspresso.device.screenshots.screenshotfiles

interface ScreenshotDirectoryProvider {

    fun getDirectoryForTest(testClassName: String, testMethodName: String): String
}