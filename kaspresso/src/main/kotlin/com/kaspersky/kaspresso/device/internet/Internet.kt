package com.kaspersky.kaspresso.device.internet

/**
 * An interface to work with internet settings.
 */
interface Internet {

    /**
     *  Enables wi-fi and mobile data using adb.
     *
     *  Required Permissions: INTERNET.
     */
    fun enable()

    /**
     *  Disables wi-fi and mobile data using adb.
     *
     *  Required Permissions: INTERNET.
     */
    fun disable()

    /**
     * Toggles only wi-fi. Note: it works only if flight mode is off.
     */
    fun toggleWiFi(enable: Boolean)
}