package com.kaspersky.kaspresso.files.dirs

import android.annotation.SuppressLint
import android.os.Build
import android.os.Environment
import com.kaspersky.kaspresso.instrumental.InstrumentalDependencyProvider
import com.kaspersky.kaspresso.internal.extensions.other.createDirIfNeeded
import java.io.File

class DefaultDirsProvider(
    private val instrumentationDependencyProvider: InstrumentalDependencyProvider
) : DirsProvider {

    private val clearedDirs = HashSet<File>()

    @SuppressLint("WorldReadableFiles", "ObsoleteSdkInt")
    override fun provideNew(dest: File): File {
        val dir = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).resolve(dest)
        } else {
            Environment.getExternalStorageDirectory().resolve(dest)
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
        if (dest.exists() && shouldClearDirThroughShell(dest)) {
            clearDirThroughShell(dest, inclusive)
        }
    }

    private fun shouldClearDirThroughShell(dest: File): Boolean {
        val device = instrumentationDependencyProvider.uiDevice
        return device.executeShellCommand("ls ${dest.absolutePath}").isNotEmpty()
    }

    private fun clearDirThroughShell(dest: File, inclusive: Boolean) {
        val device = instrumentationDependencyProvider.uiDevice
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
