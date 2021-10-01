package com.kaspersky.kaspresso.files.resources

/**
 * Provides names for resources
 */
interface ResourceFileNamesProvider {
    fun getFileName(tag: String, fileExtension: String): String
}
