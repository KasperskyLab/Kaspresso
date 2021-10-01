package com.kaspersky.kaspresso.files.resources.impl

import com.kaspersky.kaspresso.files.models.TestMethod
import com.kaspersky.kaspresso.files.resources.ResourcesDirNameProvider
import java.io.File

class DefaultResourcesDirNameProvider : ResourcesDirNameProvider {

    override fun provideResourcesDirName(testMethod: TestMethod): String {
        val clearedClassName = testMethod.className.replace("[^A-Za-z0-9._-]".toRegex(), "_")
        return "$clearedClassName${File.separator}${testMethod.methodName}"
    }
}
