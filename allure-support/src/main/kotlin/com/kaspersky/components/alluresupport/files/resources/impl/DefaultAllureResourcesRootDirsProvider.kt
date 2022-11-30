package com.kaspersky.components.alluresupport.files.resources.impl

import com.kaspersky.components.alluresupport.files.resources.AllureResourcesRootDirsProvider
import com.kaspersky.kaspresso.files.resources.ResourcesRootDirsProvider
import com.kaspersky.kaspresso.files.resources.impl.DefaultResourcesRootDirsProvider
import io.qameta.allure.kotlin.util.PropertiesUtils
import java.io.File

class DefaultAllureResourcesRootDirsProvider :
    AllureResourcesRootDirsProvider,
    ResourcesRootDirsProvider by DefaultResourcesRootDirsProvider() {

    override val allureRootDir = File(PropertiesUtils.resultsDirectoryPath)
    override val stubVideoDir = File("stub_video")
}
