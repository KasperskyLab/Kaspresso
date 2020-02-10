package com.kaspersky.kaspresso.device.screenshots

/**
 * The interface that allows implementations of [Screenshots] to track test starts.
 */
interface ScreenshotTestStartListener {
    fun onTestStarted()
}