package com.kaspersky.components.alluresupport.files.dirs

import android.annotation.SuppressLint
import android.app.Instrumentation
import android.content.Context
import android.os.Build
import android.os.Environment
import com.kaspersky.components.alluresupport.files.resources.AllureResourcesRootDirsProvider
import com.kaspersky.kaspresso.files.dirs.DefaultDirsProvider
import com.kaspersky.kaspresso.instrumental.InstrumentalDependencyProvider
import com.kaspersky.kaspresso.internal.extensions.other.createDirIfNeeded
import java.io.File

open class AllureDirsProvider(
    private val instrumentation: Instrumentation,
    private val resourcesRootDirsProvider: AllureResourcesRootDirsProvider,
    instrumentationDependencyProvider: InstrumentalDependencyProvider
) : DefaultDirsProvider(instrumentationDependencyProvider) {

    @Suppress("DEPRECATION")
    @SuppressLint("WorldReadableFiles", "ObsoleteSdkInt")
    override fun provideNew(dest: File): File {
        if (isRealVideoDir(dest)) { // screen recorder can't record to /data/data
            return super.provideNew(dest)
        }

        val dir: File = when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q -> instrumentation.targetContext.applicationContext.filesDir.resolve(dest)
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP -> Environment.getExternalStorageDirectory().resolve(dest)
            else -> instrumentation.targetContext.applicationContext.getDir(dest.canonicalPath, Context.MODE_WORLD_READABLE)
        }

        return dir.createDirIfNeeded()
    }

    private fun isRealVideoDir(dest: File): Boolean {
        return dest == resourcesRootDirsProvider.videoRootDir
    }
}
