package com.kaspersky.components.alluresupport.files.dirs

import android.annotation.SuppressLint
import android.app.Instrumentation
import android.content.Context
import android.os.Build
import android.os.Environment
import com.kaspersky.kaspresso.files.dirs.DefaultDirsProvider
import com.kaspersky.kaspresso.files.resources.ResourcesRootDirsProvider
import com.kaspersky.kaspresso.instrumental.InstrumentalDependencyProvider
import com.kaspersky.kaspresso.internal.extensions.other.createDirIfNeeded
import com.kaspersky.kaspresso.internal.extensions.other.createFileIfNeeded
import java.io.File

class AllureDirsProvider(
    private val instrumentation: Instrumentation,
    private val resourcesRootDirsProvider: ResourcesRootDirsProvider,
    instrumentationDependencyProvider: InstrumentalDependencyProvider
) : DefaultDirsProvider(instrumentationDependencyProvider) {

    @Suppress("DEPRECATION")
    @SuppressLint("WorldReadableFiles", "ObsoleteSdkInt")
    override fun provideNew(dest: File): File {
        if (isVideoDir(dest)) { // screen recorder can't record to /data/data
            return super.provideNew(dest)
        }

        val dir: File = when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q -> instrumentation.targetContext.applicationContext.filesDir.resolve(dest)
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP -> Environment.getExternalStorageDirectory().resolve(dest)
            else -> instrumentation.targetContext.applicationContext.getDir(dest.canonicalPath, Context.MODE_WORLD_READABLE)
        }

        return dir.createDirIfNeeded()
    }

    /**
     * Used for allure report video attachment workaround. Creates stub video file in package private directory so allure could attach it to report
     * @param actualVideoFile vide file saved by screen recorder
     * @return stub video file under /data/data
     */
    fun provideReportVideoAttachmentStub(actualVideoFile: File): File {
        val defaultVideoDir = super.provideNew(resourcesRootDirsProvider.videoRootDir).absolutePath
        val targetVideoDir = instrumentation.targetContext.applicationContext.filesDir.resolve(resourcesRootDirsProvider.videoRootDir).absolutePath
        val targetFilePath = actualVideoFile.absolutePath.replace(defaultVideoDir, targetVideoDir)

        return File(targetFilePath).parentFile!!
            .createDirIfNeeded()
            .resolve(actualVideoFile.name)
            .createFileIfNeeded()
    }

    private fun isVideoDir(dest: File): Boolean {
        return dest == resourcesRootDirsProvider.videoRootDir
    }
}
