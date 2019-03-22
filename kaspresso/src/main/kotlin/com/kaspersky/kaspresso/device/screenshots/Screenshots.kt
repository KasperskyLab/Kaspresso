package com.kaspersky.kaspresso.device.screenshots

/**
 * An interface to make screenshots.
 */
interface Screenshots {

    /**
     * Makes screenshot if it is possible.
     */
    fun makeIfPossible(tag: String)
}