package com.kaspersky.kaspresso.device.screenshots.screenshotfiles

import android.content.Context
import java.io.File

internal class ScreenshotFileProvider(
    private val screenshotDirectoryProvider: ScreenshotDirectoryProvider,
    private val screenshotNameProvider: ScreenshotNameProvider,
    private val screenshotDir: File
) {
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
     * @param tag a name for the screenshot
     * @return [File] which represents a file for screenshot
     */
    fun getScreenshotFile(tag: String, subDirectory: String? = null): File {
        val screenshotRootDirectory = directoryStorage.getRootScreenshotDirectory(screenshotDir)
        val screenshotTestDirectory = directoryStorage.obtainDirectory(getDirectoryForTest(screenshotRootDirectory, subDirectory))
        val screenshotName = screenshotNameProvider.getScreenshotName(tag)
        return screenshotTestDirectory.resolve(screenshotName)
    }

    private fun getDirectoryForTest(screenshotRootDirectory: File, subDirectory: String? = null): File {
        val testClass = Thread.currentThread().stackTrace.findTestClassTraceElement()
        val directory = screenshotDirectoryProvider.getDirectoryForTest(testClass.className, testClass.methodName)
        return if(subDirectory != null) {
            screenshotRootDirectory.resolve(subDirectory).resolve(directory)
        } else {
            screenshotRootDirectory.resolve(directory)
        }
    }
}
