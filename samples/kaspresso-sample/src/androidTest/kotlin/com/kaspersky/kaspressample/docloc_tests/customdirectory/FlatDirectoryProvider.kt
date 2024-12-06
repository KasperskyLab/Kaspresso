package com.kaspersky.kaspressample.docloc_tests.customdirectory

import com.kaspersky.kaspresso.files.dirs.DirsProvider
import com.kaspersky.kaspresso.files.resources.impl.DefaultResourcesDirsProvider
import com.kaspersky.kaspresso.files.resources.ResourcesDirsProvider
import com.kaspersky.kaspresso.files.resources.impl.DefaultResourcesDirNameProvider
import com.kaspersky.kaspresso.internal.extensions.other.createDirIfNeeded
import java.io.File

internal class FlatDirectoryProvider(
    private val dirsProvider: DirsProvider
) : ResourcesDirsProvider by DefaultResourcesDirsProvider(
    dirsProvider = dirsProvider,
    resourcesDirNameProvider = DefaultResourcesDirNameProvider()
) {
    override fun provide(dest: File, subDir: String?, provideCleared: Boolean): File {
        val rootDir: File = dirsProvider.provideNew(dest)
        val resultsDir: File = when (subDir) {
            null -> rootDir.resolve("")
            else -> rootDir.resolve(subDir).resolve("")
        }
        return if (provideCleared) {
            dirsProvider.provideCleared(resultsDir)
        } else {
            resultsDir.createDirIfNeeded()
        }
    }
}
