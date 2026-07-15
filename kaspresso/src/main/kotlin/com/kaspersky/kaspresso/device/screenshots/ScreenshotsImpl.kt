package com.kaspersky.kaspresso.device.screenshots

import android.graphics.Bitmap
import android.util.Log
import com.kaspersky.kaspresso.device.screenshots.screenshotmaker.ScreenshotMaker
import com.kaspersky.kaspresso.files.dirs.DirsProvider
import com.kaspersky.kaspresso.files.extensions.FileExtension
import com.kaspersky.kaspresso.files.resources.ResourceFileNamesProvider
import com.kaspersky.kaspresso.files.resources.ResourceFilesProvider
import com.kaspersky.kaspresso.files.resources.ResourcesDirsProvider
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso.visual.ScreenshotsComparator
import com.kaspersky.kaspresso.visual.VisualTestParams
import com.kaspersky.kaspresso.visual.VisualTestType
import java.io.File
import java.io.FileOutputStream

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

/**
 * The implementation of the [Screenshots] interface.
 */
class ScreenshotsImpl(
    private val logger: UiTestLogger,
    private val resourceFilesProvider: ResourceFilesProvider,
    private val screenshotMaker: ScreenshotMaker,
    private val screenshotsComparator: ScreenshotsComparator,
    private val visualTestParams: VisualTestParams,
    private val dirsProvider: DirsProvider,
    private val resourceFileNamesProvider: ResourceFileNamesProvider,
    private val resourcesDirsProvider: ResourcesDirsProvider,
) : Screenshots {

    private val originalScreenshotsDir: File
        get() {
            val rootDir = dirsProvider.provideNew(File("")).absolutePath
            return File(rootDir, File(visualTestParams.hostScreenshotsDir).name)
        }

    /**
     * Takes a screenshot if it is possible, otherwise logs the error.
     * By default a screenshot name looks like <device storage>/screenshotRootDir/<test run number>/<test class name>/<test method name>/[tag].png
     * See [ScreenshotFileProvider], [ScreenshotDirectoryProvider] for more details
     *
     * Required Permissions: WRITE_EXTERNAL_STORAGE.
     *
     * @param tag a unique tag to further identify the screenshot. Must match [a-zA-Z0-9_-]+.
     */
    override fun take(tag: String): Unit = doTakeAndApply(tag, isFull = false, block = null)

    override fun takeFullWindow(tag: String) = doTakeAndApply(tag, isFull = true, block = null)

    override fun takeAndApply(tag: String, block: File.() -> Unit): Unit = doTakeAndApply(tag, isFull = false, block = block)

    override fun takeFullWindowAndApply(tag: String, block: File.() -> Unit) = doTakeAndApply(tag, isFull = true, block = block)

    override fun assert(tag: String, isFullWindow: Boolean) = assertImpl(tag, isFullWindow, block = null)

    override fun assert(tag: String, bitmap: Bitmap) = assertImpl(tag, bitmap, block = null)

    override fun assertAndApply(tag: String, isFullWindow: Boolean, block: File.() -> Unit) = assertImpl(tag, isFullWindow, block)

    override fun assertAndApply(tag: String, bitmap: Bitmap, block: File.() -> Unit) = assertImpl(tag, bitmap, block)

    private fun assertImpl(tag: String, isFullWindow: Boolean, block: (File.() -> Unit)?) {
        logger.i("Assert screenshot with tag: $tag")
        lateinit var screenshot: File
        val targetPath = if (visualTestParams.testType == VisualTestType.Compare) {
            null
        } else {
            originalScreenshotsDir.mkdirs()
            resourcesDirsProvider.provide(originalScreenshotsDir).resolve(resourceFileNamesProvider.getFileName(tag, FileExtension.PNG.toString()))
        }
        doTakeAndApply(tag = tag, isFull = isFullWindow, targetPath) { screenshot = this }

        if (visualTestParams.testType == VisualTestType.Compare) {
            screenshot.compare()
        }

        block?.invoke(screenshot)
    }

    private fun assertImpl(tag: String, bitmap: Bitmap, block: (File.() -> Unit)?) {
        logger.i("Assert screenshot with tag: $tag")
        val screenshot = provideScreenshotFileForAssert(tag)
        saveBitmap(bitmap, screenshot)

        if (visualTestParams.testType == VisualTestType.Compare) {
            screenshot.compare()
        }

        block?.invoke(screenshot)
    }

    private fun provideScreenshotFileForAssert(tag: String): File {
        return if (visualTestParams.testType == VisualTestType.Compare) {
            resourceFilesProvider.provideScreenshotFile(tag)
        } else {
            originalScreenshotsDir.mkdirs()
            resourcesDirsProvider.provide(originalScreenshotsDir)
                .resolve(resourceFileNamesProvider.getFileName(tag, FileExtension.PNG.toString()))
        }
    }

    private fun saveBitmap(bitmap: Bitmap, screenshotFile: File) {
        screenshotFile.parentFile?.mkdirs()
        FileOutputStream(screenshotFile).use { outputStream ->
            val isSaved = bitmap.compress(Bitmap.CompressFormat.PNG, QUALITY, outputStream)
            check(isSaved) { "Failed to save bitmap screenshot to ${screenshotFile.absolutePath}" }
        }
        logger.i("Screenshot saved to $screenshotFile")
    }

    private fun File.compare() {
        logger.i("Comparing screenshot ${this.absolutePath}")
        val originalScreenshot = resourcesDirsProvider.provide(originalScreenshotsDir, provideCleared = false)
            .resolve(resourceFileNamesProvider.getFileName(nameWithoutExtension, FileExtension.PNG.toString()))
        assert(originalScreenshot.exists()) {
            "Tried to assert screenshot $absolutePath, but failed to find matching " +
                    "original screenshot by the path: ${originalScreenshot.absolutePath}/$name"
        }
        val doesMatch = screenshotsComparator.compare(originalScreenshot, this)
        logger.i("Does screenshot $name matches the original one: $doesMatch")
        if (!doesMatch) {
            throw ScreenshotDoesntMatchException("Screenshot $name doesn't match the original one")
        }
    }

    private fun doTakeAndApply(tag: String, isFull: Boolean, targetPath: File? = null, block: (File.() -> Unit)?) {
        try {
            val screenshotFile = targetPath ?: resourceFilesProvider.provideScreenshotFile(tag)
            if (isFull) {
                screenshotMaker.takeFullWindowScreenshot(screenshotFile)
            } else {
                screenshotMaker.takeScreenshot(screenshotFile)
            }
            block?.invoke(screenshotFile)
            logger.i("Screenshot saved to $screenshotFile")
        } catch (e: Throwable) {
            logger.e("An error while making screenshot occurred: ${Log.getStackTraceString(e)}")
        }
    }

    class ScreenshotDoesntMatchException(message: String) : Exception(message)

    companion object {
        private const val QUALITY = 100
    }
}
