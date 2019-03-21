package com.kaspersky.kaspresso.device.files

import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.device.server.AdbServer

object FilesManagerImpl: FilesManager {

    private val adbServer: AdbServer = Configurator.adbServer

    override fun push(serverPath: String, devicePath: String) {
        adbServer.performAdb("push $serverPath $devicePath")
    }
}