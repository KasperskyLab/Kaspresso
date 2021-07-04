package com.kaspersky.kaspresso.files.resources

import java.io.File

interface ResourcesRootDirsProvider {
    val logcatRootDir: File
    val screenshotsRootDir: File
    val videoRootDir: File
    val viewHierarchy: File
}
