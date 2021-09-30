package com.kaspersky.kaspresso.device.screenshots.screenshotfiles

/**
 * Provides names for screenshots
 */
@Deprecated(
    "The work with screenshots and relative resource providers was redesigned.\n" +
            "Please migrate to new system of work with resources presented in 'files/resources' folder.\n" +
            "An example of migration is shown in a secondary constructor of 'DocLocScreenshotTestCase'.")
interface ScreenshotNameProvider {

    /**
     * @param tag a tag to identify a screenshot
     */
    fun getScreenshotName(tag: String): String
}
