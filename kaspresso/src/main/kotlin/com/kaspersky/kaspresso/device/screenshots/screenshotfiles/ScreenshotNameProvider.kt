package com.kaspersky.kaspresso.device.screenshots.screenshotfiles

/**
 * Provides names for screenshots
 */
interface ScreenshotNameProvider {

    /**
     * @param tag a tag to identify a screenshot
     */
    fun getScreenshotName(tag: String): String
}