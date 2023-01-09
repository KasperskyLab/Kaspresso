package com.kaspersky.components.alluresupport.files.dirs

import android.annotation.SuppressLint
import android.app.Instrumentation
import com.kaspersky.kaspresso.files.dirs.DefaultDirsProvider
import com.kaspersky.kaspresso.files.dirs.DirsProvider
import com.kaspersky.kaspresso.files.resources.ResourcesRootDirsProvider
import com.kaspersky.kaspresso.internal.extensions.other.createDirIfNeeded
import java.io.File

class AllureDirsProvider(
    private val defaultDirsProvider: DefaultDirsProvider,
    private val instrumentation: Instrumentation,
    private val resourcesRootDirsProvider: ResourcesRootDirsProvider,
) : DirsProvider by defaultDirsProvider {

    @SuppressLint("WorldReadableFiles", "ObsoleteSdkInt")
    override fun provideNew(dest: File): File {
        if (isRealVideoDir(dest)) { // screen recorder can't record to /data/data
            return provideNewOnSdCard(dest)
        }

        return instrumentation.targetContext.applicationContext.filesDir
            .resolve(dest)
            .createDirIfNeeded()
    }

    fun provideNewOnSdCard(dest: File): File = defaultDirsProvider.provideNew(dest)

    private fun isRealVideoDir(dest: File): Boolean {
        return dest == resourcesRootDirsProvider.videoRootDir
    }
}
