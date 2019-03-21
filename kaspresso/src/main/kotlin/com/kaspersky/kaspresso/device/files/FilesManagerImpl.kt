package com.kaspersky.kaspresso.device.files

import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.device.server.AdbServer

/**
 * Default implementation of FilesManager interface.
 */
object FilesManagerImpl: FilesManager {

    private val adbServer: AdbServer = Configurator.adbServer

    /**
     *  Performs adb push.
     *
     *  @param serverPath a file path relative to the server directory.
     *  @param devicePath a path to copy.
     */
    override fun push(serverPath: String, devicePath: String) {
        adbServer.performAdb("push $serverPath $devicePath")
    }
}