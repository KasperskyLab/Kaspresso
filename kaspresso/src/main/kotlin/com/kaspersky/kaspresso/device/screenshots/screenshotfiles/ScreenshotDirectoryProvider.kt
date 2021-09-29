package com.kaspersky.kaspresso.device.screenshots.screenshotfiles

/**
 * Provides a directory for screenshots of a separate test
 */
@Deprecated(
    "The work with screenshots and relative resource providers was redesigned.\n" +
            "Please migrate to new system of work with resources presented in 'files/resources' folder.\n" +
            "An example of migration is shown in a secondary constructor of 'DocLocScreenshotTestCase'.")
interface ScreenshotDirectoryProvider {

    /**
     * @param testMethod specifies what test is running
     * @param runNumber specifies how many times a given test have already run
     */
    fun getDirectoryForTest(testMethod: TestMethod, runNumber: Int): String
}
