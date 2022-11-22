package com.kaspersky.components.alluresupport.files.resources

import com.kaspersky.kaspresso.files.resources.ResourcesRootDirsProvider
import java.io.File

interface AllureResourcesRootDirsProvider : ResourcesRootDirsProvider {
    val allureRootDir: File
}