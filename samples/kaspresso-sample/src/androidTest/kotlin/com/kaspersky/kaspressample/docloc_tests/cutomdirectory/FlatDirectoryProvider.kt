package com.kaspersky.kaspressample.docloc_tests.cutomdirectory

import com.kaspersky.kaspresso.files.DirsProvider
import com.kaspersky.kaspresso.files.resources.DefaultResourcesDirsProvider
import com.kaspersky.kaspresso.files.resources.ResourcesDirsProvider
import java.io.File

internal class FlatDirectoryProvider(
    private val dirsProvider: DirsProvider
) : ResourcesDirsProvider by DefaultResourcesDirsProvider(
    dirsProvider = dirsProvider,
    groupByRunNumbers = false
) {
    override fun provide(dest: File, subDir: String?): File {
        val rootDir: File = dirsProvider.provideNew(dest)
        val resultsDir: File = when (subDir) {
            null -> rootDir.resolve("")
            else -> rootDir.resolve(subDir).resolve("")
        }
        return dirsProvider.provideCleared(resultsDir)
    }
}
