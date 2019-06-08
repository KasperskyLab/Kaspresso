package com.kaspersky.kaspresso.device.screenshots.screenshoter.external

import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.MODE_WORLD_READABLE
import android.os.Build
import android.os.Environment.getExternalStorageDirectory
import android.support.test.InstrumentationRegistry
import android.support.test.uiautomator.UiDevice
import com.kaspersky.kaspresso.device.screenshots.screenshoter.ScreenshotMaker
import com.kaspersky.kaspresso.device.screenshots.screenshoter.external.Chmod.chmodPlusRWX
import com.kaspersky.kaspresso.logger.UiTestLogger
import java.io.File
import java.io.IOException
import java.util.*
import java.util.regex.Pattern

/**
 * Class for capturing spoon-compatible screenshots by uiautomator.
 */
internal class ExternalScreenshotMaker(
    screenshotDir: File,
    logger: UiTestLogger
) : ScreenshotMaker(screenshotDir, logger) {

    companion object {

        private const val NAME_SEPARATOR = "_"

        private const val EXTENSION = ".png"
        private const val TAG = "ExternalScreenshotMaker"

        private val LOCK = Any()
        private val TAG_VALIDATION = Pattern.compile("[a-zA-Z0-9_-]+")
    }

    /**
     * Holds a set of directories that have been cleared for this test.
     */
    private val clearedOutputDirectories = HashSet<File>()

    /**
     * Takes a screenshot with the specified tag.
     *
     * @param tag Unique tag to further identify the screenshot. Must match [a-zA-Z0-9_-]+.
     * @return the image file that was created
     */
    fun screenshot(tag: String): File {
        val screenshotFile = obtainScreenshotFile(
            InstrumentationRegistry.getTargetContext().applicationContext,
            tag
        )
        takeScreenshot(screenshotFile)
        return screenshotFile
    }

    /**
     * Takes a screenshot with the specified tag.  This version allows the caller to manually specify
     * the test class name and method name.  This is necessary when the screenshot is not called in
     * the traditional manner.
     *.
     * @param tag Unique tag to further identify the screenshot. Must match [a-zA-Z0-9_-]+.
     * @return the image file that was created
     */
    @Suppress("UNUSED")
    fun screenshot(
        tag: String, testClassName: String,
        testMethodName: String
    ): File {
        validateScreenshotTag(tag)
        try {
            val screenshotDirectory =
                obtainScreenshotDirectory(
                    InstrumentationRegistry.getTargetContext().applicationContext,
                    testClassName,
                    testMethodName
                )

            val screenshotName = System.currentTimeMillis().toString() + NAME_SEPARATOR + tag + EXTENSION
            val screenshotFile = screenshotDirectory.resolve(screenshotName)

            takeScreenshot(screenshotFile)

            logger.d(TAG, "Captured screenshot '$tag'.")
            return screenshotFile
        } catch (e: Exception) {
            throw RuntimeException("Unable to capture screenshot.", e)
        }
    }

    /**
     * Takes a screenshot with the specified name. This version allows the caller to manually specify
     * the output directory.  This is necessary when the screenshot is not called in
     * the traditional manner.
     *.
     * @param name Unique name to further identify the screenshot. Must match [a-zA-Z0-9_-]+.
     * @param screenshotDirectory Output directory, which will be resolved with the [screenshotDir]
     * @return the image file that was created
     */
    fun screenshot(name: String, screenshotDirectory: File): File {
        validateScreenshotTag(name)
        val screenshotFile = screenshotDirectory.resolve(name + EXTENSION)
        try {
            createDir(screenshotDirectory)
            takeScreenshot(screenshotFile)
            logger.d(TAG, "Captured screenshot '$name'.")
            return screenshotFile
        } catch (e: Exception) {
            throw RuntimeException("Unable to capture screenshot.", e)
        }
    }

    private fun validateScreenshotTag(tag: String) {
        if (!TAG_VALIDATION.matcher(tag).matches()) {
            throw IllegalArgumentException("Tag must match " + TAG_VALIDATION.pattern() + ". : $tag")
        }
    }

    @Throws(IOException::class)
    private fun takeScreenshot(file: File) {
        UiDevice.getInstance(InstrumentationRegistry.getInstrumentation()).takeScreenshot(file)
    }

    @Throws(IllegalAccessException::class)
    private fun obtainScreenshotDirectory(
        context: Context, testClassName: String,
        testMethodName: String
    ): File {
        return filesDirectory(
            context,
            testClassName,
            testMethodName
        )
    }

    /**
     * Clears spoon-screenshots folder.
     */
    @Throws(IOException::class)
    @Suppress("UNUSED")
    fun clearScreenshotsFolder(): Boolean {
        val directory: File = getDeviceDirectory(InstrumentationRegistry.getTargetContext())
        return if (directory.exists()) {
            directory.deleteRecursively()
        } else {
            true
        }
    }

    @SuppressLint("WorldReadableFiles")
    @Throws(IllegalAccessException::class)
    private fun filesDirectory(
        context: Context, testClassName: String,
        testMethodName: String
    ): File {
        val directory: File = getDeviceDirectory(context)

        synchronized(LOCK) {
            if (!clearedOutputDirectories.contains(directory)) {
                deletePath(directory, inclusive = false)
                clearedOutputDirectories.add(directory)
            }
        }

        val dirClass = directory.resolve(testClassName)
        val dirMethod = dirClass.resolve(testMethodName)
        createDir(dirMethod)

        return dirMethod
    }

    private fun getDeviceDirectory(context: Context): File {
        return if (Build.VERSION.SDK_INT >= 21) {
            // Use external storage.
            getExternalStorageDirectory().resolve(screenshotDir)
        } else {
            // Use internal storage.
            context.getDir(screenshotDir.canonicalPath, MODE_WORLD_READABLE)
        }
    }

    @Throws(IllegalAccessException::class)
    private fun createDir(dir: File) {
        val parent = dir.parentFile

        if (!parent.exists()) {
            createDir(parent)
        }

        if (!dir.exists() && !dir.mkdirs()) {
            throw IllegalAccessException("Unable to create output dir: " + dir.absolutePath)
        }

        chmodPlusRWX(dir)
    }

    private fun deletePath(path: File, inclusive: Boolean) {
        if (path.isDirectory) {
            val children = path.listFiles()

            if (children != null) {
                for (child in children) {
                    deletePath(child, true)
                }
            }
        }

        if (inclusive) {
            path.delete()
        }
    }
}
