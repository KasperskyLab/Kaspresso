package com.kaspersky.kaspressample.docloc_tests.cutomdirectory

import com.kaspersky.kaspresso.device.screenshots.screenshotfiles.ScreenshotNameProvider

internal class AutoNumeratedNameProvider : ScreenshotNameProvider {

    private var counter = 0

    override fun getScreenshotName(tag: String): String = "screenshot#${counter++}_$tag"
}
