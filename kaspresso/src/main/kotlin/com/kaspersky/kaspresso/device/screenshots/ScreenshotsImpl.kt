package com.kaspersky.kaspresso.device.screenshots

import android.graphics.BitmapFactory
import android.util.Log
import com.kaspersky.kaspresso.device.files.Files
import com.kaspersky.kaspresso.device.screenshots.screenshotmaker.ScreenshotMaker
import com.kaspersky.kaspresso.device.server.AdbServer
import com.kaspersky.kaspresso.files.dirs.DirsProvider
import com.kaspersky.kaspresso.files.resources.ResourceFilesProvider
import com.kaspersky.kaspresso.files.resources.ResourcesDirsProvider
import com.kaspersky.kaspresso.files.resources.ResourcesRootDirsProvider
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso.visual.ScreenshotsComparator
import com.kaspersky.kaspresso.visual.VisualTestParams
import com.kaspersky.kaspresso.visual.VisualTestType
import java.io.File

/**
 * The implementation of the [Screenshots] interface.
 */
class ScreenshotsImpl(
    private val logger: UiTestLogger,
    private val resourceFilesProvider: ResourceFilesProvider,
    private val screenshotMaker: ScreenshotMaker,
    private val screenshotsComparator: ScreenshotsComparator,
    private val visualTestParams: VisualTestParams,
    private val resourcesRootDirsProvider: ResourcesRootDirsProvider,
    private val resourcesDirsProvider: ResourcesDirsProvider,
    private val dirsProvider: DirsProvider,
    private val adbServer: AdbServer,
    private val files: Files,
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
    override fun take(tag: String): Unit = doTakeAndApply(tag, false, null)

    override fun takeFullWindow(tag: String) = doTakeAndApply(tag, true, null)

    override fun takeAndApply(tag: String, block: File.() -> Unit): Unit = doTakeAndApply(tag, false, block)

    override fun takeFullWindowAndApply(tag: String, block: File.() -> Unit) = doTakeAndApply(tag, true, block)

    override fun assert(tag: String) {
        logger.i("Assert screenshot with tag: $tag")
        lateinit var screenshot: File
        takeAndApply(tag) { screenshot = this}

        if (visualTestParams.testType == VisualTestType.Compare) {
            screenshot.compare()
        } else {
            screenshot.save()
        }
    }

    private fun File.compare() {
        val originalScreenshot = File(originalScreenshotsDir, name)
        assert(originalScreenshot.exists()) {
            "Tried to assert screenshot $name, but failed to find matching " +
                    "original screenshot by the path: ${originalScreenshotsDir.absolutePath}/$name"
        }
        val doesMatch = screenshotsComparator.compare(originalScreenshot, this)
        logger.i("Does screenshot $name matches the original one: $doesMatch")
        if (!doesMatch) {
            throw Exception("Screenshot $name doesn't match the original one")
        }
    }

    private fun File.save() {
        val bitmapOptions = BitmapFactory.Options()
        bitmapOptions.inJustDecodeBounds = true
        BitmapFactory.decodeFile(absolutePath, bitmapOptions)
        adbServer.performCmd("mkdir", listOf("-p", visualTestParams.hostScreenshotsDir))
        adbServer.performAdb("pull", listOf(absolutePath, "${visualTestParams.hostScreenshotsDir}/${name}"))
        files.remove(absolutePath)
    }

    private fun doTakeAndApply(tag: String, isFull: Boolean, block: (File.() -> Unit)?) {
        try {
            val screenshotFile: File = resourceFilesProvider.provideScreenshotFile(tag)
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
}
