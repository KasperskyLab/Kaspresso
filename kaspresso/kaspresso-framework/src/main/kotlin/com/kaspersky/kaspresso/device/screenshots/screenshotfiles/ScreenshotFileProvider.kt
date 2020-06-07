package com.kaspersky.kaspresso.device.screenshots.screenshotfiles

import java.io.File

internal class ScreenshotFileProvider(
    private val screenshotDirectoryProvider: ScreenshotDirectoryProvider,
    private val screenshotNameProvider: ScreenshotNameProvider,
    private val screenshotRootDir: File
) {
    private val directoryStorage = ScreenshotsDirectoryStorage()

    // stores how many times a given test was started
    private val testRunNumbers = mutableMapOf<TestMethod, Int>()

    /**
     * Returns a non-existing file for screenshot.
     * Even though the file won't exist after the method call, all the parent directories will created if necessary.
     *
     * A screenshot path is formed by [screenshotDirectoryProvider], [screenshotNameProvider] and [screenshotRootDir]
     * Note:
     * + [screenshotRootDir] will be created if doesn't exist.
     * + A directory returned by [screenshotDirectoryProvider] will be cleared before any screenshot has been done there.
     * + Don't use multiple instances of [ScreenshotFileProvider] referencing to the same [screenshotRootDir], because
     * it will cause unexpected clearing of screenshots.
     *
     * @param tag a tag for the screenshot
     * @return [File] which represents a file for screenshot
     */
    fun getScreenshotFile(tag: String, subDirectory: String? = null): File {
        val screenshotRootDirectory = directoryStorage.getRootScreenshotDirectory(screenshotRootDir)
        val screenshotTestDirectory = directoryStorage.obtainDirectory(getDirectoryForTest(screenshotRootDirectory, subDirectory))
        val screenshotName = screenshotNameProvider.getScreenshotName(tag)
        return screenshotTestDirectory.resolve(screenshotName)
    }

    private fun getDirectoryForTest(screenshotRootDirectory: File, subDirectory: String? = null): File {
        val testMethod = Thread.currentThread().stackTrace.findTestMethod()
        val runNumber = testRunNumbers[testMethod] ?: 1
        val directory = screenshotDirectoryProvider.getDirectoryForTest(testMethod, runNumber)
        return if (subDirectory != null) {
            screenshotRootDirectory.resolve(subDirectory).resolve(directory)
        } else {
            screenshotRootDirectory.resolve(directory)
        }
    }

    fun incrementRunNumberOfCurrentTest() {
        val testMethod = Thread.currentThread().stackTrace.findTestMethod()
        testRunNumbers[testMethod] = (testRunNumbers[testMethod] ?: 0) + 1
    }
}
