package com.kaspersky.kaspresso.device.screenshots.screenshoter

import android.content.Context
import android.os.Build
import android.os.Environment
import com.kaspersky.kaspresso.extensions.other.createDirectoryRWX
import java.io.File

object ScreenshotsDirectoryStorage {

    private val clearedOutputDirectories = HashSet<File>()

    fun obtainDirectory(
        context: Context,
        screenshotDir: File
    ): File {
        val screenshotDeviceDir: File = getScreenshotRootDirectory(context, screenshotDir)

        synchronized(this) {
            if (!clearedOutputDirectories.contains(screenshotDeviceDir)) {
                deletePath(screenshotDeviceDir, inclusive = false)
                clearedOutputDirectories.add(screenshotDeviceDir)
            }
        }

        screenshotDeviceDir.createDirectoryRWX()

        return screenshotDeviceDir
    }

    private fun getScreenshotRootDirectory(context: Context, screenshotDir: File): File {
        return if (Build.VERSION.SDK_INT >= 21) {
            // Use external storage.
            Environment.getExternalStorageDirectory().resolve(screenshotDir)
        } else {
            // Use internal storage.
            context.getDir(screenshotDir.canonicalPath, Context.MODE_WORLD_READABLE)
        }
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