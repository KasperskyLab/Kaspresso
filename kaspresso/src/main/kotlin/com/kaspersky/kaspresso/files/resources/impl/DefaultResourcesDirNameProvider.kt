package com.kaspersky.kaspresso.files.resources.impl

import com.kaspersky.kaspresso.files.models.TestMethod
import com.kaspersky.kaspresso.files.resources.ResourcesDirNameProvider
import java.io.File

class DefaultResourcesDirNameProvider(
    private val groupByRunNumbers: Boolean,
) : ResourcesDirNameProvider {

    override fun getResourcesDirName(testMethod: TestMethod, runNumber: Int): String {
        val clearedClassName = testMethod.className.replace("[^A-Za-z0-9._-]".toRegex(), "_")
        val rawDirName = "$clearedClassName${File.separator}${testMethod.methodName}"
        return "${if (groupByRunNumbers) "run_$runNumber${File.separator}" else ""}$rawDirName"
    }
}
