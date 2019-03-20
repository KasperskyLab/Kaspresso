package com.kaspersky.kaspresso.device.adb

import com.kaspersky.kaspresso.configurator.Configurator

object Internet {

    /**
     *  Enables wi-fi and mobile data using adb.
     */
    fun enableInternet() =
        Configurator.serverInterface.performAdb("shell svc data enable", "shell svc wifi enable")

    /**
     *  Disables wi-fi and mobile data using adb.
     */
    fun disableInternet() =
        Configurator.serverInterface.performAdb("shell svc data disable", "shell svc wifi disable")
}