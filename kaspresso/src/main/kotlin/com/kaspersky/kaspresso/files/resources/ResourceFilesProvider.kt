package com.kaspersky.kaspresso.files.resources

import java.io.File

/**
 * Provides files for resources
 */
interface ResourceFilesProvider {
    fun provideLogcatFile(tag: String, subDir: String? = null): File
    fun provideScreenshotFile(tag: String, subDir: String? = null): File
    fun provideVideoFile(tag: String, subDir: String? = null): File
    fun provideViewHierarchyFile(tag: String, subDir: String? = null): File
}
