package com.kaspersky.components.alluresupport.files.resources.impl

import com.kaspersky.components.alluresupport.files.resources.AllureResourcesRootDirsProvider
import io.qameta.allure.kotlin.util.PropertiesUtils
import java.io.File

class DefaultAllureResourcesRootDirsProvider : AllureResourcesRootDirsProvider {
    override val logcatRootDir = File("logcat")
    override val screenshotsRootDir = File("screenshots")
    override val videoRootDir = File("video")
    override val viewHierarchy = File("view_hierarchy")
    override val allureRootDir = File(PropertiesUtils.resultsDirectoryPath)
    override val stubVideoDir = File("stub-video")
}