package com.kaspersky.kaspresso.device.screenshots

/**
 * An interface to make screenshots.
 */
interface ScreenshotsManager {

    /**
     * Makes screenshot if it is possible.
     */
    fun makeScreenshotIfPossible(tag: String)
}