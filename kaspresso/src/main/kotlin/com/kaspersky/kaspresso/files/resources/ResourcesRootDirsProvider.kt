package com.kaspersky.kaspresso.files.resources

import java.io.File

interface ResourcesRootDirsProvider {
    val logcatRootDir: File
    val screenshotsRootDir: File
    val videoRootDir: File
    val viewHierarchy: File
}

class DefaultResourcesRootDirsProvider : ResourcesRootDirsProvider {
    override val logcatRootDir = File("logcat")
    override val screenshotsRootDir = File("screenshots")
    override val videoRootDir = File("video")
    override val viewHierarchy = File("view_hierarchy")
}
