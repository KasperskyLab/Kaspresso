package com.kaspersky.kaspresso.files.resources

import com.kaspersky.kaspresso.files.TestMethod
import com.kaspersky.kaspresso.files.findTestMethod
import com.kaspersky.kaspresso.files.DirsProvider
import java.io.File

interface ResourcesDirsProvider {
    fun provide(dest: File, subDir: String? = null): File
    fun onNewTestRun()
}

class DefaultResourcesDirsProvider(
    private val dirsProvider: DirsProvider,
    private val groupByRunNumbers: Boolean
) : ResourcesDirsProvider {

    private val testRunsCount = mutableMapOf<TestMethod, Int>()

    override fun provide(dest: File, subDir: String?): File {
        val rootDir: File = dirsProvider.provideNew(dest)
        val resourcesDest: File = resolveResourcesDirDest(rootDir, subDir)
        return dirsProvider.provideCleared(resourcesDest)
    }

    override fun onNewTestRun() {
        val testMethod: TestMethod = Thread.currentThread().stackTrace.findTestMethod()
        testRunsCount[testMethod] = (testRunsCount[testMethod] ?: 0) + 1
    }

    private fun resolveResourcesDirDest(rootDir: File, subDir: String? = null): File {
        val testMethod: TestMethod = Thread.currentThread().stackTrace.findTestMethod()
        val runNumber: Int = testRunsCount[testMethod] ?: 1
        val resourcesDirName: String = getResourcesDirName(testMethod, runNumber)
        return when (subDir) {
            null -> rootDir.resolve(resourcesDirName)
            else -> rootDir.resolve(subDir).resolve(resourcesDirName)
        }
    }

    private fun getResourcesDirName(testMethod: TestMethod, runNumber: Int): String {
        val clearedClassName = testMethod.className.replace("[^A-Za-z0-9._-]".toRegex(), "_")
        val rawDirName = "$clearedClassName${File.separator}${testMethod.methodName}"
        return "${if (groupByRunNumbers) "run_$runNumber${File.separator}" else ""}$rawDirName"
    }
}
