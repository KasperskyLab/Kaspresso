package com.kaspersky.kaspresso.device.screenshots.screenshotfiles

/**
 * Default implementation of [ScreenshotFileProvider]
 * If [addTimestamps] is true it adds timestamps to names like that "1570158949869_ScreenshotSampleTest_step_1.png"
 */
@Deprecated(
    "The work with screenshots and relative resource providers was redesigned.\n" +
            "Please migrate to new system of work with resources presented in 'files/resources' folder.\n" +
            "An example of migration is shown in a secondary constructor of 'DocLocScreenshotTestCase'.")
class DefaultScreenshotNameProvider(
    private val addTimestamps: Boolean
) : ScreenshotNameProvider {

    companion object {
        private const val NAME_SEPARATOR = "_"
        private const val EXTENSION = ".png"
    }

    override fun getScreenshotName(tag: String): String {
        return if (addTimestamps) {
            System.currentTimeMillis().toString() + NAME_SEPARATOR + tag + EXTENSION
        } else {
            tag + EXTENSION
        }
    }
}
