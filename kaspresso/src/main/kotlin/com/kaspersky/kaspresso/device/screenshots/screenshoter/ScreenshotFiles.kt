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

    /**
     * Returns a non-existing file for screenshot.
     * Even though the file won't exist after the method call, all the parent directories will created if necessary.
     *
     * A screenshot path is as follows:
     * <device storage>/[screenshotDir]/<test class name>/<test method name>/<nano time>-[tag].png
     *
     * Note:
     * + [screenshotDir] will be created if doesn't exist.
     * + A /<test class name>/<test method name> will be removed on the first method call.
     *   If you have multiple places where you want to use this location,
     *   you MUST share the same instance of this class among the occurrences.
     *
     * @param context a context to get directory on pre-lollipop.
     * @param tag a name for the screenshot
     * @return [File] which represents a file for screenshot
     */
    fun getScreenshotFile(context: Context, tag: String): File {
        val screenshotRootDirectory = directoryStorage.getRootScreenshotDirectory(context, screenshotDir)

        val screenshotTestDirectory = directoryStorage.obtainDirectory(getDirectoryForTest(screenshotRootDirectory))

        val screenshotName = tag + EXTENSION
        return screenshotTestDirectory.resolve(screenshotName)
    }

    private fun getDirectoryForTest(screenshotRootDirectory: File): File {
        val testClass = Thread.currentThread().stackTrace.findTestClassTraceElement()
        val className = testClass.className.replace("[^A-Za-z0-9._-]".toRegex(), NAME_SEPARATOR)
        val methodName = testClass.methodName

        return screenshotRootDirectory.resolve(className).resolve(methodName)
    }
}
