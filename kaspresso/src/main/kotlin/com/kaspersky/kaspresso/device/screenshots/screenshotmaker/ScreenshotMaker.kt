package com.kaspersky.kaspresso.device.screenshots.screenshotmaker

import java.io.File

interface ScreenshotMaker {

    fun takeScreenshot(file: File)
}