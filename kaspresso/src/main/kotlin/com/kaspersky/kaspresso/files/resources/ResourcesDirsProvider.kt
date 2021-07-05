package com.kaspersky.kaspresso.files.resources

import java.io.File

interface ResourcesDirsProvider {
    fun provide(path: File, subDir: String? = null): File
    fun onNewTestRun()
}
