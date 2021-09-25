package com.kaspersky.kaspresso.files.resources

import java.io.File

interface ResourcesDirsProvider {
    fun provide(dest: File, subDir: String? = null): File
    fun onNewTestRun()
}
