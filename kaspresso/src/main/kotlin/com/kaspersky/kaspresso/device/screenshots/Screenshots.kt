package com.kaspersky.kaspresso.device.screenshots

import java.io.File

/**
 * The interface to make screenshots.
 */
interface Screenshots {

    /**
     * Takes screenshot if it is possible.
     *
     * Required Permissions: WRITE_EXTERNAL_STORAGE.
     *
     * @param tag a unique tag to further identify the screenshot. Must match [a-zA-Z0-9_-]+.
     */
    fun take(tag: String)

    /**
     * Takes full screenshot if it is possible.
     *
     * Required Permissions: WRITE_EXTERNAL_STORAGE.
     *
     * @param tag a unique tag to further identify the screenshot. Must match [a-zA-Z0-9_-]+.
     */
    fun takeFullWindow(tag: String)

    /**
     * Takes screenshot if it is possible and applies a function on the file.
     *
     * Required Permissions: WRITE_EXTERNAL_STORAGE.
     *
     * @param tag a unique tag to further identify the screenshot. Must match [a-zA-Z0-9_-]+.
     */
    fun takeAndApply(tag: String, block: File.() -> Unit)

    /**
     * Takes full screenshot if it is possible and applies a function on the file.
     *
     * Required Permissions: WRITE_EXTERNAL_STORAGE.
     *
     * @param tag a unique tag to further identify the screenshot. Must match [a-zA-Z0-9_-]+.
     */
    fun takeFullWindowAndApply(tag: String, block: File.() -> Unit)
}
