package com.kaspersky.kaspresso.device.screenshots.screenshotfiles

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