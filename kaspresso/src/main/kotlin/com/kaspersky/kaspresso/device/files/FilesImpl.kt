package com.kaspersky.kaspresso.device.files

import com.kaspersky.kaspresso.device.server.AdbServer
import com.kaspersky.kaspresso.logger.UiTestLogger

/**
 * The implementation of the [Files] interface.
 */
class FilesImpl(
    private val logger: UiTestLogger,
    private val adbServer: AdbServer
) : Files {

    /**
     * Performs adb push.
     *
     * Required Permissions: INTERNET.
     *
     * @param serverPath a file path relative to the server directory.
     * @param devicePath a path to copy.
     */
    override fun push(serverPath: String, devicePath: String) {
        adbServer.performAdb("push $serverPath $devicePath")
        logger.i("Push file from $serverPath to $devicePath")
    }

    /**
     * Removes a file by given path.
     *
     * Required Permissions: INTERNET
     *
     * @param path a path to remove
     */
    override fun remove(path: String) {
        adbServer.performShell("rm -f $path")
        logger.i("Remove file from $path")
    }

    /**
     * Performs adb pull.
     *
     * Required Permissions: INTERNET.
     *
     * @param devicePath a file path relative to the device directory.
     * @param serverPath a path to copy. (If empty - pulls in adbServer directory (folder with file "adbserver-desktop.jar"))
     */
    override fun pull(devicePath: String, serverPath: String) {
        adbServer.performAdb("pull $devicePath $serverPath")
        logger.i("Pull file from $devicePath to $serverPath")
    }
}
