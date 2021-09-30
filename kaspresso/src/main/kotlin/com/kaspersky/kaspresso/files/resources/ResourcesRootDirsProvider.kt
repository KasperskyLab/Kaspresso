package com.kaspersky.kaspresso.files.resources

import java.io.File

/**
 * Provides root directories for resources
 */
interface ResourcesRootDirsProvider {
    val logcatRootDir: File
    val screenshotsRootDir: File
    val videoRootDir: File
    val viewHierarchy: File
}
