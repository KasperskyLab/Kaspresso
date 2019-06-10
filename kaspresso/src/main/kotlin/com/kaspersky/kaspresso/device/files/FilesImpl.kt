package com.kaspersky.kaspresso.device.files

import com.kaspersky.kaspresso.device.server.AdbServer

/**
 * Default implementation of Files interface.
 */
class FilesImpl: Files {

    /**
     *  Performs adb push.
     *
     *  Required Permissions: INTERNET
     *
     *  @param serverPath a file path relative to the server directory.
     *  @param devicePath a path to copy.
     */
    override fun push(serverPath: String, devicePath: String) {
        AdbServer.performAdb("push $serverPath $devicePath")
    }
}