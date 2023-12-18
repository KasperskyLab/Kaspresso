package com.kaspersky.components.alluresupport.files.resources

import java.io.File

interface AllureResourcesRootDirsProvider {
    val allureRootDir: File
    val stubVideoDir: File
}
