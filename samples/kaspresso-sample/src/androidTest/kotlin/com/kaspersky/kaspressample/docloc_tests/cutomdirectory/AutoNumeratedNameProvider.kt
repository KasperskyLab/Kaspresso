package com.kaspersky.kaspressample.docloc_tests.cutomdirectory

import com.kaspersky.kaspresso.files.resources.ResourceFileNamesProvider

internal class AutoNumeratedNamesProvider : ResourceFileNamesProvider {
    private var counter = 0
    override fun getFileName(tag: String, fileExtension: String): String = "screenshot#${counter++}_$tag$fileExtension"
}
