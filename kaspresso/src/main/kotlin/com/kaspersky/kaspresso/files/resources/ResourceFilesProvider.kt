package com.kaspersky.kaspresso.files.resources

import com.kaspersky.kaspresso.files.FileExtension
import com.kaspersky.kaspresso.internal.extensions.other.createFileIfNeeded
import java.io.File

interface ResourceFilesProvider {
    fun provideLogcatFile(tag: String, subDir: String? = null): File
    fun provideScreenshotFile(tag: String, subDir: String? = null): File
    fun provideVideoFile(tag: String, subDir: String? = null): File
    fun provideViewHierarchyFile(tag: String, subDir: String? = null): File
}

class DefaultResourceFilesProvider(
    private val resourcesRootDirsProvider: ResourcesRootDirsProvider,
    private val resourcesDirsProvider: ResourcesDirsProvider,
    private val resourceFileNamesProvider: ResourceFileNamesProvider,
) : ResourceFilesProvider {

    override fun provideLogcatFile(tag: String, subDir: String?): File {
        val resFileName: String = resourceFileNamesProvider.getFileName(tag, FileExtension.TXT.toString())
        return resourcesDirsProvider.provide(resourcesRootDirsProvider.logcatRootDir, subDir).resolve(resFileName)
    }

    override fun provideScreenshotFile(tag: String, subDir: String?): File {
        val resFileName: String = resourceFileNamesProvider.getFileName(tag, FileExtension.PNG.toString())
        return resourcesDirsProvider.provide(resourcesRootDirsProvider.screenshotsRootDir, subDir).resolve(resFileName)
    }

    override fun provideVideoFile(tag: String, subDir: String?): File {
        val resFileName: String = resourceFileNamesProvider.getFileName(tag, FileExtension.MP4.toString())
        return resourcesDirsProvider.provide(resourcesRootDirsProvider.videoRootDir, subDir).resolve(resFileName).createFileIfNeeded()
    }

    override fun provideViewHierarchyFile(tag: String, subDir: String?): File {
        val resFileName: String = resourceFileNamesProvider.getFileName(tag, FileExtension.XML.toString())
        return resourcesDirsProvider.provide(resourcesRootDirsProvider.viewHierarchy, subDir).resolve(resFileName)
    }
}
