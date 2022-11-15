package com.kaspersky.kaspresso.files.dirs

import android.annotation.SuppressLint
import android.app.Instrumentation
import android.content.Context
import android.os.Build
import android.os.Environment
import androidx.test.uiautomator.UiDevice
import com.kaspersky.kaspresso.internal.extensions.other.createDirIfNeeded
import java.io.File

open class DefaultDirsProvider(
    private val instrumentation: Instrumentation,
    private val device: UiDevice
) : DirsProvider {
    private val clearedDirs = HashSet<File>()

    @Suppress("DEPRECATION")
    @SuppressLint("WorldReadableFiles", "ObsoleteSdkInt")
    override fun provideNew(dest: File): File {
        val dir = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).resolve(dest)
        } else {
            instrumentation.targetContext.applicationContext.getDir(dest.canonicalPath, Context.MODE_WORLD_READABLE)
        }

        return dir.createDirIfNeeded()
    }

    override fun provideCleared(dest: File): File {
        if (!clearedDirs.contains(dest)) {
            clearDir(dest, inclusive = false)
            clearedDirs.add(dest)
        }
        return dest.createDirIfNeeded()
    }
    @Suppress("SameParameterValue")
    private fun clearDir(dest: File, inclusive: Boolean) {
        clearDirManually(dest, inclusive)
        if (shouldClearDirThroughShell(dest)) {
            clearDirThroughShell(dest, inclusive)
        }
    }

    private fun shouldClearDirThroughShell(dest: File): Boolean {
        return device.executeShellCommand("ls ${dest.absolutePath}").isNotEmpty()
    }

    private fun clearDirThroughShell(dest: File, inclusive: Boolean) {
        if (inclusive) {
            device.executeShellCommand("rm -r ${dest.absolutePath}")
        } else {
            device.executeShellCommand("find ${dest.absolutePath} -type f -delete")
        }
    }

    private fun clearDirManually(path: File, inclusive: Boolean) {
        if (path.isDirectory) {
            path.listFiles()?.forEach { clearDirManually(path = it, inclusive = true) }
        } else {
            path.delete()
        }
        if (inclusive) {
            path.delete()
        }
    }
}
