package com.kaspersky.kaspresso.files.resources.impl

import com.kaspersky.kaspresso.files.dirs.DirsProvider
import com.kaspersky.kaspresso.files.extensions.findTestMethod
import com.kaspersky.kaspresso.files.models.TestMethod
import com.kaspersky.kaspresso.files.resources.ResourcesDirNameProvider
import com.kaspersky.kaspresso.files.resources.ResourcesDirsProvider
import com.kaspersky.kaspresso.internal.extensions.other.createDirIfNeeded
import java.io.File

class DefaultResourcesDirsProvider(
    private val dirsProvider: DirsProvider,
    private val resourcesDirNameProvider: ResourcesDirNameProvider,
    private val testThread: Thread = Thread.currentThread()
) : ResourcesDirsProvider {

    override fun provide(dest: File, subDir: String?, provideCleared: Boolean): File {
        val rootDir: File = dirsProvider.provideNew(dest)
        val resourcesDest: File = resolveResourcesDirDest(rootDir, subDir)
        return if (provideCleared) {
            dirsProvider.provideCleared(resourcesDest)
        } else {
            resourcesDest.createDirIfNeeded()
        }
    }

    private fun resolveResourcesDirDest(rootDir: File, subDir: String? = null): File {

        return rootDir.run {
            var resourceDir = this
            val testMethod: TestMethod? = testThread.stackTrace.findTestMethod()

            subDir?.let { resourceDir = resourceDir.resolve(it) }
            testMethod?.let {
                val resourcesDirName: String = resourcesDirNameProvider.provideResourcesDirName(testMethod)
                resourceDir = resourceDir.resolve(resourcesDirName)
            }

            resourceDir
        }
    }
}
