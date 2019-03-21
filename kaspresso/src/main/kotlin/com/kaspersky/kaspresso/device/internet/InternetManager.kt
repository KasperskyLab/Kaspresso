package com.kaspersky.kaspresso.device.internet

/**
 * An interface to work with internet settings.
 */
interface InternetManager {

    /**
     *  Enables wi-fi and mobile data using adb.
     */
    fun enableInternet()

    /**
     *  Disables wi-fi and mobile data using adb.
     */
    fun disableInternet()

    /**
     * Toggles only wi-fi. Note: it works only if flight mode is off.
     */
    fun toggleWiFi(enable: Boolean)
}