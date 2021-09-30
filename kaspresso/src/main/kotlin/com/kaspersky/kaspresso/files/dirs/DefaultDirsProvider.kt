package com.kaspersky.kaspresso.files.dirs

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Environment
import androidx.test.platform.app.InstrumentationRegistry
import com.kaspersky.kaspresso.internal.extensions.other.createDirIfNeeded
import java.io.File

class DefaultDirsProvider : DirsProvider {
    private val clearedDirs = HashSet<File>()

    @Suppress("DEPRECATION")
    @SuppressLint("WorldReadableFiles", "ObsoleteSdkInt")
    override fun provideNew(dest: File): File {
        val dir: File = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Environment.getExternalStorageDirectory().resolve(dest)
        } else {
            InstrumentationRegistry.getInstrumentation().targetContext.applicationContext.getDir(
                dest.canonicalPath,
                Context.MODE_WORLD_READABLE
            )
        }
        return dir.createDirIfNeeded()
    }

    override fun provideCleared(dest: File): File {
        if (!clearedDirs.contains(dest)) {
            clearDir(path = dest, inclusive = false)
            clearedDirs.add(dest)
        }
        return dest.createDirIfNeeded()
    }

    private fun clearDir(path: File, inclusive: Boolean) {
        if (path.isDirectory) {
            path.listFiles()?.forEach { clearDir(path = it, inclusive = true) }
        }
        if (inclusive) {
            path.delete()
        }
    }
}
