package com.kaspersky.kaspresso.device.screenshots.screenshoter

import android.content.Context
import com.kaspersky.kaspresso.extensions.other.createDirectoryRWX
import java.io.File

internal class ScreenshotFiles(
    private val screenshotDir: File
) {

    companion object {
        private const val NAME_SEPARATOR = "_"
        private const val EXTENSION = ".png"
    }

    fun obtainScreenshotFile(context: Context, tag: String): File {
        val screenshotRootDirectory = ScreenshotsDirectoryStorage.obtainDirectory(context, screenshotDir)

        val screenshotTestDirectory = createDirectoryForTest(screenshotRootDirectory)

        val screenshotName = System.currentTimeMillis().toString() + NAME_SEPARATOR + tag + EXTENSION
        return screenshotTestDirectory.resolve(screenshotName)
    }

    private fun createDirectoryForTest(screenshotRootDirectory: File): File {
        val testClass = Thread.currentThread().stackTrace.findTestClassTraceElement()
        val className = testClass.className.replace("[^A-Za-z0-9._-]".toRegex(), NAME_SEPARATOR)
        val methodName = testClass.methodName

        val screenshotTestDirectory = screenshotRootDirectory.resolve(className).resolve(methodName)
        screenshotTestDirectory.createDirectoryRWX()
        return screenshotTestDirectory
    }
}
