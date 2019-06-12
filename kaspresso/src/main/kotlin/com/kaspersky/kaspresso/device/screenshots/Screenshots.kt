package com.kaspersky.kaspresso.device.screenshots

/**
 * An interface to make screenshots.
 */
interface Screenshots {

    /**
     * Takes screenshot if it is possible.
     *
     * Required Permissions: WRITE_EXTERNAL_STORAGE
     *
     * @param tag a unique tag to further identify the screenshot. Must match [a-zA-Z0-9_-]+.
     */
    fun take(tag: String)
}