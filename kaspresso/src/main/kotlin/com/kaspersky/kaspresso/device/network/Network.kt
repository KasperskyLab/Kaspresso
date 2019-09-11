package com.kaspersky.kaspresso.device.network

/**
 * The interface to work with network settings.
 */
interface Network {

    /**
     * Enables wi-fi and mobile data using adb.
     *
     * Required Permissions: INTERNET.
     */
    fun enable()

    /**
     * Disables wi-fi and mobile data using adb.
     *
     * Required Permissions: INTERNET.
     */
    fun disable()

    /**
     * Toggles only wi-fi. Note: it works only if flight mode is off.
     */
    fun toggleWiFi(enable: Boolean)
}