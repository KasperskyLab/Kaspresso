package com.kaspersky.kaspresso.files.resources

import java.io.File

/**
 * Provides directories for resources
 */
interface ResourcesDirsProvider {
    fun provide(dest: File, subDir: String? = null): File
}
