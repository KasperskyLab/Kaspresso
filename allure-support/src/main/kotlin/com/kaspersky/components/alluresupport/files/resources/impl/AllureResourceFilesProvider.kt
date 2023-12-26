package com.kaspersky.components.alluresupport.files.resources.impl

import com.kaspersky.components.alluresupport.files.resources.AllureResourcesRootDirsProvider
import com.kaspersky.kaspresso.files.resources.ResourceFilesProvider
import com.kaspersky.kaspresso.files.resources.ResourcesDirsProvider
import com.kaspersky.kaspresso.files.resources.impl.DefaultResourceFilesProvider
import com.kaspersky.kaspresso.internal.extensions.other.createFileIfNeeded
import java.io.File

class AllureResourceFilesProvider(
    defaultResourceFilesProvider: DefaultResourceFilesProvider,
    private val resourcesRootDirsProvider: AllureResourcesRootDirsProvider,
    private val resourcesDirsProvider: ResourcesDirsProvider
) : ResourceFilesProvider by defaultResourceFilesProvider {
    /**
     * Used for allure report video attachment workaround. Creates stub video file in package private directory so allure could attach it to report
     * @param actualVideoFile vide file saved by screen recorder
     * @return stub video file under /data/data
     */
    fun provideStubVideoFile(actualVideoFile: File): File {
        val resFileName: String = actualVideoFile.name
        return resourcesDirsProvider.provide(resourcesRootDirsProvider.stubVideoDir)
            .resolve(resFileName)
            .createFileIfNeeded()
    }
}
