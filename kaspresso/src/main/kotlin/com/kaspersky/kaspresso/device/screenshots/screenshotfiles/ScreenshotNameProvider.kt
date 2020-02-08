package com.kaspersky.kaspresso.device.screenshots.screenshotfiles

/**
 * Returns a screenshot name by tag
 */
interface ScreenshotNameProvider {

    fun getScreenshotName(tag: String): String
}