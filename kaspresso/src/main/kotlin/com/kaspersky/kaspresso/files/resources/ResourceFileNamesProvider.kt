package com.kaspersky.kaspresso.files.resources

interface ResourceFileNamesProvider {
    fun getFileName(tag: String, fileExtension: String): String
}

class DefaultResourceFileNamesProvider(
    private val addTimestamps: Boolean
) : ResourceFileNamesProvider {

    override fun getFileName(tag: String, fileExtension: String): String =
        "${if (addTimestamps) "${System.currentTimeMillis()}_" else ""}$tag$fileExtension"
}
