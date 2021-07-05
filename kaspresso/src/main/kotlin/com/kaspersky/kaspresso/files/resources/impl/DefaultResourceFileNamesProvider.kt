package com.kaspersky.kaspresso.files.resources.impl

import com.kaspersky.kaspresso.files.resources.ResourceFileNamesProvider

class DefaultResourceFileNamesProvider(
    private val addTimestamps: Boolean
) : ResourceFileNamesProvider {

    override fun getFileName(tag: String, fileExtension: String): String =
        "${if (addTimestamps) "${System.currentTimeMillis()}_" else ""}$tag$fileExtension"
}
