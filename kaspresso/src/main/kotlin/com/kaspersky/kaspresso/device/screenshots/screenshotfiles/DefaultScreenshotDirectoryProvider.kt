package com.kaspersky.kaspresso.device.screenshots.screenshotfiles

import java.io.File

/**
 * Default implementation of [ScreenshotDirectoryProvider]
 * If [groupByRunNumbers] is true it groups screenshots by run numbers of tests. It allows to save all screenshots of a test
 * running several times per the same suite.
 */
@Deprecated(
    "The work with screenshots and relative resource providers was redesigned.\n" +
            "Please migrate to new system of work with resources presented in 'files/resources' folder.\n" +
            "An example of migration is shown in a secondary constructor of 'DocLocScreenshotTestCase'.")
class DefaultScreenshotDirectoryProvider(
    private val groupByRunNumbers: Boolean
) : ScreenshotDirectoryProvider {

    companion object {
        private const val NAME_SEPARATOR = "_"
    }

    override fun getDirectoryForTest(testMethod: TestMethod, runNumber: Int): String {
        val clearedClassName = testMethod.className.replace("[^A-Za-z0-9._-]".toRegex(), NAME_SEPARATOR)
        return if (groupByRunNumbers) {
            "run_$runNumber" + File.separator + clearedClassName + File.separator + testMethod.methodName
        } else {
            clearedClassName + File.separator + testMethod.methodName
        }
    }
}
