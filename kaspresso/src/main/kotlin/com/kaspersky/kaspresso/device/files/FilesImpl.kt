package com.kaspersky.kaspresso.device.files

import com.kaspersky.kaspresso.device.server.AdbServer

/**
 * Default implementation of Files interface.
 */
class FilesImpl(
    private val adbServer: AdbServer
) : Files {

    /**
     *  Performs adb push.
     *
     *  Required Permissions: INTERNET.
     *
     *  @param serverPath a file path relative to the server directory.
     *  @param devicePath a path to copy.
     */
    override fun push(serverPath: String, devicePath: String) {
        adbServer.performAdb("push $serverPath $devicePath")
    }

    /**
     *  Removes a file by given path.
     *
     *  Required Permissions: INTERNET
     *
     *  @param path a path to remove
     */
    override fun remove(path: String) {
        adbServer.performShell("rm -f $path")
    }
}
