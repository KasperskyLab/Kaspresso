package com.kaspersky.components.alluresupport.files.resources

import com.kaspersky.components.alluresupport.files.dirs.AllureDirsProvider
import com.kaspersky.kaspresso.files.resources.ResourceFileNamesProvider
import com.kaspersky.kaspresso.files.resources.ResourcesDirsProvider
import com.kaspersky.kaspresso.files.resources.impl.DefaultResourceFilesProvider
import com.kaspersky.kaspresso.internal.extensions.other.createFileIfNeeded
import java.io.File

open class AllureResourceFilesProvider(
    private val resourcesRootDirsProvider: AllureResourcesRootDirsProvider,
    resourcesDirsProvider: ResourcesDirsProvider,
    resourceFileNamesProvider: ResourceFileNamesProvider,
    private val dirsProvider: AllureDirsProvider
) : DefaultResourceFilesProvider(resourcesRootDirsProvider, resourcesDirsProvider, resourceFileNamesProvider) {
    /**
     * Used for allure report video attachment workaround. Creates stub video file in package private directory so allure could attach it to report
     * @param actualVideoFile vide file saved by screen recorder
     * @return stub video file under /data/data
     */
    fun provideStubVideoFile(actualVideoFile: File): File {
        val stubFile = dirsProvider.provideNew(resourcesRootDirsProvider.stubVideoDir).resolve(actualVideoFile.name)
        return stubFile.createFileIfNeeded()
    }
}