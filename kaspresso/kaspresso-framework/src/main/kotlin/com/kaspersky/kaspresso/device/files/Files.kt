package com.kaspersky.kaspresso.device.files

import com.kaspersky.kaspresso.annotations.RequiresAdbServer

/**
 * The interface to work with file permissions.
 *
 * Required: Started AdbServer
 *     1. Download a file "kaspresso/artifacts/adbserver-desktop.jar"
 *     2. Start AdbServer => input in cmd "java jar path_to_file/adbserver-desktop.jar"
 * Methods demanding to use AdbServer in the default implementation of this interface are marked.
 *     But nobody can't deprecate you to write implementation that doesn't require AdbServer.
 */
interface Files {

    /**
     * Performs adb push.
     *
     * Required Permissions: INTERNET.
     *
     * @param serverPath a file path relative to the server directory.
     * @param devicePath a path to copy.
     */
    @RequiresAdbServer
    fun push(serverPath: String, devicePath: String)

    /**
     * Removes a file by given path.
     *
     * Required Permissions: INTERNET
     *
     * @param path a path to remove
     */
    @RequiresAdbServer
    fun remove(path: String)

    /**
     * Performs adb pull.
     *
     * Required Permissions: INTERNET.
     *
     * @param devicePath a file path relative to the device directory.
     * @param serverPath a path to copy. (If empty - pulls in adbServer directory (folder with file "adbserver-desktop.jar"))
     */
    @RequiresAdbServer
    fun pull(devicePath: String, serverPath: String = "")
}
