package com.kaspersky.kaspresso.device.screenshots.screenshotfiles

interface ScreenshotNameProvider {

    fun getScreenshotName(tag: String): String
}