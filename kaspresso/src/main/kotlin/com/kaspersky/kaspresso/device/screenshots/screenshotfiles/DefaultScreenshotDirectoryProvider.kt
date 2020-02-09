package com.kaspersky.kaspresso.device.screenshots.screenshotfiles

import java.io.File

class DefaultScreenshotDirectoryProvider : ScreenshotDirectoryProvider {

    companion object {
        private const val NAME_SEPARATOR = "_"
    }

    override fun getDirectoryForTest(testMethod: TestMethod): String {
        val clearedClassName = testMethod.className.replace("[^A-Za-z0-9._-]".toRegex(), NAME_SEPARATOR)
        return clearedClassName + File.separator + testMethod.methodName
    }
}