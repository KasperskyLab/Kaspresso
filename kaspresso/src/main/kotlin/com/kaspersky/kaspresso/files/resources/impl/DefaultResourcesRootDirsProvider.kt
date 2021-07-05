package com.kaspersky.kaspresso.files.resources.impl

import com.kaspersky.kaspresso.files.resources.ResourcesRootDirsProvider
import java.io.File

class DefaultResourcesRootDirsProvider : ResourcesRootDirsProvider {
    override val logcatRootDir = File("logcat")
    override val screenshotsRootDir = File("screenshots")
    override val videoRootDir = File("video")
    override val viewHierarchy = File("view_hierarchy")
}
