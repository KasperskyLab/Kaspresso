package com.kaspersky.kaspresso.device.adb

import com.kaspersky.kaspresso.configurator.Configurator

object Files {

    /**
     *  Performs adb push.
     *
     *  @param serverPath file path relative to the server directory.
     *  @param devicePath path to copy.
     */
    fun push(serverPath: String, devicePath: String) =
        Configurator.serverInterface.performAdb("push $serverPath $devicePath")
}