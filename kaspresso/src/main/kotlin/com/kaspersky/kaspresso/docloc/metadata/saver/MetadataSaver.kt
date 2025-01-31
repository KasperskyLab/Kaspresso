package com.kaspersky.kaspresso.docloc.metadata.saver

import java.io.File

interface MetadataSaver {
    fun saveScreenshotMetadata(folderPath: File, name: String)
}
