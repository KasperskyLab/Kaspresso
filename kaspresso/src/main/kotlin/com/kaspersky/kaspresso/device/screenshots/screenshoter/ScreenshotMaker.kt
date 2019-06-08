package com.kaspersky.kaspresso.device.screenshots.screenshoter

import android.content.Context
import android.os.Build
import android.os.Environment
import com.kaspersky.kaspresso.device.screenshots.screenshoter.external.Chmod
import java.io.File
import java.util.*

internal abstract class ScreenshotMaker(
    private val screenshotDir: File
) {

    companion object {
        private const val NAME_SEPARATOR = "_"

        private val LOCK = Any()
        private const val EXTENSION = ".png"
    }

    /**
     * Holds a set of directories that have been cleared for this test.
     */
    private val clearedOutputDirectories = HashSet<File>()

    fun obtainScreenshotFile(context: Context, tag: String): File {
        val testClass = Thread.currentThread().stackTrace.findTestClassTraceElement()
        val className = testClass.className.replace("[^A-Za-z0-9._-]".toRegex(), NAME_SEPARATOR)
        val methodName = testClass.methodName

        val screenshotDirectory = filesDirectory(context, className, methodName)

        val screenshotName = System.currentTimeMillis().toString() + NAME_SEPARATOR + tag + EXTENSION
        return screenshotDirectory.resolve(screenshotName)
    }

    private fun getDeviceDirectory(context: Context): File {
        return if (Build.VERSION.SDK_INT >= 21) {
            // Use external storage.
            Environment.getExternalStorageDirectory().resolve(screenshotDir)
        } else {
            // Use internal storage.
            context.getDir(screenshotDir.canonicalPath, Context.MODE_WORLD_READABLE)
        }
    }

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

    private fun createDir(dir: File) {
        val parent = dir.parentFile

        if (!parent.exists()) {
            createDir(parent)
        }

        if (!dir.exists() && !dir.mkdirs()) {
            throw IllegalAccessException("Unable to create output dir: " + dir.absolutePath)
        }

        Chmod.chmodPlusRWX(dir)
    }

    private fun deletePath(path: File, inclusive: Boolean) {
        if (path.isDirectory) {
            path.listFiles()?.run {
                forEach { child -> deletePath(child, true) }
            }
        }

        if (inclusive) path.delete()
    }
}