package com.kaspersky.kaspresso.device.screenshots.screenshoter

import android.content.Context
import java.io.File

internal class ScreenshotFiles(
    private val screenshotDir: File
) {

    companion object {
        private const val NAME_SEPARATOR = "_"
        private const val EXTENSION = ".png"
    }

    private val directoryStorage = ScreenshotsDirectoryStorage()

    fun obtainScreenshotFile(context: Context, tag: String): File {
        val screenshotRootDirectory = directoryStorage.getRootScreenshotDirectory(context, screenshotDir)

        val screenshotTestDirectory = directoryStorage.obtainDirectory(getDirectoryForTest(screenshotRootDirectory))

        val screenshotName = System.currentTimeMillis().toString() + NAME_SEPARATOR + tag + EXTENSION
        return screenshotTestDirectory.resolve(screenshotName)
    }

    private fun getDirectoryForTest(screenshotRootDirectory: File): File {
        val testClass = Thread.currentThread().stackTrace.findTestClassTraceElement()
        val className = testClass.className.replace("[^A-Za-z0-9._-]".toRegex(), NAME_SEPARATOR)
        val methodName = testClass.methodName

        return screenshotRootDirectory.resolve(className).resolve(methodName)
    }
}
