package com.kaspersky.kaspresso.device.files

import com.kaspersky.kaspresso.device.server.AdbServerWrapper

/**
 * Default implementation of Files interface.
 */
class FilesImpl : Files {

    /**
     *  Performs adb push.
     *
     *  Required Permissions: INTERNET.
     *
     *  @param serverPath a file path relative to the server directory.
     *  @param devicePath a path to copy.
     */
    override fun push(serverPath: String, devicePath: String) {
        AdbServerWrapper.performAdb("push $serverPath $devicePath")
    }

    /**
     *  Removes a file by given path.
     *
     *  Required Permissions: INTERNET
     *
     *  @param path a path to remove
     */
    override fun remove(path: String) {
        AdbServerWrapper.performShell("rm -f $path")
    }
}
