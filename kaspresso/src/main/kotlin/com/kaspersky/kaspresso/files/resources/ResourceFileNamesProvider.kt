package com.kaspersky.kaspresso.files.resources

interface ResourceFileNamesProvider {
    fun getFileName(tag: String, fileExtension: String): String
}
