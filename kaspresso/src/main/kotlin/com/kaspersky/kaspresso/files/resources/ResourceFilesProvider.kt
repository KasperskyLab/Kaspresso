package com.kaspersky.kaspresso.files.resources

import java.io.File

interface ResourceFilesProvider {
    fun provideLogcatFile(tag: String, subDir: String? = null): File
    fun provideScreenshotFile(tag: String, subDir: String? = null): File
    fun provideVideoFile(tag: String, subDir: String? = null): File
    fun provideViewHierarchyFile(tag: String, subDir: String? = null): File
}
