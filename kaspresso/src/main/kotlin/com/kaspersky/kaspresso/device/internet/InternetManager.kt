package com.kaspersky.kaspresso.device.internet

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