package com.kaspersky.kaspresso.files

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Environment
import androidx.test.platform.app.InstrumentationRegistry
import com.kaspersky.kaspresso.internal.extensions.other.createIfNeeded
import java.io.File

interface DirsProvider {
    fun provideNew(path: File): File
    fun provideCleared(dir: File): File
}
///storage/emulated/0/video/run_1/com.kaspersky.kaspressample.simple_tests.SimpleTest/test/Video_SimpleTest.mp4
class DefaultDirsProvider: DirsProvider {
    private val clearedDirs = HashSet<File>()

    @SuppressLint("WorldReadableFiles")
    override fun provideNew(path: File): File {
        val dir: File = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Environment.getExternalStorageDirectory().resolve(path)
        } else {
            InstrumentationRegistry.getInstrumentation().targetContext.applicationContext.getDir(
                path.canonicalPath,
                Context.MODE_WORLD_READABLE
            )
        }
        return dir.createIfNeeded()
    }

    override fun provideCleared(dir: File): File {
        if (!clearedDirs.contains(dir)) {
            clearDir(path = dir, inclusive = false)
            clearedDirs.add(dir)
        }
        return dir.createIfNeeded()
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
