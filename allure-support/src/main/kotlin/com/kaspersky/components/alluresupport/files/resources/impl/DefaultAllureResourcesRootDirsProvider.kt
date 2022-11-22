package com.kaspersky.components.alluresupport.files.resources.impl

import com.kaspersky.components.alluresupport.files.resources.AllureResourcesRootDirsProvider
import java.io.File

class DefaultAllureResourcesRootDirsProvider : AllureResourcesRootDirsProvider {
    override val logcatRootDir = File("logcat")
    override val screenshotsRootDir = File("screenshots")
    override val videoRootDir = File("video")
    override val viewHierarchy = File("view_hierarchy")
    override val allureRootDir = File("allure-results")
}