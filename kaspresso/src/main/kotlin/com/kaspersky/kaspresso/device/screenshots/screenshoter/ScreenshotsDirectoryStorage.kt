package com.kaspersky.kaspresso.device.screenshots.screenshoter

import android.content.Context
import android.os.Build
import android.os.Environment
import com.kaspersky.kaspresso.extensions.other.createDirectoryRWX
import java.io.File

class ScreenshotsDirectoryStorage {

    private val clearedOutputDirectories = HashSet<File>()

    fun getRootScreenshotDirectory(
        context: Context,
        screenshotDir: File
    ): File {
        val screenshotDeviceDir: File = getScreenshotDirectory(context, screenshotDir)
        if (!screenshotDeviceDir.exists()) {
            screenshotDeviceDir.createDirectoryRWX()
        }
        return screenshotDeviceDir
    }

    fun obtainDirectory(
        screenshotTestDir: File
    ): File {

        if (!clearedOutputDirectories.contains(screenshotTestDir)) {
            deletePath(screenshotTestDir, inclusive = false)
            clearedOutputDirectories.add(screenshotTestDir)
        }

        if (!screenshotTestDir.exists()) {
            screenshotTestDir.createDirectoryRWX()
        }

        return screenshotTestDir
    }

    private fun getScreenshotDirectory(context: Context, screenshotDir: File): File {
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
                forEach { child -> deletePath(child, inclusive = true) }
            }
        }

        if (inclusive) path.delete()
    }
}